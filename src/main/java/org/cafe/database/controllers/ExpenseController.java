package org.cafe.database.controllers;

import java.util.ArrayList;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.expense.CreateExpenseModel;
import org.cafe.models.expense.ExpenseModel;

public class ExpenseController extends DatabaseController<ExpenseModel> {
  public ExpenseController(DatabaseService databaseService) {
    super(
            databaseService,
            "Expenses",
            new String[]{"name", "value", "period", "description"}
    );
  }

  public void create(
          CreateExpenseModel expense
  ) {
    Object[] values = {
      expense.getName(),
      expense.getValue(),
      expense.getPeriod(),
      expense.getDescription(),};

    super.insert(values);
  }

  public ExpenseModel getById(String id) {
    ArrayList<Object[]> results = super.findById(id);
    if (results.isEmpty()) {
      throw new IllegalArgumentException("Despesa com o ID fornecido não encontrada.");
    }

    Object[] row = results.getFirst();

    return new ExpenseModel(
            (String) row[0],
            (String) row[1],
            (double) row[2],
            (String) row[3],
            (String) row[4]
    );
  }

  public ArrayList<ExpenseModel> getAll() {
    ArrayList<Object[]> results = super.findAll();
    if (results.isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<ExpenseModel> expenses = new ArrayList<>();
    for (Object[] row : results) {
      expenses.add(
              new ExpenseModel(
                      (String) row[0],
                      (String) row[1],
                      (double) row[2],
                      (String) row[3],
                      (String) row[4]
              )
      );
    }

    return expenses;
  }

  public void update(ExpenseModel updateExpense) {
    Object[] values = {
      updateExpense.getName(),
      updateExpense.getValue(),
      updateExpense.getPeriod(),
      updateExpense.getDescription(),};

    super.setById(updateExpense.getId(), values);
  }
}

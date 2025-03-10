package br.edu.ifpb.database.controllers;

import java.util.ArrayList;
import br.edu.ifpb.database.DatabaseController;
import br.edu.ifpb.database.DatabaseService;
import br.edu.ifpb.models.expense.CreateExpenseModel;
import br.edu.ifpb.models.expense.ExpenseModel;

public class ExpenseDatabaseController extends DatabaseController<ExpenseModel> {
  public ExpenseDatabaseController(DatabaseService databaseService) {
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

    Object[] result = results.get(0);

    return new ExpenseModel(
            (String) result[0],
            (String) result[1],
            (double) result[2],
            (String) result[3],
            (String) result[4]
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

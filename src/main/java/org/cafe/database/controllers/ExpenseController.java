package org.cafe.database.controllers;

import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.ExpenseModel;

import java.util.ArrayList;

public class ExpenseController extends DatabaseController<ExpenseModel> {
  public ExpenseController(DatabaseService databaseService) {
    super(
      databaseService,
      "Expenses",
      new String[]{ "name", "value", "period", "description" }
    );
  }

  public void create(
    ExpenseModel expenseModel
  ) {
    Object[] values = {
      expenseModel.getName(),
      expenseModel.getValue(),
      expenseModel.getPeriod(),
      expenseModel.getDescription(),
    };

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

  public void updateById(
    String id,
    ExpenseModel updatedExpense
  ) {
    Object[] values = {
      updatedExpense.getName(),
      updatedExpense.getValue(),
      updatedExpense.getPeriod(),
      updatedExpense.getDescription(),
    };

    super.setById(id, values);
  }
}

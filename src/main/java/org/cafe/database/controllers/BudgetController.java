package org.cafe.database.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget.CreateBudgetModel;

public class BudgetController extends DatabaseController<BudgetModel> {
  public BudgetController(DatabaseService databaseService) {
    super(
            databaseService,
            "Budgets",
            new String[]{"name", "description", "category", "status", "total_budget_value", "total_spent", "initial_date", "end_date"}
    );
  }

  /**
   * @param createBudgetModel Dados de criação de um orçamento.
   * @return Retorna o ID do registro criado.
   */
  public String create(CreateBudgetModel createBudgetModel) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    String initialDateFormatted = createBudgetModel.getInitialDate().format(formatter);
    String endDateFormatted = createBudgetModel.getEndDate().format(formatter);

    Object[] values = {
      createBudgetModel.getName(),
      createBudgetModel.getDescription(),
      createBudgetModel.getCategory(),
      createBudgetModel.getStatus(),
      createBudgetModel.getTotalBudgetValue(),
      createBudgetModel.getTotalSpent(),
      initialDateFormatted,
      endDateFormatted
    };

    return super.insert(values);
  }

  public BudgetModel getById(String id) {
    ArrayList<Object[]> results = super.findById(id);

    if (results == null || results.isEmpty()) {
      return null;
    }

    Object[] result = results.get(0);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    BudgetModel budget = new BudgetModel(
            (String) result[0],
            (String) result[1],
            (String) result[2],
            (String) result[3],
            (String) result[4],
            (double) result[5],
            (double) result[6],
            LocalDateTime.parse((String) result[7], formatter),
            LocalDateTime.parse((String) result[8], formatter)
    );

    return budget;
  }

  public ArrayList<BudgetModel> getAll() {
    ArrayList<Object[]> results = super.findAll();
    if (results.isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<BudgetModel> budgets = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    for (Object[] row : results) {
      BudgetModel budget = new BudgetModel(
              (String) row[0],
              (String) row[1],
              (String) row[2],
              (String) row[3],
              (String) row[4],
              (double) row[5],
              (double) row[6],
              LocalDateTime.parse((String) row[7], formatter),
              LocalDateTime.parse((String) row[8], formatter)
      );

      budgets.add(budget);
    }

    return budgets;
  }

  public void update(BudgetModel updatedBudget) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    String initialDateFormatted = updatedBudget.getInitialDate().format(formatter);
    String endDateFormatted = updatedBudget.getEndDate().format(formatter);

    Object[] values = {
      updatedBudget.getName(),
      updatedBudget.getDescription(),
      updatedBudget.getCategory(),
      updatedBudget.getStatus(),
      updatedBudget.getTotalBudgetValue(),
      updatedBudget.getTotalSpent(),
      initialDateFormatted,
      endDateFormatted
    };

    super.setById(updatedBudget.getId(), values);
  }
}

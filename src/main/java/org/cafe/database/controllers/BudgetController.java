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
      new String[]{"name", "description", "category", "status", "initial_date", "end_date"}
    );
  }

  public void create(CreateBudgetModel createBudgetModel) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    String initialDateFormatted = createBudgetModel.getInitialDate().format(formatter);
    String endDateFormatted = createBudgetModel.getEndDate().format(formatter);

    Object[] values = {
      createBudgetModel.getName(),
      createBudgetModel.getDescription(),
      createBudgetModel.getCategory(),
      createBudgetModel.getStatus(),
      initialDateFormatted,
      endDateFormatted
    };

    super.insert(values);
  }

  public ArrayList<BudgetModel> getAll() {
    try {
      ArrayList<Object[]> results = super.findAll();
      if (results.isEmpty()) {
        throw new IllegalArgumentException("Nenhum orçamento encontrado.");
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
          LocalDateTime.parse((String) row[5], formatter),
          LocalDateTime.parse((String) row[6], formatter)
        );

        budgets.add(budget);
      }

      return budgets;
    } catch (Exception e) {
      throw new RuntimeException("Erro ao buscar os orçamentos: " + e.getMessage(), e);
    }
  }

  public void update(String id, CreateBudgetModel updatedBudget) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    String initialDateFormatted = updatedBudget.getInitialDate().format(formatter);
    String endDateFormatted = updatedBudget.getEndDate().format(formatter);

    Object[] values = {
      updatedBudget.getName(),
      updatedBudget.getDescription(),
      updatedBudget.getCategory(),
      updatedBudget.getStatus(),
      initialDateFormatted,
      endDateFormatted
    };

    super.setById(id, values);
  }
}

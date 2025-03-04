package org.cafe.database.controllers;

import java.util.ArrayList;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget.CreateBudgetModel;
import org.cafe.utils.DateFormatter;

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
    String initialDateFormatted = DateFormatter.formatISO(createBudgetModel.getInitialDate());
    if (initialDateFormatted == null) {
      return null;
    }
    String endDateFormatted = DateFormatter.formatISO(createBudgetModel.getEndDate());
    if (endDateFormatted == null) {
      return null;
    }

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

  private BudgetModel getBudget(Object[] row) {
    return new BudgetModel(
            (String) row[0],
            (String) row[1],
            (String) row[2],
            (String) row[3],
            (String) row[4],
            (double) row[5],
            (double) row[6],
            DateFormatter.parseISO((String) row[7]),
            DateFormatter.parseISO((String) row[8])
    );
  }

  public BudgetModel getById(String id) {
    ArrayList<Object[]> results = super.findById(id);

    if (results == null || results.isEmpty()) {
      return null;
    }

    Object[] result = results.get(0);

    return getBudget(result);
  }

  public ArrayList<BudgetModel> getAll() {
    ArrayList<Object[]> results = super.findAll();
    if (results.isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<BudgetModel> budgets = new ArrayList<>();

    for (Object[] row : results) {
      budgets.add(getBudget(row));
    }

    return budgets;
  }

  public void update(BudgetModel updatedBudget) {
    String initialDateFormatted = DateFormatter.formatISO(updatedBudget.getInitialDate());
    String endDateFormatted = DateFormatter.formatISO(updatedBudget.getEndDate());

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

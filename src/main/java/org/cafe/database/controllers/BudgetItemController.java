package org.cafe.database.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.budget_item.BudgetItemModel;
import org.cafe.models.budget_item.CreateBudgetItemModel;

public class BudgetItemController extends DatabaseController<BudgetItemModel> {
  public BudgetItemController(DatabaseService databaseService) {
    super(
            databaseService,
            "BudgetItems",
            new String[]{"budget_id", "name", "description", "value", "period", "created_at"}
    );
  }

  private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public String create(CreateBudgetItemModel createBudgetItemModel) {
    String createdAtFormatted = createBudgetItemModel.getCreatedAt().format(FORMATTER);

    Object[] values = {
      createBudgetItemModel.getBudgetId(),
      createBudgetItemModel.getName(),
      createBudgetItemModel.getDescription(),
      createBudgetItemModel.getValue(),
      createBudgetItemModel.getPeriod(),
      createdAtFormatted
    };

    return super.insert(values);
  }

  public ArrayList<BudgetItemModel> getAll() {
    ArrayList<Object[]> results = super.findAll();
    if (results.isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<BudgetItemModel> budgetItems = new ArrayList<>();

    for (Object[] row : results) {
      BudgetItemModel budgetItem = new BudgetItemModel(
              (String) row[0],
              (String) row[1],
              (String) row[2],
              (String) row[3],
              (Double) row[4],
              (String) row[5],
              LocalDate.parse((String) row[6], FORMATTER)
      );

      budgetItems.add(budgetItem);
    }

    return budgetItems;
  }

  /**
   * Obtém todos os itens de orçamento relacionados com base no budget_id.
   *
   * @param budgetId O ID do orçamento.
   * @return Uma lista contendo todos os itens de orçamento associados ao ID do
   * orçamento.
   */
  public ArrayList<BudgetItemModel> getAllByBudgetId(String budgetId) {
    ArrayList<Object[]> results = super.findAllByCondition("budget_id = ?", budgetId);
    if (results.isEmpty()) {
      return new ArrayList<>();
    }

    ArrayList<BudgetItemModel> budgetItems = new ArrayList<>();

    for (Object[] row : results) {
      BudgetItemModel budgetItem = new BudgetItemModel(
              (String) row[0],
              (String) row[1],
              (String) row[2],
              (String) row[3],
              (Double) row[4],
              (String) row[5],
              LocalDate.parse((String) row[6], FORMATTER)
      );

      budgetItems.add(budgetItem);
    }

    return budgetItems;
  }

  public void update(BudgetItemModel updatedBudgetItem) {
    String createdAtFormatted = updatedBudgetItem.getCreatedAt().format(FORMATTER
    );

    Object[] values = {
      updatedBudgetItem.getBudgetId(),
      updatedBudgetItem.getName(),
      updatedBudgetItem.getDescription(),
      updatedBudgetItem.getValue(),
      updatedBudgetItem.getPeriod(),
      createdAtFormatted
    };

    super.setById(updatedBudgetItem.getId(), values);
  }
}

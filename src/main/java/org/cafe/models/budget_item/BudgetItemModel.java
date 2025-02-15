package org.cafe.models.budget_item;

public class BudgetItemModel extends CreateBudgetItemModel {
  private String id;

  public BudgetItemModel(
          String id,
          String budgetId,
          String name,
          String description,
          double value,
          String period
  ) {
    super(budgetId, name, description, value, period);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

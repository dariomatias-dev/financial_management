package br.edu.ifpb.models.budget_item;

import java.time.LocalDate;

public class BudgetItemModel extends CreateBudgetItemModel {
  private String id;

  public BudgetItemModel(
          String id,
          String budgetId,
          String name,
          String description,
          double value,
          String period,
          LocalDate createdAt
  ) {
    super(budgetId, name, description, value, period, createdAt);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

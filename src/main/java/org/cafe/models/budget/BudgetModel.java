package org.cafe.models.budget;

import java.time.LocalDateTime;

public class BudgetModel extends CreateBudgetModel {
  private String id;

  public BudgetModel(
          String id,
          String name,
          String description,
          String category,
          String status,
          double totalBudgetValue,
          double totalSpent,
          LocalDateTime initialDate,
          LocalDateTime endDate
  ) {
    super(name, description, category, status, totalBudgetValue, totalSpent, initialDate, endDate);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

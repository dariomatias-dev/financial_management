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
    LocalDateTime initialDate,
    LocalDateTime endDate
  ) {
    super(name, description, category, status, initialDate, endDate);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

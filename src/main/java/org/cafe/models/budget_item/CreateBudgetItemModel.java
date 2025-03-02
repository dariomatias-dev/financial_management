package org.cafe.models.budget_item;

import java.time.LocalDate;

public class CreateBudgetItemModel {
  private String budgetId;
  private String name;
  private String description;
  private double value;
  private String period;
  private LocalDate createdAt;

  public CreateBudgetItemModel(
          String budgetId,
          String name,
          String description,
          double value,
          String period,
          LocalDate createdAt
  ) {
    this.budgetId = budgetId;
    this.name = name;
    this.description = description;
    this.value = value;
    this.period = period;
    this.createdAt = createdAt;
  }

  public String getBudgetId() {
    return budgetId;
  }

  public void setBudgetId(String budgetId) {
    this.budgetId = budgetId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public String getPeriod() {
    return period;
  }

  public void setPeriod(String period) {
    this.period = period;
  }

  public LocalDate getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }
}

package org.cafe.models.budget;

import java.time.LocalDateTime;

public class CreateBudgetModel {
  private String name;
  private String description;
  private String category;
  private String status;
  private double totalBudgetValue;
  private double totalSpent;
  private LocalDateTime initialDate;
  private LocalDateTime endDate;

  public CreateBudgetModel(
          String name,
          String description,
          String category,
          String status,
          double totalBudgetValue,
          double totalSpent,
          LocalDateTime initialDate,
          LocalDateTime endDate
  ) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.status = status;
    this.totalBudgetValue = totalBudgetValue;
    this.totalSpent = totalSpent;
    this.initialDate = initialDate;
    this.endDate = endDate;
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

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDateTime getInitialDate() {
    return initialDate;
  }

  public void setInitialDate(LocalDateTime initialDate) {
    this.initialDate = initialDate;
  }

  public double getTotalBudgetValue() {
    return totalBudgetValue;
  }

  public void setTotalBudgetValue(double totalBudgetValue) {
    this.totalBudgetValue = totalBudgetValue;
  }

  public double getTotalSpent() {
    return totalSpent;
  }

  public void setTotalSpent(double totalSpent) {
    this.totalSpent = totalSpent;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDateTime endDate) {
    this.endDate = endDate;
  }
}

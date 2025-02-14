package org.cafe.models.budget;

import java.time.LocalDate;

public class CreateBudgetModel {
  private String name;
  private String description;
  private String category;
  private String status;
  private LocalDate initialDate;
  private LocalDate endDate;

  public CreateBudgetModel(
    String name,
    String description,
    String category,
    String status,
    LocalDate initialDate,
    LocalDate endDate
  ) {
    this.name = name;
    this.description = description;
    this.category = category;
    this.status = status;
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

  public LocalDate getInitialDate() {
    return initialDate;
  }

  public void setInitialDate(LocalDate initialDate) {
    this.initialDate = initialDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
}

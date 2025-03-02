package org.cafe.models.revenue;

public class CreateRevenueModel {
  private String name;
  private String description;
  private double value;
  private String period;

  public CreateRevenueModel(
          String name,
          String description,
          double value,
          String period
  ) {
    this.name = name;
    this.description = description;
    this.value = value;
    this.period = period;
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
}

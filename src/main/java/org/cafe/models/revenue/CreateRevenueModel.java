package org.cafe.models.revenue;

public class CreateRevenueModel {
  private String name;
  private double value;
  private String period;
  private String revenueType;
  private String description;

  public CreateRevenueModel(
    String name,
    double value,
    String period,
    String revenueType,
    String description
  ) {
    this.name = name;
    this.value = value;
    this.period = period;
    this.revenueType = revenueType;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getRevenueType() {
    return revenueType;
  }

  public void setRevenueType(String revenueType) {
    this.revenueType = revenueType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

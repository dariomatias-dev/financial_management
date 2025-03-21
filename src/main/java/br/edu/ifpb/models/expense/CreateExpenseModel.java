package br.edu.ifpb.models.expense;

public class CreateExpenseModel {

  private String name;
  private double value;
  private String period;
  private String description;

  public CreateExpenseModel(
    String name,
    double value,
    String period,
    String description
  ) {
    this.name = name;
    this.value = value;
    this.period = period;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}

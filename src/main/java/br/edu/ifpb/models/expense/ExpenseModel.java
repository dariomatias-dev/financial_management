package br.edu.ifpb.models.expense;

public class ExpenseModel extends CreateExpenseModel {
  private String id;

  public ExpenseModel(
          String id,
          String name,
          double value,
          String period,
          String description
  ) {
    super(name, value, period, description);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

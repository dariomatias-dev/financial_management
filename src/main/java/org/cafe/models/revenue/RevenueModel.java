package org.cafe.models.revenue;

public class RevenueModel extends CreateRevenueModel {
  private String id;

  public RevenueModel(
          String id,
          String name,
          String description,
          double value,
          String period
  ) {
    super(name, description, value, period);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

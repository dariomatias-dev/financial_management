package org.cafe.models.revenue;

public class RevenueModel extends CreateRevenueModel {
  private String id;

  public RevenueModel(
          String id,
          String name,
          double value,
          String period,
          String description,
          String revenueType
  ) {
    super(name, value, period, description, revenueType);
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}

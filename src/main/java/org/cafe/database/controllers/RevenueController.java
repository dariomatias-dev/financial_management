package org.cafe.database.controllers;

import java.util.ArrayList;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.revenue.RevenueModel;

import java.util.ArrayList;
import org.cafe.models.revenue.CreateRevenueModel;

public class RevenueController extends DatabaseController<RevenueModel> {
  public RevenueController(DatabaseService databaseService) {
    super(
      databaseService,
      "Revenues",
      new String[]{ "name", "value", "period", "description", "revenue_type" }
    );
  }

  public void create(
            CreateRevenueModel RevenueModel
  ) {
    Object[] values = {
      RevenueModel.getName(),
      RevenueModel.getValue(),
      RevenueModel.getPeriod(),
      RevenueModel.getRevenueType(),
      RevenueModel.getDescription()

    };

    super.insert(values);
  }

  public RevenueModel getById(String id) {
    ArrayList<Object[]> results = super.findById(id);
    if (results.isEmpty()) {
      throw new IllegalArgumentException("Receita com o ID fornecido não encontrada.");
    }

    Object[] row = results.getFirst();

    return new RevenueModel(
      (String) row[0],
      (String) row[1],
      (double) row[2],
      (String) row[3],
      (String) row[4],
      (String) row[5]
    );
  }

  public ArrayList<RevenueModel> getAll() {
    ArrayList<Object[]> results = super.findAll();
    ArrayList<RevenueModel> revenues = new ArrayList<>();
    for (Object[] row : results) {
      revenues.add(
        new RevenueModel(
          (String) row[0],
          (String) row[1],
          (double) row[2],
          (String) row[3],
          (String) row[4],
          (String) row[5]
        )
      );
    }

    return revenues;
  }

  public void update(RevenueModel updateRevenue) {
    Object[] values = {
      updateRevenue.getName(),
      updateRevenue.getValue(),
      updateRevenue.getPeriod(),
      updateRevenue.getRevenueType(),
      updateRevenue.getDescription(),
    };

    super.setById(updateRevenue.getId(), values);
  }
}

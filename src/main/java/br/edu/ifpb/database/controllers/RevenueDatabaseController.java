package br.edu.ifpb.database.controllers;

import java.util.ArrayList;
import br.edu.ifpb.database.DatabaseController;
import br.edu.ifpb.database.DatabaseService;
import br.edu.ifpb.models.revenue.CreateRevenueModel;
import br.edu.ifpb.models.revenue.RevenueModel;

public class RevenueDatabaseController extends DatabaseController<RevenueModel> {
  public RevenueDatabaseController(DatabaseService databaseService) {
    super(
            databaseService,
            "Revenues",
            new String[]{"name", "description", "value", "period"}
    );
  }

  public void create(
          CreateRevenueModel revenue
  ) {
    Object[] values = {
      revenue.getName(),
      revenue.getDescription(),
      revenue.getValue(),
      revenue.getPeriod()
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
            (String) row[2],
            (double) row[3],
            (String) row[4]
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
                      (String) row[2],
                      (double) row[3],
                      (String) row[4]
              )
      );
    }

    return revenues;
  }

  public void update(RevenueModel updateRevenue) {
    Object[] values = {
      updateRevenue.getName(),
      updateRevenue.getDescription(),
      updateRevenue.getValue(),
      updateRevenue.getPeriod()
    };

    super.setById(updateRevenue.getId(), values);
  }
}

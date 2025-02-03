package org.cafe.controllers;

import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.Revenue.RevenueModel;

import java.util.ArrayList;

public class RevenueController extends DatabaseController<RevenueModel> {
    public RevenueController(DatabaseService databaseService) {
        super(
            databaseService,
            "revenues",
            new String[]{ "name","GrossValue", "NetValue", "period","RevenueType","description" }
        );
    }

    public void create(
            RevenueModel RevenueModel
    ) {
        Object[] values = {
            RevenueModel.getName(),
            RevenueModel.getGrossValue(),
            RevenueModel.getNetValue(),
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
            (double) row[1],
            (double) row[2],
            (String) row[3],
            (String) row[4],
            (String) row[5]
        );
    }

    public ArrayList<RevenueModel> getAll() {
        ArrayList<Object[]> results = super.findAll();
        ArrayList<RevenueModel> expenses = new ArrayList<>();
        for (Object[] row : results) {
            expenses.add(
                new RevenueModel(
                    (String) row[0],
                    (double) row[1],
                    (double) row[2],
                    (String) row[3],
                    (String) row[4],
                    (String) row[5]
                )
            );
        }

        return expenses;
    }

    public void updateById(
            String id,
            RevenueModel updatedRevenue
    ) {
        Object[] values = {
            updatedRevenue.getName(),
            updatedRevenue.getGrossValue(),
            updatedRevenue.getNetValue(),
            updatedRevenue.getPeriod(),
            updatedRevenue.getRevenueType(),
            updatedRevenue.getDescription(),
        };

        super.setById(id, values);
    }
}
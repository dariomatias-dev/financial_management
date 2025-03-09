package org.cafe.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.cafe.database.controllers.BudgetDatabaseController;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget_item.BudgetItemModel;

public class BudgetCalculator {
  public BudgetModel calculate(
          BudgetModel budget,
          BudgetDatabaseController budgetDatabaseController,
          List<BudgetItemModel> budgetItems
  ) {
    double totalBudget = 0.0;

    for (BudgetItemModel item : budgetItems) {
      long numberOfPeriods = calculatePeriods(item.getPeriod(), budget.getInitialDate(), budget.getEndDate());
      double itemTotal = numberOfPeriods * item.getValue();
      totalBudget += itemTotal;
    }

    BudgetModel newBudget = new BudgetModel(
            budget.getId(),
            budget.getName(),
            budget.getDescription(),
            budget.getCategory(),
            budget.getStatus(),
            budget.getTotalBudgetValue(),
            totalBudget,
            budget.getInitialDate(),
            budget.getEndDate()
    );

    budgetDatabaseController.update(newBudget);

    return newBudget;
  }

  private long calculatePeriods(String period, LocalDate startDate, LocalDate endDate) {
    return switch (period.toLowerCase()) {
      case "diário" ->
        ChronoUnit.DAYS.between(startDate, endDate);
      case "semanal" ->
        ChronoUnit.WEEKS.between(startDate, endDate);
      case "mensal" ->
        ChronoUnit.MONTHS.between(startDate, endDate);
      default ->
        throw new IllegalArgumentException("Invalid period type. Use 'diário', 'semanal', or 'mensal'.");
    };
  }
}

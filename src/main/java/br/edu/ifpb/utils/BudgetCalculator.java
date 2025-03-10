package br.edu.ifpb.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import br.edu.ifpb.database.controllers.BudgetDatabaseController;
import br.edu.ifpb.models.budget.BudgetModel;
import br.edu.ifpb.models.budget_item.BudgetItemModel;

public class BudgetCalculator {
  public BudgetModel calculate(
          BudgetModel budget,
          BudgetDatabaseController budgetDatabaseController,
          List<BudgetItemModel> budgetItems
  ) {
    double totalBudget = 0.0;
    
    for (BudgetItemModel item : budgetItems) {
      long numberOfPeriods = calculatePeriods(item.getPeriod(), budget.getInitialDate(), budget.getEndDate().plusDays(1));
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

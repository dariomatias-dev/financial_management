package org.cafe.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.database.controllers.RevenueController;

public class FinancialSummary {
  private final RevenueController revenueController;
  private final ExpenseController expenseController;
  private final Date startDate;
  private final Date endDate;

  public FinancialSummary(
          RevenueController revenueController,
          ExpenseController expenseController,
          Date startDate,
          Date endDate
  ) {
    this.revenueController = revenueController;
    this.expenseController = expenseController;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public double calculateAndDisplaySummary() {
    double totalRevenue = revenueController.getAll().stream()
            .filter(revenue -> isWithinDateRange(revenue.getPeriod(), startDate, endDate))
            .mapToDouble(revenue -> calculateValueForPeriod(revenue.getPeriod(), revenue.getValue(), startDate, endDate))
            .sum();

    double totalExpense = expenseController.getAll().stream()
            .filter(expense -> isWithinDateRange(expense.getPeriod(), startDate, endDate))
            .mapToDouble(expense -> calculateValueForPeriod(expense.getPeriod(), expense.getValue(), startDate, endDate))
            .sum();

    return totalRevenue - totalExpense;
  }

  private boolean isWithinDateRange(String period, Date startDate, Date endDate) {
    LocalDate start = startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    LocalDate end = endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

    long numberOfPeriods = calculatePeriods(period, start, end);
    return numberOfPeriods > 0;
  }

  private long calculatePeriods(String period, LocalDate startDate, LocalDate endDate) {
    return switch (period.toLowerCase()) {
      case "diário" -> ChronoUnit.DAYS.between(startDate, endDate);
      case "semanal" -> ChronoUnit.WEEKS.between(startDate, endDate);
      case "mensal" -> ChronoUnit.MONTHS.between(startDate, endDate);
      default -> throw new IllegalArgumentException("Tipo de período inválido. Use 'diário', 'semanal' ou 'mensal'.");
    };
  }

  private double calculateValueForPeriod(String period, double value, Date startDate, Date endDate) {
    LocalDate start = startDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    LocalDate end = endDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

    long numberOfPeriods = calculatePeriods(period, start, end);
    return value * numberOfPeriods;
  }
}

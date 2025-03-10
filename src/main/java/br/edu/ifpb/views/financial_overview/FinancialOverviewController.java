package br.edu.ifpb.views.financial_overview;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import br.edu.ifpb.database.controllers.ExpenseDatabaseController;
import br.edu.ifpb.database.controllers.RevenueDatabaseController;
import br.edu.ifpb.models.expense.ExpenseModel;
import br.edu.ifpb.models.revenue.RevenueModel;
import br.edu.ifpb.utils.CurrencyFormatter;
import br.edu.ifpb.utils.SetBackIcon;

public class FinancialOverviewController {
  private final RevenueDatabaseController revenueDatabaseController;
  private final ExpenseDatabaseController expenseDatabaseController;

  private final JComboBox<String> periodFilterField;
  private final JLabel totalValueRevenues;
  private final JLabel totalValueExpenses;
  private final JTable revenuesTable;
  private final JTable expensesTable;

  /**
   * Construtor.
   *
   * @param revenueDatabaseController Controlador de receitas.
   * @param expenseDatabaseController Controlador de despesas.
   * @param exitButton Botão de sair da tela.
   * @param screenTitle Título da tela de gerenciamento de despesas.
   * @param periodFilterField Seletor de filtro de período.
   * @param totalValueRevenues Valor total das receitas.
   * @param totalValueExpenses Valor total das despesas.
   * @param revenuesTable Tabela de receitas.
   * @param expensesTable Tabela de despesas.
   */
  public FinancialOverviewController(
          RevenueDatabaseController revenueDatabaseController,
          ExpenseDatabaseController expenseDatabaseController,
          JLabel exitButton,
          JLabel screenTitle,
          JComboBox<String> periodFilterField,
          JLabel totalValueRevenues,
          JLabel totalValueExpenses,
          JTable revenuesTable,
          JTable expensesTable
  ) {
    this.revenueDatabaseController = revenueDatabaseController;
    this.expenseDatabaseController = expenseDatabaseController;

    this.periodFilterField = periodFilterField;
    this.totalValueRevenues = totalValueRevenues;
    this.totalValueExpenses = totalValueExpenses;
    this.revenuesTable = revenuesTable;
    this.expensesTable = expensesTable;

    screenTitle.setFocusable(true);

    new SetBackIcon().set(exitButton);

    init();
  }

  private void init() {
    filterRegisters();
  }

  // Filtra as receitas e despesas com base no período selecionado.
  protected void filterRegisters() {
    ArrayList<RevenueModel> filteredRevenues = filterRegistersByPeriod(revenueDatabaseController.getAll());
    ArrayList<ExpenseModel> filteredExpenses = filterRegistersByPeriod(expenseDatabaseController.getAll());

    // Calcular o valor total das receitas do período selecionado.
    double totalRevenue = 0.0;
    for (RevenueModel revenue : filteredRevenues) {
      totalRevenue += revenue.getValue();
    }

    // Calcular o valor total das despesas do período selecionado.
    double totalExpense = 0.0;
    for (ExpenseModel expense : filteredExpenses) {
      totalExpense += expense.getValue();
    }

    // Mostra as receitas e despesas filtradas.
    showRevenues(filteredRevenues);
    showExpenses(filteredExpenses);

    // Mostra o valor total das receitas e despesas filtradas.
    totalValueRevenues.setText("Valor Total: " + CurrencyFormatter.format(totalRevenue));
    totalValueExpenses.setText("Valor Total: " + CurrencyFormatter.format(totalExpense));
  }

  /**
   * Filtra os registros por período.
   */
  private <T> ArrayList<T> filterRegistersByPeriod(ArrayList<T> records) {
    String periodFilter = (String) periodFilterField.getSelectedItem();
    ArrayList<T> filteredRecords = new ArrayList<>();

    for (T record : records) {
      if (record instanceof ExpenseModel expense && expense.getPeriod().equals(periodFilter)) {
        filteredRecords.add(record);
      } else if (record instanceof RevenueModel revenue && revenue.getPeriod().equals(periodFilter)) {
        filteredRecords.add(record);
      }
    }

    return filteredRecords;
  }

  /**
   * Exibi as receitas.
   */
  private void showRevenues(ArrayList<RevenueModel> revenues) {
    DefaultTableModel tableModel = (DefaultTableModel) revenuesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (RevenueModel revenue : revenues) {
      String formattedValue = CurrencyFormatter.format(revenue.getValue());

      Object[] rowData = new Object[3];
      rowData[0] = revenue.getName();
      rowData[1] = revenue.getDescription();
      rowData[2] = formattedValue;
      tableModel.addRow(rowData);
    }
  }

  /**
   * Mostra as informações das despesas.
   */
  private void showExpenses(ArrayList<ExpenseModel> expenses) {
    DefaultTableModel tableModel = (DefaultTableModel) expensesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela de despesas.
    for (ExpenseModel expense : expenses) {
      String formattedValue = CurrencyFormatter.format(expense.getValue());

      Object[] rowData = new Object[3];
      rowData[0] = expense.getName();
      rowData[1] = expense.getDescription();
      rowData[2] = formattedValue;
      tableModel.addRow(rowData);
    }
  }
}

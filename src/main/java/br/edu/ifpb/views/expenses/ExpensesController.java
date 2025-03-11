package br.edu.ifpb.views.expenses;

import br.edu.ifpb.database.controllers.ExpenseDatabaseController;
import br.edu.ifpb.models.expense.ExpenseModel;
import br.edu.ifpb.utils.ConfirmDeleteDialog;
import br.edu.ifpb.utils.CurrencyFormatter;
import br.edu.ifpb.utils.RecordVerification;
import br.edu.ifpb.utils.SearchFieldHandler;
import br.edu.ifpb.utils.SetBackIcon;
import br.edu.ifpb.utils.ValueRangeFilter;
import br.edu.ifpb.views.expenses.components.manager_expense.ManagerExpenseView;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpensesController {
  private final Window parentComponent;
  private final Runnable onUpdateMainScreen;
  private final ExpenseDatabaseController expenseDatabaseController;
  private final JTextField searchField;
  private final JTextField valueMinFilterField;
  private final JTextField valueMaxFilterField;
  private final JComboBox<String> periodFilterField;
  private final JTable expensesTable;

  private ArrayList<ExpenseModel> allExpenses;
  private ArrayList<ExpenseModel> displayedExpenses;

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param onUpdateMainScreen Método para atualização da tela principal.
   * @param expenseDatabaseController Controlador de despesas.
   * @param exitButton Botão de sair da tela.
   * @param screenTitle Título da tela de gerenciamento de despesas.
   * @param searchField Campo de pesquisa.
   * @param valueMinFilterField Campo de filtro de valor mínimo.
   * @param valueMaxFilterField Campo de filtro de valor máximo.
   * @param periodFilterField Seletor de filtro de período.
   * @param expensesTable Tabela de despesas.
   */
  public ExpensesController(
          Window parentComponent,
          Runnable onUpdateMainScreen,
          ExpenseDatabaseController expenseDatabaseController,
          JLabel exitButton,
          JLabel screenTitle,
          JTextField searchField,
          JTextField valueMinFilterField,
          JTextField valueMaxFilterField,
          JComboBox<String> periodFilterField,
          JTable expensesTable
  ) {
    this.parentComponent = parentComponent;
    this.onUpdateMainScreen = onUpdateMainScreen;
    this.expenseDatabaseController = expenseDatabaseController;
    this.searchField = searchField;
    this.valueMinFilterField = valueMinFilterField;
    this.valueMaxFilterField = valueMaxFilterField;
    this.periodFilterField = periodFilterField;
    this.expensesTable = expensesTable;

    screenTitle.setFocusable(true);
    new SearchFieldHandler(searchField).initialize();
    new SetBackIcon().set(exitButton);

    listExpenses();
  }

  /**
   * Obtém e exibe todas as despesas.
   */
  private void listExpenses() {
    allExpenses = expenseDatabaseController.getAll();
    displayedExpenses = allExpenses;

    showExpenses();
  }

  /**
   * Exibe as despesas.
   */
  private void showExpenses() {
    DefaultTableModel tableModel = (DefaultTableModel) expensesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (ExpenseModel expense : displayedExpenses) {
      String formattedValue = CurrencyFormatter.format(expense.getValue());
      Object[] rowData = {
        expense.getName(),
        expense.getDescription(),
        formattedValue,
        expense.getPeriod()
      };
      tableModel.addRow(rowData);
    }
  }

  /**
   * Atualiza a lista de despesas.
   */
  private void updateScreen() {
    onUpdateMainScreen.run();
    listExpenses();
    search();
  }

  // SCREEN ACTIONS
  /**
   * Realiza a pesquisa de despesas com base nos filtros aplicados.
   */
  protected void search() {
    // Obtém os filtros de pesquisa.
    String query = searchField.getText().trim().toLowerCase();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    // Verifica se a pesquisa está limpa e exibe todas as despesas.
    if (query.equals("pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedExpenses = allExpenses;
      showExpenses();
      return;
    }

    // Valida o intervalo de valores.
    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    if (!valueRangeFilter.validate(parentComponent, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    ArrayList<ExpenseModel> results = new ArrayList<>();

    // Aplica os filtros.
    for (ExpenseModel expense : allExpenses) {
      boolean matchesQuery = expense.getName().toLowerCase().contains(query) || expense.getDescription().toLowerCase().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || expense.getPeriod().equals(periodFilter);

      // Filtra pelo valor.
      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = expense.getValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = expense.getValue() <= valueRangeFilter.getValueMaxFilter();
      }

      // Adiciona a despesa aos resultados caso tenha passado pelos filtros.
      if (matchesQuery && matchesPeriod && matchesValue) {
        results.add(expense);
      }
    }

    displayedExpenses = results;
    showExpenses();
  }

  /**
   * Abre a tela de criação de uma nova despesa.
   */
  protected void addButton() {
    new ManagerExpenseView(
            expenseDatabaseController,
            null,
            this::updateScreen
    ).setVisible(true);
  }

  /**
   * Abre a tela de atualização da despesa selecionada.
   */
  protected void updateButton() {
    if (RecordVerification.verifyRecords(expensesTable, "atualizar")) {
      ExpenseModel selectedExpense = displayedExpenses.get(expensesTable.getSelectedRow());
      new ManagerExpenseView(
              expenseDatabaseController,
              selectedExpense,
              this::updateScreen
      ).setVisible(true);
    }
  }

  /**
   * Remove a despesa selecionada após confirmação.
   */
  protected void deleteButton() {
    ConfirmDeleteDialog.showDeleteConfirmation(
            expensesTable,
            () -> {
              ExpenseModel selectedExpense = displayedExpenses.get(expensesTable.getSelectedRow());
              expenseDatabaseController.removeById(selectedExpense.getId());
              updateScreen();
            }
    );
  }

  /**
   * Limpa todos os filtros aplicados.
   */
  protected void clearFiltersButton() {
    searchField.setText("Pesquisar...");
    valueMinFilterField.setText("");
    valueMaxFilterField.setText("");
    periodFilterField.setSelectedItem("Todos");

    search();
  }
}

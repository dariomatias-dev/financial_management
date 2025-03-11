package br.edu.ifpb.views.budgets;

import br.edu.ifpb.core.formatters.DateMaskFormatter;
import br.edu.ifpb.database.controllers.BudgetDatabaseController;
import br.edu.ifpb.database.controllers.BudgetItemDatabaseController;
import br.edu.ifpb.models.budget.BudgetModel;
import br.edu.ifpb.utils.ConfirmDeleteDialog;
import br.edu.ifpb.utils.DateFormatter;
import br.edu.ifpb.utils.RecordVerification;
import br.edu.ifpb.utils.SearchFieldHandler;
import br.edu.ifpb.utils.SetBackIcon;
import br.edu.ifpb.utils.ValueRangeFilter;
import br.edu.ifpb.views.budget.BudgetView;
import br.edu.ifpb.views.budgets.components.manager_budget.ManagerBudgetView;
import java.awt.Window;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BudgetsController {
  private final Window parentComponent;
  private final BudgetDatabaseController budgetDatabaseController;
  private final BudgetItemDatabaseController budgetItemDatabaseController;
  private final JTextField searchField;
  private final JTextField valueMinFilterField;
  private final JTextField valueMaxFilterField;
  private final JComboBox<String> statusFilterSelect;
  private final JFormattedTextField initialDateFilterField;
  private final JFormattedTextField endDateFilterField;
  private final JTable budgetsTable;

  private ArrayList<BudgetModel> allBudgets;
  private ArrayList<BudgetModel> displayedBudgets;

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   * @param exitButton Botão de sair da tela.
   * @param screenTitle Título da tela.
   * @param searchField Campo de pesquisa.
   * @param valueMinFilterField Campo de filtro de valor mínimo.
   * @param valueMaxFilterField Campo de filtro de valor máximo.
   * @param statusFilterSelect Seletor de filtro de status.
   * @param initialDateFilterField Campo de filtro de data inicial.
   * @param endDateFilterField Campo de filtro de data final.
   * @param budgetsTable Tabela de orçamentos.
   */
  public BudgetsController(
          Window parentComponent,
          BudgetDatabaseController budgetDatabaseController,
          BudgetItemDatabaseController budgetItemDatabaseController,
          JLabel exitButton,
          JLabel screenTitle,
          JTextField searchField,
          JTextField valueMinFilterField,
          JTextField valueMaxFilterField,
          JComboBox<String> statusFilterSelect,
          JFormattedTextField initialDateFilterField,
          JFormattedTextField endDateFilterField,
          JTable budgetsTable
  ) {
    this.parentComponent = parentComponent;
    this.budgetDatabaseController = budgetDatabaseController;
    this.budgetItemDatabaseController = budgetItemDatabaseController;
    this.searchField = searchField;
    this.valueMinFilterField = valueMinFilterField;
    this.valueMaxFilterField = valueMaxFilterField;
    this.statusFilterSelect = statusFilterSelect;
    this.initialDateFilterField = initialDateFilterField;
    this.endDateFilterField = endDateFilterField;
    this.budgetsTable = budgetsTable;

    screenTitle.setFocusable(true);
    new SearchFieldHandler(searchField).initialize();
    new SetBackIcon().set(exitButton);

    new DateMaskFormatter().applyMask(initialDateFilterField);
    new DateMaskFormatter().applyMask(endDateFilterField);

    listBudgets();

    showBudgets();
  }

  /**
   * Obtém todos os orçamento.
   */
  private void listBudgets() {
    allBudgets = budgetDatabaseController.getAll();
    displayedBudgets = allBudgets;
  }

  /**
   * Exibi os orçamentos.
   */
  private void showBudgets() {
    DefaultTableModel tableModel = (DefaultTableModel) budgetsTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (BudgetModel budget : displayedBudgets) {
      String initialDateFormatted = DateFormatter.format(budget.getInitialDate());
      String endDateFormatted = DateFormatter.format(budget.getEndDate());
      String valueFormatted = String.format("%.2f", budget.getTotalBudgetValue());

      Object[] rowData = new Object[6];
      rowData[0] = budget.getName();
      rowData[1] = budget.getCategory();
      rowData[2] = "R$ " + valueFormatted;
      rowData[3] = budget.getStatus();
      rowData[4] = initialDateFormatted;
      rowData[5] = endDateFormatted;

      tableModel.addRow(rowData);
    }
  }

  /**
   * Atualiza a lista de orçamentos.
   */
  private void updateScreen() {
    listBudgets();

    search();
  }

  /**
   * Lida com a criação de um orçamento.
   */
  private void onBudgetCreated(
          String budgetId
  ) {
    BudgetModel selectedBudget = budgetDatabaseController.getById(budgetId);
    new BudgetView(
            budgetDatabaseController,
            budgetItemDatabaseController,
            selectedBudget,
            (value) -> {
              updateScreen();
            }
    ).setVisible(true);

    updateScreen();
  }

  /**
   * Lida com a atualização de um orçamento.
   */
  private void onUpdateBudget(int index, BudgetModel value) {
    allBudgets.set(index, value);

    displayedBudgets = allBudgets;
  }

  // SCREEN ACTIONS
  /**
   * Filtra os orçamentos de acordo com os filtros definidos.
   */
  protected void search() {
    // Obtenção dos filtros.
    String query = searchField.getText().trim().toLowerCase();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();
    String statusFilter = (String) statusFilterSelect.getSelectedItem();
    String initialDateFilterText = initialDateFilterField.getText().trim();
    String endDateFilterText = endDateFilterField.getText().trim();

    // Verificação da presença de filtragem.
    if (query.equals("pesquisar...") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty() && statusFilter.equals("Todos")
            && initialDateFilterText.equals(DateFormatter.PLACEHOLDER_DATE) && endDateFilterText.equals(DateFormatter.PLACEHOLDER_DATE)) {
      displayedBudgets = allBudgets;
      showBudgets();

      return;
    }

    // Valida o intervalo de valores.
    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    if (!valueRangeFilter.validate(parentComponent, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    // Formatação da data inicial e final.
    LocalDate initialDateFilter = null;
    if (!initialDateFilterText.equals(DateFormatter.PLACEHOLDER_DATE)) {
      initialDateFilter = DateFormatter.parse(initialDateFilterText);
    }

    LocalDate endDateFilter = null;
    if (!endDateFilterText.equals(DateFormatter.PLACEHOLDER_DATE)) {
      endDateFilter = DateFormatter.parse(endDateFilterText);
    }

    ArrayList<BudgetModel> results = new ArrayList<>();

    // Filtragem dos orçamentos.
    for (BudgetModel budget : allBudgets) {
      // Filtro de texto e status.
      boolean matchesQuery = budget.getName().toLowerCase().contains(query) || budget.getDescription().toLowerCase().contains(query) || budget.getCategory().toLowerCase().contains(query);
      boolean matchesStatus = statusFilter.equals("Todos") || budget.getStatus().equals(statusFilter);

      // Filtros de valor.
      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = budget.getTotalBudgetValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = budget.getTotalBudgetValue() <= valueRangeFilter.getValueMaxFilter();
      }

      // Filtro de data.
      boolean matchesDate = true;
      if (initialDateFilter != null && budget.getInitialDate() != null) {
        matchesDate = !budget.getInitialDate().isBefore(initialDateFilter);
      }
      if (matchesDate && endDateFilter != null && budget.getEndDate() != null) {
        matchesDate = !budget.getEndDate().isAfter(endDateFilter);
      }

      // Checagem das filtragens para adição do orçamento.
      if (matchesQuery && matchesStatus && matchesValue && matchesDate) {
        results.add(budget);
      }
    }

    // Processo de exibição dos orçamentos filtrados.
    displayedBudgets = results;

    showBudgets();
  }

  /**
   * Abre a tela do orçamento selecionado.
   */
  protected void accessButton() {
    if (RecordVerification.verifyRecords(budgetsTable, "acessar")) {
      BudgetModel selectedBudget = displayedBudgets.get(budgetsTable.getSelectedRow());
      new BudgetView(
              budgetDatabaseController,
              budgetItemDatabaseController,
              selectedBudget,
              (value) -> {
                onUpdateBudget(budgetsTable.getSelectedRow(), value);
              }
      ).setVisible(true);
    }
  }

  /**
   * Abre a tela de criação de um novo orçamento.
   */
  protected void addButton() {
    new ManagerBudgetView(
            budgetDatabaseController,
            null,
            this::onBudgetCreated
    ).setVisible(true);
  }

  /**
   * Abre a tela de atualização do orçamento selecionada.
   */
  protected void updateButton() {
    if (RecordVerification.verifyRecords(budgetsTable, "atualizar")) {
      BudgetModel selectedBudget = displayedBudgets.get(budgetsTable.getSelectedRow());
      new ManagerBudgetView(
              budgetDatabaseController,
              selectedBudget,
              (budgetId) -> {
                updateScreen();
              }
      ).setVisible(true);
    }
  }

  /**
   * Remove a despesa selecionada após confirmação.
   */
  protected void deleteButton() {
    ConfirmDeleteDialog.showDeleteConfirmation(
            budgetsTable,
            () -> {
              BudgetModel selectedExpense = displayedBudgets.get(budgetsTable.getSelectedRow());
              budgetDatabaseController.removeById(selectedExpense.getId());

              updateScreen();
            }
    );
  }

  /**
   * Limpa todos os filtros definidos.
   */
  protected void clearFiltersButton() {
    searchField.setText("Pesquisar...");
    searchField.setText("Pesquisar...");
    valueMinFilterField.setText("");
    valueMaxFilterField.setText("");
    statusFilterSelect.setSelectedItem("Todos");
    initialDateFilterField.setText(DateFormatter.PLACEHOLDER_DATE);
    endDateFilterField.setText(DateFormatter.PLACEHOLDER_DATE);

    search();
  }
}

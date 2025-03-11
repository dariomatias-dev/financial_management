package br.edu.ifpb.views.budget;

import br.edu.ifpb.database.controllers.BudgetDatabaseController;
import br.edu.ifpb.database.controllers.BudgetItemDatabaseController;
import br.edu.ifpb.models.budget.BudgetModel;
import br.edu.ifpb.models.budget_item.BudgetItemModel;
import br.edu.ifpb.utils.BudgetCalculator;
import br.edu.ifpb.utils.ConfirmDeleteDialog;
import br.edu.ifpb.utils.CurrencyFormatter;
import br.edu.ifpb.utils.RecordVerification;
import br.edu.ifpb.utils.SearchFieldHandler;
import br.edu.ifpb.utils.SetBackIcon;
import br.edu.ifpb.utils.ValueRangeFilter;
import br.edu.ifpb.utils.WindowClosure;
import br.edu.ifpb.views.budget.components.manager_budget_item.ManagerBudgetItemView;
import java.awt.Window;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BudgetController {
  private final Window parentComponent;
  private final JTextField searchField;
  private final JLabel nameText;
  private final JLabel categoryText;
  private final JLabel descriptionText;
  private final JLabel statusText;
  private final JLabel initialDateText;
  private final JLabel endDateText;
  private final JTable monetaryInfoTable;
  private final JTable budgetItemsTable;
  private final JTextField valueMinFilterField;
  private final JTextField valueMaxFilterField;
  private final JComboBox<String> periodFilterField;

  private BudgetModel budget;
  private final BudgetDatabaseController budgetDatabaseController;
  private final BudgetItemDatabaseController budgetItemDatabaseController;
  private final Consumer<BudgetModel> onUpdateBudget;

  private ArrayList<BudgetItemModel> allBudgetItems;
  private ArrayList<BudgetItemModel> displayedBudgetItems;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   * @param budget Dados do registro selecionado.
   * @param onUpdateBudget Metódo para atualização do orçamento.
   * @param exitButton Botão de sair da tela.
   * @param screenTitle Título da tela de gerenciamento de receitas.
   * @param searchField Campo de pesquisa.
   * @param nameText Nome do orçamento.
   * @param categoryText Categoria do orçamento.
   * @param descriptionText Descriç~ão do orçamento.
   * @param statusText Status do orçamento.
   * @param initialDateText Data inicial do orçamento.
   * @param endDateText Data final do orçamento.
   * @param valueMinFilterField Campo de filtro de valor mínimo.
   * @param valueMaxFilterField Campo de filtro de valor máximo.
   * @param periodFilterField Seletor de filtro de período.
   * @param monetaryInfoTable Tabela de valores do orçamento.
   * @param budgetItemsTable Tabela de itens do orçamento.
   */
  public BudgetController(
          Window parentComponent,
          BudgetDatabaseController budgetDatabaseController,
          BudgetItemDatabaseController budgetItemDatabaseController,
          BudgetModel budget,
          Consumer<BudgetModel> onUpdateBudget,
          JLabel exitButton,
          JLabel screenTitle,
          JTextField searchField,
          JLabel nameText,
          JLabel categoryText,
          JLabel descriptionText,
          JLabel statusText,
          JLabel initialDateText,
          JLabel endDateText,
          JTextField valueMinFilterField,
          JTextField valueMaxFilterField,
          JComboBox<String> periodFilterField,
          JTable monetaryInfoTable,
          JTable budgetItemsTable
  ) {
    this.searchField = searchField;
    this.nameText = nameText;
    this.categoryText = categoryText;
    this.descriptionText = descriptionText;
    this.statusText = statusText;
    this.initialDateText = initialDateText;
    this.endDateText = endDateText;
    this.monetaryInfoTable = monetaryInfoTable;
    this.budgetItemsTable = budgetItemsTable;
    this.valueMinFilterField = valueMinFilterField;
    this.valueMaxFilterField = valueMaxFilterField;
    this.periodFilterField = periodFilterField;

    this.parentComponent = parentComponent;
    this.budget = budget;
    this.budgetDatabaseController = budgetDatabaseController;
    this.budgetItemDatabaseController = budgetItemDatabaseController;
    this.onUpdateBudget = onUpdateBudget;

    showInfos();

    showFinancialSummaryBudget();

    listBudgetItems();

    showBudgetItems();

    screenTitle.setFocusable(true);
    new SearchFieldHandler(searchField).initialize();

    new SetBackIcon().set(exitButton);

    calculateBudgetValue();

    WindowClosure.apply(parentComponent);
  }

  /**
   * Mostra as informações do orçamento.
   */
  private void showInfos() {
    String description = budget.getDescription();
    if (description.length() > 94) {
      description = description.substring(0, 94) + "...";
    }

    nameText.setText(budget.getName());
    categoryText.setText(budget.getCategory());
    descriptionText.setText(description);
    statusText.setText(budget.getStatus());
    initialDateText.setText(budget.getInitialDate().format(DATE_FORMATTER));
    endDateText.setText(budget.getEndDate().format(DATE_FORMATTER));
  }

  /**
   * Obtém todos os itens do orçamento.
   */
  private void listBudgetItems() {
    allBudgetItems = budgetItemDatabaseController.getAllByBudgetId(budget.getId());
    displayedBudgetItems = allBudgetItems;
  }

  /**
   * Exibi os dados dos itens de orçamento repassados.
   */
  private void showBudgetItems() {
    DefaultTableModel tableModel = (DefaultTableModel) budgetItemsTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (BudgetItemModel budgetItem : displayedBudgetItems) {
      String formattedValue = CurrencyFormatter.format(budgetItem.getValue());
      Object[] rowData = new Object[5];

      String createdAtFormatted = budgetItem.getCreatedAt().format(DATE_FORMATTER);

      rowData[0] = budgetItem.getName();
      rowData[1] = budgetItem.getDescription();
      rowData[2] = formattedValue;
      rowData[3] = budgetItem.getPeriod();
      rowData[4] = createdAtFormatted;

      tableModel.addRow(rowData);
    }
  }

  /**
   * Atualiza a lista de itens de orçamento.
   */
  private void updateScreen() {
    listBudgetItems();

    search();
  }

  /**
   * Método chamado quando um item de orçamento é criado.
   */
  private void onBudgetItemCreated() {
    updateScreen();

    calculateBudgetValue();
  }

  /**
   * Método chamado quando um item de orçamento é atualizado.
   */
  private void onBudgetItemUpdated(
          BudgetItemModel budgetItem,
          BudgetItemModel value
  ) {
    updateScreen();

    if (budgetItem.getValue() != value.getValue() || !budgetItem.getPeriod().equals(value.getPeriod())) {
      calculateBudgetValue();
    }
  }

  /**
   * Cálcula o valor total dos itens do orçamento.
   */
  private void calculateBudgetValue() {
    budget = new BudgetCalculator().calculate(budget, budgetDatabaseController, allBudgetItems);

    showFinancialSummaryBudget();

    onUpdateBudget.accept(budget);
  }

  /**
   * Método de pesquisa das despesas.
   */
  protected void search() {
    // Obtenção dos filtros.
    String query = searchField.getText().trim().toLowerCase();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    // Verificação da presença de filtragem.
    if (query.equals("pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedBudgetItems = allBudgetItems;
      showBudgetItems();

      return;
    }

    // Obtenção dos filtros de valor mínimo e máximo.
    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    if (!valueRangeFilter.validate(parentComponent, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    ArrayList<BudgetItemModel> results = new ArrayList<>();

    // Filtragem dos orçamentos.
    for (BudgetItemModel budgetItem : allBudgetItems) {
      // Filtro de texto e período.
      boolean matchesQuery = query.equals("Pesquisar...") || budgetItem.getName().toLowerCase().contains(query) || budgetItem.getDescription().toLowerCase().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || budgetItem.getPeriod().equals(periodFilter);

      // Filtros de valor.
      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = budgetItem.getValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = budgetItem.getValue() <= valueRangeFilter.getValueMaxFilter();
      }

      // Checagem das filtragens.
      if (matchesQuery && matchesPeriod && matchesValue) {
        results.add(budgetItem);
      }
    }

    displayedBudgetItems = results;

    showBudgetItems();
  }

  private void showFinancialSummaryBudget() {
    // Cálculo dos valores
    double totalSpent = budget.getTotalSpent();
    double totalBudget = budget.getTotalBudgetValue();
    double remainingValue = totalBudget - totalSpent;

    DefaultTableModel monetaryModel = (DefaultTableModel) monetaryInfoTable.getModel();
    monetaryModel.setRowCount(0);

    // Adição das informações
    monetaryModel.addRow(new Object[]{
      CurrencyFormatter.format(totalBudget),
      CurrencyFormatter.format(totalSpent),
      CurrencyFormatter.format(remainingValue)
    });
  }

  // SCREEN ACTIONS
  /**
   * Abre a tela de criação de um novo item de orçamento.
   */
  protected void addButton() {
    new ManagerBudgetItemView(
            budget.getId(),
            budgetItemDatabaseController,
            null,
            (value) -> {
              onBudgetItemCreated();
            }
    ).setVisible(true);
  }

  /**
   * Abre a tela de atualização do item de orçamento selecionado.
   */
  protected void updateButton() {
    if (RecordVerification.verifyRecords(budgetItemsTable, "atualizar")) {
      BudgetItemModel selectedBudgetItem = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
      new ManagerBudgetItemView(
              budget.getId(),
              budgetItemDatabaseController,
              selectedBudgetItem,
              (value) -> {
                onBudgetItemUpdated(selectedBudgetItem, value);
              }
      ).setVisible(true);
    }
  }

  /**
   * Remove o item de orçamento selecionado após confirmação.
   */
  protected void deleteButton() {
    ConfirmDeleteDialog.showDeleteConfirmation(
            budgetItemsTable,
            () -> {
              BudgetItemModel selectedExpense = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
              budgetItemDatabaseController.removeById(selectedExpense.getId());

              updateScreen();

              calculateBudgetValue();
            }
    );
  }

  /**
   * Limpa todos os filtros aplicados.
   */
  protected void clearFilters() {
    searchField.setText("Pesquisar...");
    valueMinFilterField.setText("");
    valueMaxFilterField.setText("");
    periodFilterField.setSelectedItem("Todos");

    displayedBudgetItems = allBudgetItems;
    
    showBudgetItems();
  }
}

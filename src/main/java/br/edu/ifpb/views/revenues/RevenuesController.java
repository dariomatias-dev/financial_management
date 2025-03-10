package br.edu.ifpb.views.revenues;

import java.awt.Window;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import br.edu.ifpb.database.controllers.RevenueDatabaseController;
import br.edu.ifpb.models.revenue.RevenueModel;
import br.edu.ifpb.utils.ConfirmDeleteDialog;
import br.edu.ifpb.utils.CurrencyFormatter;
import br.edu.ifpb.utils.RecordVerification;
import br.edu.ifpb.utils.SearchFieldHandler;
import br.edu.ifpb.utils.SetBackIcon;
import br.edu.ifpb.utils.ValueRangeFilter;
import br.edu.ifpb.views.revenues.components.manager_register.ManagerRevenueView;

public class RevenuesController {
  private final Window parentComponent;
  private final Runnable onUpdateMainScreen;
  private final RevenueDatabaseController revenueDatabaseController;
  private final JTextField searchField;
  private final JTextField valueMinFilterField;
  private final JTextField valueMaxFilterField;
  private final JComboBox<String> periodFilterField;
  private final JTable revenuesTable;

  private ArrayList<RevenueModel> allRevenues;
  private ArrayList<RevenueModel> displayedRevenues;

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param onUpdateMainScreen Método para atualização da tela principal.
   * @param revenueDatabaseController Controlador de receitas.
   * @param exitButton Botão de sair da tela.
   * @param screenTitle Título da tela de gerenciamento de receitas.
   * @param searchField Campo de pesquisa.
   * @param valueMinFilterField Campo de filtro de valor mínimo.
   * @param valueMaxFilterField Campo de filtro de valor máximo.
   * @param periodFilterField Seletor de filtro de período.
   * @param revenuesTable Tabela de receitas.
   */
  public RevenuesController(
          Window parentComponent,
          Runnable onUpdateMainScreen,
          RevenueDatabaseController revenueDatabaseController,
          JLabel exitButton,
          JLabel screenTitle,
          JTextField searchField,
          JTextField valueMinFilterField,
          JTextField valueMaxFilterField,
          JComboBox<String> periodFilterField,
          JTable revenuesTable
  ) {
    this.parentComponent = parentComponent;
    this.onUpdateMainScreen = onUpdateMainScreen;
    this.revenueDatabaseController = revenueDatabaseController;
    this.searchField = searchField;
    this.valueMinFilterField = valueMinFilterField;
    this.valueMaxFilterField = valueMaxFilterField;
    this.periodFilterField = periodFilterField;
    this.revenuesTable = revenuesTable;

    screenTitle.setFocusable(true);
    new SearchFieldHandler(searchField).initialize();
    new SetBackIcon().set(exitButton);

    listRevenues();
  }

  /**
   * Obtém e exibe todas as receitas.
   */
  private void listRevenues() {
    allRevenues = revenueDatabaseController.getAll();
    displayedRevenues = allRevenues;

    showRevenues();
  }

  /**
   * Exibi as receitas.
   */
  private void showRevenues() {
    DefaultTableModel tableModel = (DefaultTableModel) revenuesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (RevenueModel expense : displayedRevenues) {
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
   * Atualiza a lista de receitas.
   */
  private void updateScreen() {
    onUpdateMainScreen.run();
    listRevenues();
    search();
  }

  // SCREEN ACTIONS
  /**
   * Filtra as receitas de acordo com os filtros definidos.
   */
  protected void search() {
    // Obtém os filtros de pesquisa.
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    // Verificação da presença de filtragem.
    if (query.equals("Pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedRevenues = allRevenues;
      showRevenues();

      return;
    }

    // Valida o intervalo de valores.
    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    if (!valueRangeFilter.validate(parentComponent, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    ArrayList<RevenueModel> results = new ArrayList<>();

    // Filtragem das receitas.
    for (RevenueModel expense : allRevenues) {
      // Filtro de texto e período.
      boolean matchesQuery = query.equals("Pesquisar...") || expense.getName().contains(query) || expense.getDescription().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || expense.getPeriod().equals(periodFilter);

      // Filtros de valor.
      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = expense.getValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = expense.getValue() <= valueRangeFilter.getValueMaxFilter();
      }

      // Checagem das filtragens para adição da receita.
      if (matchesQuery && matchesPeriod && matchesValue) {
        results.add(expense);
      }
    }

    // Processo de exibição das receitas filtradas.
    displayedRevenues = results;

    showRevenues();
  }

  /**
   * Abre a tela de criação de uma nova receita.
   */
  protected void addButton() {
    new ManagerRevenueView(
            revenueDatabaseController,
            null,
            this::updateScreen
    ).setVisible(true);
  }

  /**
   * Abre a tela de atualização da receita selecionada.
   */
  protected void updateButton() {
    if (RecordVerification.verifyRecords(revenuesTable, "atualizar")) {
      RevenueModel selectedRevenue = displayedRevenues.get(revenuesTable.getSelectedRow());
      new ManagerRevenueView(
              revenueDatabaseController,
              selectedRevenue,
              this::updateScreen
      ).setVisible(true);
    }
  }

  /**
   * Remove a despesa selecionada após confirmação.
   */
  protected void deleteButton() {
    ConfirmDeleteDialog.showDeleteConfirmation(
            revenuesTable,
            () -> {
              RevenueModel selectedRevenue = displayedRevenues.get(revenuesTable.getSelectedRow());
              revenueDatabaseController.removeById(selectedRevenue.getId());
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

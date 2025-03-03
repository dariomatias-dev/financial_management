package org.cafe.views.budget;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.cafe.database.controllers.BudgetController;
import org.cafe.database.controllers.BudgetItemController;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget_item.BudgetItemModel;
import org.cafe.utils.BudgetCalculator;
import org.cafe.utils.ConfirmDeleteDialog;
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.utils.RecordVerificationUtil;
import org.cafe.utils.SearchFieldHandlerUtil;
import org.cafe.utils.SetBackIcon;
import org.cafe.utils.ValueRangeFilter;
import org.cafe.views.budget.components.manager_budget_item.ManagerBudgetItemView;

public class BudgetView extends javax.swing.JFrame {
  private BudgetModel budget;
  private final BudgetController budgetController;
  private final BudgetItemController budgetItemController;
  private ArrayList<BudgetItemModel> allBudgetItems;
  private ArrayList<BudgetItemModel> displayedBudgetItems;
  private final Consumer<BudgetModel> onUpdateBudget;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  /**
   * Construtor.
   *
   * @param budgetController Controlador de orçamentos.
   * @param budgetItemController Controlador de itens de orçamento.
   * @param budget Dados do registro selecionado.
   * @param onUpdateBudget Metódo para atualização do orçamento.
   */
  public BudgetView(
          BudgetController budgetController,
          BudgetItemController budgetItemController,
          BudgetModel budget,
          Consumer<BudgetModel> onUpdateBudget
  ) {
    this.budget = budget;
    this.budgetController = budgetController;
    this.budgetItemController = budgetItemController;
    this.onUpdateBudget = onUpdateBudget;

    initComponents();

    initializeSearchField();

    showInfos();

    showFinancialSummaryBudget();

    listBudgetItems();

    showBudgetItems();

    new SetBackIcon().set(exitButton);
  }

  private void initializeSearchField() {
    labelInvisible.setFocusable(true);

    new SearchFieldHandlerUtil(searchField).initialize();
  }

  /**
   * Mostra as informações do orçamento.
   */
  private void showInfos() {
    nameText.setText("Nome: " + budget.getName());
    categoryText.setText("Categoria: " + budget.getCategory());
    descriptionText.setText("Descrição: " + budget.getDescription());
    statusText.setText("Status: " + budget.getStatus());
    initialDateText.setText("Data Inicial: " + budget.getInitialDate().format(DATE_FORMATTER));
    endDateText.setText("Data Final: " + budget.getEndDate().format(DATE_FORMATTER));
  }

  /**
   * Obtém todos os itens do orçamento.
   */
  private void listBudgetItems() {
    allBudgetItems = budgetItemController.getAllByBudgetId(budget.getId());
    displayedBudgetItems = allBudgetItems;
  }

  /**
   * Exibi os dados dos itens de orçamento repassados.
   */
  private void showBudgetItems() {
    DefaultTableModel tableModel = (DefaultTableModel) budgetItemsTable.getModel();
    tableModel.setRowCount(0);

    for (BudgetItemModel budgetItem : displayedBudgetItems) {
      String formattedValue = CurrencyFormatterUtil.format(budgetItem.getValue());
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
    budget = new BudgetCalculator().calculate(budget, budgetController, allBudgetItems);

    if (budget.getTotalSpent() > budget.getTotalBudgetValue()) {
      JOptionPane.showMessageDialog(null, "Aviso: O valor dos itens do orçamento ultrapassou o orçamento total!", "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    showFinancialSummaryBudget();

    onUpdateBudget.accept(budget);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    background = new javax.swing.JPanel();
    deleteButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    descriptionText = new javax.swing.JLabel();
    categoryText = new javax.swing.JLabel();
    statusText = new javax.swing.JLabel();
    initialDateText = new javax.swing.JLabel();
    endDateText = new javax.swing.JLabel();
    itemsText = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    searchField = new javax.swing.JTextField();
    searchButton = new javax.swing.JButton();
    labelInvisible = new javax.swing.JLabel();
    periodFilterLabel = new javax.swing.JLabel();
    periodFilterField = new javax.swing.JComboBox<>();
    valueMinFilterLabel = new javax.swing.JLabel();
    valueMinFilterField = new javax.swing.JTextField();
    valueMaxFilterLabel = new javax.swing.JLabel();
    valueMaxFilterField = new javax.swing.JTextField();
    separator = new javax.swing.JSeparator();
    jScrollPane2 = new javax.swing.JScrollPane();
    budgetItemsTable = new javax.swing.JTable();
    exitButton = new javax.swing.JLabel();
    screenTitle = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    monetaryInfoTable = new javax.swing.JTable();
    jLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    background.setBackground(new java.awt.Color(255, 255, 255));

    deleteButton.setForeground(new java.awt.Color(255, 0, 51));
    deleteButton.setText("Excluir");
    deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        deleteButtonMouseClicked(evt);
      }
    });

    updateButton.setText("Atualizar");
    updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        updateButtonMouseClicked(evt);
      }
    });

    addButton.setText("Adicionar");
    addButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        addButtonMouseClicked(evt);
      }
    });

    descriptionText.setText("Descrição");

    categoryText.setText("Categoria");

    statusText.setText("Status");

    initialDateText.setText("Data Inicial");

    endDateText.setText("Data Final");

    itemsText.setText("Itens:");

    nameText.setText("Nome");

    searchButton.setText("Filtrar");
    searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        searchButtonMouseClicked(evt);
      }
    });

    periodFilterLabel.setText("Período:");

    periodFilterField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Diário", "Semanal", "Mensal" }));

    valueMinFilterLabel.setText("Valor Mínimo:");

    valueMaxFilterLabel.setText("Valor Máximo:");

    separator.setForeground(new java.awt.Color(60, 63, 65));

    budgetItemsTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Nome", "Descrição", "Valor", "Período", "Criado em"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane2.setViewportView(budgetItemsTable);
    if (budgetItemsTable.getColumnModel().getColumnCount() > 0) {
      budgetItemsTable.getColumnModel().getColumn(0).setMinWidth(120);
      budgetItemsTable.getColumnModel().getColumn(0).setPreferredWidth(120);
      budgetItemsTable.getColumnModel().getColumn(0).setMaxWidth(120);
      budgetItemsTable.getColumnModel().getColumn(2).setMinWidth(88);
      budgetItemsTable.getColumnModel().getColumn(2).setPreferredWidth(88);
      budgetItemsTable.getColumnModel().getColumn(2).setMaxWidth(88);
      budgetItemsTable.getColumnModel().getColumn(3).setMinWidth(60);
      budgetItemsTable.getColumnModel().getColumn(3).setPreferredWidth(60);
      budgetItemsTable.getColumnModel().getColumn(3).setMaxWidth(60);
      budgetItemsTable.getColumnModel().getColumn(4).setMinWidth(68);
      budgetItemsTable.getColumnModel().getColumn(4).setPreferredWidth(68);
      budgetItemsTable.getColumnModel().getColumn(4).setMaxWidth(68);
    }

    exitButton.setForeground(new java.awt.Color(255, 0, 51));
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Orçamento");

    monetaryInfoTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Valor do Orçamento", "Valor Total dos Itens", "Valor Restante"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane1.setViewportView(monetaryInfoTable);

    jLabel1.setText("Resumo Financeiro do Orçamento:");

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(separator)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(descriptionText)
              .addComponent(nameText))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelInvisible)
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(categoryText)
              .addComponent(initialDateText))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(endDateText)
              .addComponent(statusText))
            .addGap(188, 188, 188))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
              .addComponent(jScrollPane2)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton))
              .addGroup(javax.swing.GroupLayout.Alignment.LEADING, backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(backgroundLayout.createSequentialGroup()
                    .addComponent(periodFilterLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addGroup(backgroundLayout.createSequentialGroup()
                    .addComponent(valueMinFilterLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(valueMaxFilterLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(searchField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton))
              .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(jLabel1)
                  .addGroup(backgroundLayout.createSequentialGroup()
                    .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(screenTitle)))
                .addGap(0, 0, Short.MAX_VALUE)))
            .addContainerGap())
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(itemsText)
            .addGap(0, 0, Short.MAX_VALUE))))
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGap(19, 19, 19)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(screenTitle))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(labelInvisible)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(nameText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(descriptionText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(categoryText)
              .addComponent(statusText))))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(initialDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(endDateText))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel1)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(itemsText)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(searchButton))
        .addGap(6, 6, 6)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(valueMinFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(periodFilterLabel)
          .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addGap(3, 3, 3)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deleteButton)
          .addComponent(updateButton)
          .addComponent(addButton))
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Abre a tela de criação de item de orçamento.
   */
  private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
    new ManagerBudgetItemView(
            budget.getId(),
            budgetItemController,
            null,
            (value) -> {
              onBudgetItemCreated();
            }
    ).setVisible(true);
  }//GEN-LAST:event_addButtonMouseClicked

  /**
   * Abre a tela de atualização do item de orçamento selecionado.
   */
  private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
    if (RecordVerificationUtil.verifyRecords(budgetItemsTable, "atualizar")) {
      BudgetItemModel selectedBudgetItem = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
      new ManagerBudgetItemView(
              budget.getId(),
              budgetItemController,
              selectedBudgetItem,
              (value) -> {
                onBudgetItemUpdated(selectedBudgetItem, value);
              }
      ).setVisible(true);
    }
  }//GEN-LAST:event_updateButtonMouseClicked

  /**
   * Remove o item de orçamento selecionado.
   */
  private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
    ConfirmDeleteDialog.showDeleteConfirmation(
            budgetItemsTable,
            () -> {
              BudgetItemModel selectedExpense = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
              budgetItemController.removeById(selectedExpense.getId());

              updateScreen();

              calculateBudgetValue();
            }
    );
  }//GEN-LAST:event_deleteButtonMouseClicked

  /**
   * Método de pesquisa das despesas.
   */
  private void search() {
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    if (query.equals("Pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedBudgetItems = allBudgetItems;
      showBudgetItems();

      return;
    }

    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    
    if (!valueRangeFilter.validate(this, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    ArrayList<BudgetItemModel> results = new ArrayList<>();

    for (BudgetItemModel budgetItem : allBudgetItems) {
      boolean matchesQuery = query.equals("Pesquisar...") || budgetItem.getName().contains(query) || budgetItem.getDescription().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || budgetItem.getPeriod().equals(periodFilter);

      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = budgetItem.getValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = budgetItem.getValue() <= valueRangeFilter.getValueMaxFilter();
      }

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
    monetaryModel.addRow(new Object[]{CurrencyFormatterUtil.format(totalBudget), CurrencyFormatterUtil.format(totalSpent), CurrencyFormatterUtil.format(remainingValue)});
  }

  /**
   * Método chamado para filtrar os itens do orçamento de acordo com os filtros
   * definidos.
   */
  private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
    search();
  }//GEN-LAST:event_searchButtonMouseClicked

  /**
   * Método chamado para sair da tela.
   */
  private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
    this.dispose();
  }//GEN-LAST:event_exitButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel background;
  private javax.swing.JTable budgetItemsTable;
  private javax.swing.JLabel categoryText;
  private javax.swing.JButton deleteButton;
  private javax.swing.JLabel descriptionText;
  private javax.swing.JLabel endDateText;
  private javax.swing.JLabel exitButton;
  private javax.swing.JLabel initialDateText;
  private javax.swing.JLabel itemsText;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel labelInvisible;
  private javax.swing.JTable monetaryInfoTable;
  private javax.swing.JLabel nameText;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JLabel periodFilterLabel;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JSeparator separator;
  private javax.swing.JLabel statusText;
  private javax.swing.JButton updateButton;
  private javax.swing.JTextField valueMaxFilterField;
  private javax.swing.JLabel valueMaxFilterLabel;
  private javax.swing.JTextField valueMinFilterField;
  private javax.swing.JLabel valueMinFilterLabel;
  // End of variables declaration//GEN-END:variables
}

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
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.utils.RecordVerificationUtil;
import org.cafe.utils.SearchFieldHandlerUtil;
import org.cafe.views.budget.components.manager_budget_item.ManagerBudgetItemView;

public class BudgetView extends javax.swing.JFrame {
  private BudgetModel budget;
  private final BudgetController budgetController;
  private final BudgetItemController budgetItemController;
  private ArrayList<BudgetItemModel> allBudgetItems;
  private ArrayList<BudgetItemModel> displayedBudgetItems;
  private final Consumer<BudgetModel> onUpdateBudget;

  /**
   * Construtor.
   *
   * @param budgetController Controlador de de orçamentos.
   * @param budgetItemController Controlador de itens de orçamento.
   * @param budget Dados do registro selecionado. orçamentos.
   * @param onUpdateBudget Função para atualização do orçamento.
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

    listBudgetItems();

    showBudgetItems();
    
    exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back_icon.png")));
  }

  private void initializeSearchField() {
    labelInvisible.setFocusable(true);

    new SearchFieldHandlerUtil(searchField).initialize();
  }

  /**
   * Mostra as informações do orçamento.
   */
  private void showInfos() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    nameText.setText("Nome: " + budget.getName());
    categoryText.setText("Categoria: " + budget.getCategory());
    descriptionText.setText("Descrição: " + budget.getDescription());
    statusText.setText("Status: " + budget.getStatus());
    totalSpentText.setText("Total dos Itens: R$ " + budget.getTotalSpent());
    totalBudgetValueText.setText("Valor do Orçamento: R$ " + budget.getTotalBudgetValue());
    initialDateText.setText("Data Inicial: " + budget.getInitialDate().format(formatter));
    endDateText.setText("Data Final: " + budget.getEndDate().format(formatter));
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
      Object[] rowData = new Object[4];

      rowData[0] = budgetItem.getName();
      rowData[1] = budgetItem.getDescription();
      rowData[2] = formattedValue;
      rowData[3] = budgetItem.getPeriod();

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

    totalSpentText.setText("Valor: R$ " + budget.getTotalSpent());

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
    totalBudgetValueText = new javax.swing.JLabel();
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
    totalSpentText = new javax.swing.JLabel();
    separator = new javax.swing.JSeparator();
    jScrollPane2 = new javax.swing.JScrollPane();
    budgetItemsTable = new javax.swing.JTable();
    exitButton = new javax.swing.JLabel();
    screenTitle = new javax.swing.JLabel();

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

    totalBudgetValueText.setText("Valor do Orçamento");

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

    totalSpentText.setText("Total dos Itens:");

    separator.setForeground(new java.awt.Color(60, 63, 65));

    budgetItemsTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Nome", "Descrição", "Valor", "Período"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false
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

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(screenTitle)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jScrollPane2)
              .addComponent(separator)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(descriptionText)
                  .addComponent(categoryText)
                  .addComponent(statusText)
                  .addComponent(nameText))
                .addGap(525, 525, 525))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(searchField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(valueMinFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(valueMaxFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(backgroundLayout.createSequentialGroup()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(totalSpentText)
                  .addComponent(totalBudgetValueText)
                  .addComponent(initialDateText)
                  .addComponent(endDateText)
                  .addComponent(itemsText)
                  .addGroup(backgroundLayout.createSequentialGroup()
                    .addComponent(periodFilterLabel)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE)))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelInvisible)))
        .addContainerGap())
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
            .addComponent(categoryText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(statusText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(totalSpentText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(totalBudgetValueText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(initialDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(endDateText)
            .addGap(24, 24, 24)
            .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(itemsText)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
              .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(searchButton))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
              .addComponent(addButton))))
        .addContainerGap(9, Short.MAX_VALUE))
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
    ManagerBudgetItemView createManagerBudgetItemView = new ManagerBudgetItemView(
            budget.getId(),
            budgetItemController,
            null,
            (value) -> {
              onBudgetItemCreated();
            }
    );

    createManagerBudgetItemView.setVisible(true);
  }//GEN-LAST:event_addButtonMouseClicked

  /**
   * Abre a tela de atualização do item de orçamento selecionado.
   */
  private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
    if (RecordVerificationUtil.verifyRecords(budgetItemsTable, "atualizar")) {
      BudgetItemModel selectedBudgetItem = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
      ManagerBudgetItemView updateManagerBudgetItemView = new ManagerBudgetItemView(
              budget.getId(),
              budgetItemController,
              selectedBudgetItem,
              (value) -> {
                onBudgetItemUpdated(selectedBudgetItem, value);
              }
      );

      updateManagerBudgetItemView.setVisible(true);
    }
  }//GEN-LAST:event_updateButtonMouseClicked

  /**
   * Remove o item de orçamento selecionado.
   */
  private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
    if (RecordVerificationUtil.verifyRecords(budgetItemsTable, "excluir")) {
      // Confirmar remoção de registro.
      int confirm = JOptionPane.showConfirmDialog(
              null,
              "Você realmente deseja excluir este registro?",
              "Confirmar Exclusão",
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE
      );

      // Se o usuário confirmar, exclui o registro.
      if (confirm == JOptionPane.YES_OPTION) {
        BudgetItemModel selectedExpense = displayedBudgetItems.get(budgetItemsTable.getSelectedRow());
        budgetItemController.removeById(selectedExpense.getId());

        updateScreen();

        calculateBudgetValue();
      }
    }
  }//GEN-LAST:event_deleteButtonMouseClicked

  /**
   * Método de pesquisa/filtragem dos itens do orçamento.
   */
  private void search() {
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    if (query.equals("Pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedBudgetItems = allBudgetItems;
      showBudgetItems();
    } else {
      double valueMinFilter = Double.MIN_VALUE;
      double valueMaxFilter = Double.MAX_VALUE;
      boolean applyValueMinFilter = false;
      boolean applyValueMaxFilter = false;

      try {
        if (!valueMinFilterText.isEmpty()) {
          valueMinFilter = Double.parseDouble(valueMinFilterText);
          applyValueMinFilter = true;
        }
        if (!valueMaxFilterText.isEmpty()) {
          valueMaxFilter = Double.parseDouble(valueMaxFilterText);
          applyValueMaxFilter = true;
        }
        if (applyValueMinFilter && applyValueMaxFilter && valueMinFilter > valueMaxFilter) {
          JOptionPane.showMessageDialog(this, "O valor mínimo não pode ser maior que o valor máximo.", "Erro", JOptionPane.ERROR_MESSAGE);
          return;
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos nos campos de valor.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      ArrayList<BudgetItemModel> results = new ArrayList<>();

      for (BudgetItemModel budgetItem : allBudgetItems) {
        boolean matchesQuery = query.equals("Pesquisar...") || budgetItem.getName().contains(query) || budgetItem.getDescription().contains(query);
        boolean matchesPeriod = periodFilter.equals("Todos") || budgetItem.getPeriod().equals(periodFilter);

        boolean matchesValue = true;
        if (applyValueMinFilter) {
          matchesValue = budgetItem.getValue() >= valueMinFilter;
        }
        if (applyValueMaxFilter && matchesValue) {
          matchesValue = budgetItem.getValue() <= valueMaxFilter;
        }

        if (matchesQuery && matchesPeriod && matchesValue) {
          results.add(budgetItem);
        }
      }

      displayedBudgetItems = results;

      showBudgetItems();
    }
  }

  /**
   * Método chamado para filtrar os itens do orçamento de acordo com as
   * filtragens definidas.
   */
  private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
    search();
  }//GEN-LAST:event_searchButtonMouseClicked

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
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel labelInvisible;
  private javax.swing.JLabel nameText;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JLabel periodFilterLabel;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JSeparator separator;
  private javax.swing.JLabel statusText;
  private javax.swing.JLabel totalBudgetValueText;
  private javax.swing.JLabel totalSpentText;
  private javax.swing.JButton updateButton;
  private javax.swing.JTextField valueMaxFilterField;
  private javax.swing.JLabel valueMaxFilterLabel;
  private javax.swing.JTextField valueMinFilterField;
  private javax.swing.JLabel valueMinFilterLabel;
  // End of variables declaration//GEN-END:variables
}

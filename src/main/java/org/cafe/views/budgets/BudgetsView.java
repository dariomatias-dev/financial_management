package org.cafe.views.budgets;

import org.cafe.database.controllers.BudgetDatabaseController;
import org.cafe.database.controllers.BudgetItemDatabaseController;

public class BudgetsView extends javax.swing.JFrame {
  private final BudgetsController controller;

  /**
   * Construtor.
   *
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   */
  public BudgetsView(
          BudgetDatabaseController budgetDatabaseController,
          BudgetItemDatabaseController budgetItemDatabaseController
  ) {
    initComponents();

    this.controller = new BudgetsController(
            this,
            budgetDatabaseController,
            budgetItemDatabaseController,
            exitButton,
            screenTitle,
            searchField,
            valueMinFilterField,
            valueMaxFilterField,
            statusFilterSelect,
            initialDateFilterField,
            endDateFilterField,
            budgetsTable
    );
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
    accessButton = new javax.swing.JButton();
    searchField = new javax.swing.JTextField();
    clearFiltersButton = new javax.swing.JButton();
    statusFilterSelect = new javax.swing.JComboBox<>();
    valueMinFilterLabel = new javax.swing.JLabel();
    valueMinFilterField = new javax.swing.JTextField();
    valueMaxFilterLabel = new javax.swing.JLabel();
    valueMaxFilterField = new javax.swing.JTextField();
    initialDateFilterLabel = new javax.swing.JLabel();
    statusFilterLabel = new javax.swing.JLabel();
    initialDateFilterField = new javax.swing.JFormattedTextField();
    endDateFilterField = new javax.swing.JFormattedTextField();
    jScrollPane2 = new javax.swing.JScrollPane();
    budgetsTable = new javax.swing.JTable();
    endDateFilterLabel1 = new javax.swing.JLabel();
    exitButton = new javax.swing.JLabel();
    screenTitle = new javax.swing.JLabel();
    searchButton = new javax.swing.JButton();

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

    accessButton.setText("Acessar");
    accessButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        accessButtonMouseClicked(evt);
      }
    });

    clearFiltersButton.setText("Limpar Filtros");
    clearFiltersButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        clearFiltersButtonMouseClicked(evt);
      }
    });

    statusFilterSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Rascunho", "Finalizado", "Negado", "Aprovado" }));

    valueMinFilterLabel.setText("Valor Mínimo:");

    valueMaxFilterLabel.setText("Valor Máximo:");

    initialDateFilterLabel.setText("Data Inicial:");

    statusFilterLabel.setText("Status:");

    budgetsTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null, null, null},
        {null, null, null, null, null, null},
        {null, null, null, null, null, null},
        {null, null, null, null, null, null}
      },
      new String [] {
        "Nome", "Categoria", "Valor", "Status", "Data Inicial", "Data Final"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane2.setViewportView(budgetsTable);
    if (budgetsTable.getColumnModel().getColumnCount() > 0) {
      budgetsTable.getColumnModel().getColumn(2).setMinWidth(88);
      budgetsTable.getColumnModel().getColumn(2).setPreferredWidth(88);
      budgetsTable.getColumnModel().getColumn(2).setMaxWidth(88);
      budgetsTable.getColumnModel().getColumn(3).setMinWidth(88);
      budgetsTable.getColumnModel().getColumn(3).setPreferredWidth(88);
      budgetsTable.getColumnModel().getColumn(3).setMaxWidth(88);
      budgetsTable.getColumnModel().getColumn(4).setMinWidth(68);
      budgetsTable.getColumnModel().getColumn(4).setPreferredWidth(68);
      budgetsTable.getColumnModel().getColumn(4).setMaxWidth(68);
      budgetsTable.getColumnModel().getColumn(5).setMinWidth(68);
      budgetsTable.getColumnModel().getColumn(5).setPreferredWidth(68);
      budgetsTable.getColumnModel().getColumn(5).setMaxWidth(68);
    }

    endDateFilterLabel1.setText("Data Final:");

    exitButton.setForeground(new java.awt.Color(255, 0, 51));
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Orçamentos");

    searchButton.setText("Filtrar");
    searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        searchButtonMouseClicked(evt);
      }
    });

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane2)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(valueMinFilterLabel)
              .addComponent(initialDateFilterLabel))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(initialDateFilterField, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
              .addComponent(valueMinFilterField))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(valueMaxFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                .addComponent(endDateFilterLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(endDateFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(statusFilterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusFilterSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(screenTitle)))
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(accessButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(addButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(updateButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addComponent(searchField)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(searchButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(clearFiltersButton)))
        .addContainerGap())
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGap(16, 16, 16)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(screenTitle))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(clearFiltersButton)
          .addComponent(searchButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(statusFilterSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(statusFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(valueMinFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(initialDateFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(initialDateFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(endDateFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(endDateFilterLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deleteButton)
          .addComponent(updateButton)
          .addComponent(accessButton)
          .addComponent(addButton))
        .addContainerGap())
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Remove o orçamento selecionado.
   */
  private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
    controller.deleteButton();
  }//GEN-LAST:event_deleteButtonMouseClicked

  /**
   * Abre a tela de atualização do orçamento selecionado.
   */
  private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
    controller.updateButton();
  }//GEN-LAST:event_updateButtonMouseClicked

  /**
   * Abre a tela de criação de orçamento.
   */
  private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
    controller.addButton();
  }//GEN-LAST:event_addButtonMouseClicked

  /**
   * Abre a tela do orçamento selecionado.
   */
  private void accessButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accessButtonMouseClicked
    controller.accessButton();
  }//GEN-LAST:event_accessButtonMouseClicked

  /**
   * Remove todos os filtros.
   */
  private void clearFiltersButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearFiltersButtonMouseClicked
    controller.clearFiltersButton();
  }//GEN-LAST:event_clearFiltersButtonMouseClicked

  /**
   * Fecha a tela.
   */
  private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
    this.dispose();
  }//GEN-LAST:event_exitButtonMouseClicked

  /**
   * Filtra os orçamentos de acordo com os filtros definidos.
   */
  private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
    controller.search();
  }//GEN-LAST:event_searchButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton accessButton;
  private javax.swing.JButton addButton;
  private javax.swing.JPanel background;
  private javax.swing.JTable budgetsTable;
  private javax.swing.JButton clearFiltersButton;
  private javax.swing.JButton deleteButton;
  private javax.swing.JFormattedTextField endDateFilterField;
  private javax.swing.JLabel endDateFilterLabel1;
  private javax.swing.JLabel exitButton;
  private javax.swing.JFormattedTextField initialDateFilterField;
  private javax.swing.JLabel initialDateFilterLabel;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JLabel statusFilterLabel;
  private javax.swing.JComboBox<String> statusFilterSelect;
  private javax.swing.JButton updateButton;
  private javax.swing.JTextField valueMaxFilterField;
  private javax.swing.JLabel valueMaxFilterLabel;
  private javax.swing.JTextField valueMinFilterField;
  private javax.swing.JLabel valueMinFilterLabel;
  // End of variables declaration//GEN-END:variables
}

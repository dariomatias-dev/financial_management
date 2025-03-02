package org.cafe.views.expenses;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.utils.ConfirmDeleteDialog;
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.utils.RecordVerificationUtil;
import org.cafe.utils.SearchFieldHandlerUtil;
import org.cafe.views.expenses.components.manager_expense.ManagerExpenseView;

public class ExpensesView extends javax.swing.JFrame {
  private final ExpenseController expenseController;
  private ArrayList<ExpenseModel> allExpenses;
  private ArrayList<ExpenseModel> displayedExpenses;

  /**
   * Construtor.
   *
   * @param expenseController Controlador de despesas.
   */
  public ExpensesView(ExpenseController expenseController) {
    this.expenseController = expenseController;

    initComponents();

    initializeSearchField();

    listExpenses();

    exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back_icon.png")));
  }

  private void initializeSearchField() {
    screenTitle.setFocusable(true);

    new SearchFieldHandlerUtil(searchField).initialize();
  }

  /**
   * Obtém todas as despesas.
   */
  private void listExpenses() {
    allExpenses = expenseController.getAll();
    displayedExpenses = allExpenses;

    showExpenses();
  }

  /**
   * Mostra as informações das despesas.
   */
  private void showExpenses() {
    DefaultTableModel tableModel = (DefaultTableModel) expensesTable.getModel();
    tableModel.setRowCount(0);

    for (ExpenseModel expense : displayedExpenses) {
      String formattedValue = CurrencyFormatterUtil.format(expense.getValue());

      Object[] rowData = new Object[4];
      rowData[0] = expense.getName();
      rowData[1] = expense.getDescription();
      rowData[2] = formattedValue;
      rowData[3] = expense.getPeriod();
      tableModel.addRow(rowData);
    }
  }

  /**
   * Atualiza a lista de despesas.
   */
  private void updateScreen() {
    listExpenses();

    search();
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
    screenTitle = new javax.swing.JLabel();
    exitButton = new javax.swing.JLabel();
    deleteButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    searchField = new javax.swing.JTextField();
    searchButton = new javax.swing.JButton();
    periodLabel = new javax.swing.JLabel();
    periodFilterField = new javax.swing.JComboBox<>();
    valueFilterLabel = new javax.swing.JLabel();
    valueMinFilterLabel = new javax.swing.JLabel();
    valueMaxFilterLabel = new javax.swing.JLabel();
    valueMinFilterField = new javax.swing.JTextField();
    valueMaxFilterField = new javax.swing.JTextField();
    jScrollPane1 = new javax.swing.JScrollPane();
    expensesTable = new javax.swing.JTable();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    background.setBackground(new java.awt.Color(255, 255, 255));

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Despesas");

    exitButton.setForeground(new java.awt.Color(255, 0, 51));
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

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

    searchButton.setText("Filtrar");
    searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        searchButtonMouseClicked(evt);
      }
    });

    periodLabel.setText("Período:");

    periodFilterField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Diário", "Semanal", "Mensal" }));

    valueFilterLabel.setText("Valor:");

    valueMinFilterLabel.setText("Mínimo:");

    valueMaxFilterLabel.setText("Máximo");

    expensesTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Nome", "Descrição", "Valor", "Período"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane1.setViewportView(expensesTable);
    if (expensesTable.getColumnModel().getColumnCount() > 0) {
      expensesTable.getColumnModel().getColumn(0).setMinWidth(120);
      expensesTable.getColumnModel().getColumn(0).setPreferredWidth(120);
      expensesTable.getColumnModel().getColumn(0).setMaxWidth(120);
      expensesTable.getColumnModel().getColumn(2).setMinWidth(88);
      expensesTable.getColumnModel().getColumn(2).setPreferredWidth(88);
      expensesTable.getColumnModel().getColumn(2).setMaxWidth(88);
      expensesTable.getColumnModel().getColumn(3).setMinWidth(60);
      expensesTable.getColumnModel().getColumn(3).setPreferredWidth(60);
      expensesTable.getColumnModel().getColumn(3).setMaxWidth(60);
    }

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(searchField)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(searchButton))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addComponent(valueMinFilterLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(valueMaxFilterLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(addButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(updateButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(screenTitle))
              .addComponent(valueFilterLabel)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(periodLabel)
                .addGap(18, 18, 18)
                .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGap(0, 0, Short.MAX_VALUE)))
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
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(searchButton))
        .addGap(1, 1, 1)
        .addComponent(valueFilterLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(valueMinFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(periodLabel)
          .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
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
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

  /**
   * Remove a despesa selecionada.
   */
    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
      ConfirmDeleteDialog.showDeleteConfirmation(
              expensesTable,
              () -> {
                ExpenseModel selectedExpense = displayedExpenses.get(expensesTable.getSelectedRow());
                expenseController.removeById(selectedExpense.getId());

                updateScreen();
              }
      );
    }//GEN-LAST:event_deleteButtonMouseClicked

  /**
   * Abre a tela de criação de uma despesa.
   */
    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
      ManagerExpenseView createManagerRegisterView = new ManagerExpenseView(expenseController, null, this::updateScreen);

      createManagerRegisterView.setVisible(true);
    }//GEN-LAST:event_addButtonMouseClicked

  /**
   * Abre a tela de atualização da despesa selecionada.
   */
    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
      if (RecordVerificationUtil.verifyRecords(expensesTable, "atualizar")) {
        ExpenseModel selectedExpense = displayedExpenses.get(expensesTable.getSelectedRow());
        ManagerExpenseView updateManagerRegisterView = new ManagerExpenseView(expenseController, selectedExpense, this::updateScreen);

        updateManagerRegisterView.setVisible(true);
      }
    }//GEN-LAST:event_updateButtonMouseClicked

  /**
   * Método de pesquisa/filtragem das despesas.
   */
  private void search() {
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    if (query.equals("Pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedExpenses = allExpenses;
      showExpenses();

      return;
    }

    double valueMinFilter = Double.MIN_VALUE;
    double valueMaxFilter = Double.MAX_VALUE;

    try {
      if (!valueMinFilterText.isEmpty()) {
        valueMinFilter = Double.parseDouble(valueMinFilterText);
      }
      if (!valueMaxFilterText.isEmpty()) {
        valueMaxFilter = Double.parseDouble(valueMaxFilterText);
      }
      if (valueMinFilter > valueMaxFilter) {
        JOptionPane.showMessageDialog(this, "O valor mínimo não pode ser maior que o valor máximo.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(this, "Por favor, insira valores numéricos válidos nos campos de valor.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    ArrayList<ExpenseModel> results = new ArrayList<>();

    for (ExpenseModel expense : allExpenses) {
      boolean matchesQuery = query.equals("Pesquisar...") || expense.getName().contains(query) || expense.getDescription().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || expense.getPeriod().equals(periodFilter);
      boolean matchesValue = expense.getValue() >= valueMinFilter && expense.getValue() <= valueMaxFilter;

      if (matchesQuery && matchesPeriod && matchesValue) {
        results.add(expense);
      }
    }

    displayedExpenses = results;

    showExpenses();
  }

  /**
   * Método chamado para filtrar as despesas de acordo com as filtragens
   * definidas.
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
  private javax.swing.JButton deleteButton;
  private javax.swing.JLabel exitButton;
  private javax.swing.JTable expensesTable;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JLabel periodLabel;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JButton updateButton;
  private javax.swing.JLabel valueFilterLabel;
  private javax.swing.JTextField valueMaxFilterField;
  private javax.swing.JLabel valueMaxFilterLabel;
  private javax.swing.JTextField valueMinFilterField;
  private javax.swing.JLabel valueMinFilterLabel;
  // End of variables declaration//GEN-END:variables
}

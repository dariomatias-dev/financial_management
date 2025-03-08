package org.cafe.views.financial_overview;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.database.controllers.RevenueController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.models.revenue.RevenueModel;
import org.cafe.utils.CurrencyFormatter;
import org.cafe.utils.SetBackIcon;

public class FinancialOverviewView extends javax.swing.JFrame {
  private final RevenueController revenueController;
  private final ExpenseController expenseController;

  /**
   * Construtor.
   *
   * @param revenueController Controlador de receitas.
   * @param expenseController Controlador de despesas.
   */
  public FinancialOverviewView(RevenueController revenueController, ExpenseController expenseController) {
    this.revenueController = revenueController;
    this.expenseController = expenseController;

    initComponents();

    filterRegisters();

    screenTitle.setFocusable(true);

    new SetBackIcon().set(exitButton);
  }

  // Filtra as receitas e despesas com base no período selecionado.
  private void filterRegisters() {
    ArrayList<RevenueModel> filteredRevenues = filterRegistersByPeriod(revenueController.getAll());
    ArrayList<ExpenseModel> filteredExpenses = filterRegistersByPeriod(expenseController.getAll());

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
   * Mostra as informações das receitas.
   */
  private void showRevenues(ArrayList<RevenueModel> revenues) {
    DefaultTableModel tableModel = (DefaultTableModel) revenuesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela de receitas.
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

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    background = new javax.swing.JPanel();
    exitButton = new javax.swing.JLabel();
    screenTitle = new javax.swing.JLabel();
    selectPeriodLabel = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    expensesTable = new javax.swing.JTable();
    jScrollPane2 = new javax.swing.JScrollPane();
    revenuesTable = new javax.swing.JTable();
    revenuesTitle = new javax.swing.JLabel();
    totalValueRevenues = new javax.swing.JLabel();
    selectedRevenues = new javax.swing.JLabel();
    calculateButton = new javax.swing.JButton();
    selectedExpenses = new javax.swing.JLabel();
    totalValueExpenses = new javax.swing.JLabel();
    expensesTitle = new javax.swing.JLabel();
    periodFilterField = new javax.swing.JComboBox<>();
    separator1 = new javax.swing.JSeparator();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    background.setBackground(new java.awt.Color(255, 255, 255));
    background.setPreferredSize(new java.awt.Dimension(598, 427));

    exitButton.setForeground(new java.awt.Color(255, 0, 51));
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Visão Financeira");

    selectPeriodLabel.setForeground(new java.awt.Color(0, 0, 0));
    selectPeriodLabel.setText("Selecione o período:");

    expensesTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Nome", "Descrição", "Valor"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, true, true
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane1.setViewportView(expensesTable);

    revenuesTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {

      },
      new String [] {
        "Nome", "Descrição", "Valor"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, true, true
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane2.setViewportView(revenuesTable);

    revenuesTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    revenuesTitle.setForeground(new java.awt.Color(0, 0, 0));
    revenuesTitle.setText("Receitas");

    totalValueRevenues.setForeground(new java.awt.Color(0, 0, 0));
    totalValueRevenues.setText("Valor Total:");

    selectedRevenues.setForeground(new java.awt.Color(0, 0, 0));
    selectedRevenues.setText("Selecionadas:");

    calculateButton.setText("Calcular");
    calculateButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        calculateButtonMouseClicked(evt);
      }
    });

    selectedExpenses.setForeground(new java.awt.Color(0, 0, 0));
    selectedExpenses.setText("Selecionadas:");

    totalValueExpenses.setForeground(new java.awt.Color(0, 0, 0));
    totalValueExpenses.setText("Valor Total:");

    expensesTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    expensesTitle.setForeground(new java.awt.Color(0, 0, 0));
    expensesTitle.setText("Despesas");

    periodFilterField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diário", "Semanal", "Mensal" }));

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(separator1)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(selectPeriodLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 315, Short.MAX_VALUE)
            .addComponent(calculateButton))
          .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
          .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(screenTitle))
              .addComponent(revenuesTitle)
              .addComponent(totalValueRevenues)
              .addComponent(selectedRevenues)
              .addComponent(expensesTitle)
              .addComponent(totalValueExpenses)
              .addComponent(selectedExpenses))
            .addGap(0, 0, Short.MAX_VALUE)))
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
          .addComponent(selectPeriodLabel)
          .addComponent(calculateButton)
          .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(revenuesTitle)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(totalValueRevenues)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selectedRevenues)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addComponent(expensesTitle)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(totalValueExpenses)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(selectedExpenses)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(12, 12, 12))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Método chamado para sair da tela.
   */
  private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
    this.dispose();
  }//GEN-LAST:event_exitButtonMouseClicked

  /**
   * Calcula o valor total das receitas e despesas do períoodo selecionado.
   */
  private void calculateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calculateButtonMouseClicked
    filterRegisters();
  }//GEN-LAST:event_calculateButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel background;
  private javax.swing.JButton calculateButton;
  private javax.swing.JLabel exitButton;
  private javax.swing.JTable expensesTable;
  private javax.swing.JLabel expensesTitle;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JTable revenuesTable;
  private javax.swing.JLabel revenuesTitle;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JLabel selectPeriodLabel;
  private javax.swing.JLabel selectedExpenses;
  private javax.swing.JLabel selectedRevenues;
  private javax.swing.JSeparator separator1;
  private javax.swing.JLabel totalValueExpenses;
  private javax.swing.JLabel totalValueRevenues;
  // End of variables declaration//GEN-END:variables
}

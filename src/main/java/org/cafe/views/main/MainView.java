package org.cafe.views.main;

import org.cafe.database.controllers.BudgetController;
import org.cafe.database.controllers.BudgetItemController;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.database.controllers.RevenueController;
import org.cafe.views.expenses.ExpensesView;
import org.cafe.views.revenues.RevenuesView;

public class MainView extends javax.swing.JFrame {
  private final ExpenseController expenseController;
  private final RevenueController revenueController;
  private final BudgetController budgetController;
  private final BudgetItemController budgetItemController;

  /**
   * Construtor.
   *
   * @param expenseController Controlador de despesas.
   * @param revenueController Controlador de receitas.
   * @param budgetController Controlador de orçamentos.
   * @param budgetItemController Controlador de itens de orçamento.
   */
  public MainView(ExpenseController expenseController, RevenueController revenueController, BudgetController budgetController, BudgetItemController budgetItemController) {
    this.expenseController = expenseController;
    this.revenueController = revenueController;
    this.budgetController = budgetController;
    this.budgetItemController = budgetItemController;
    
    initComponents();
    
    revenuesArrowIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_green.png")));
    expensesArrowIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_red.png")));
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    revenuesPainel = new javax.swing.JPanel();
    revenuesArrowIcon = new javax.swing.JLabel();
    revenuesValue = new javax.swing.JLabel();
    revenuesTitle = new javax.swing.JLabel();
    expensesPanel = new javax.swing.JPanel();
    expensesTitle = new javax.swing.JLabel();
    expensesValue = new javax.swing.JLabel();
    expensesArrowIcon = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 255, 255));

    jPanel1.setBackground(new java.awt.Color(255, 255, 255));

    revenuesPainel.setBackground(new java.awt.Color(255, 255, 255));
    revenuesPainel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        revenuesPainelMouseClicked(evt);
      }
    });

    revenuesArrowIcon.setBackground(new java.awt.Color(0, 0, 0));
    revenuesArrowIcon.setForeground(new java.awt.Color(0, 0, 0));

    revenuesValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    revenuesValue.setForeground(new java.awt.Color(51, 204, 0));
    revenuesValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    revenuesValue.setText("R$ 4.595.60");

    revenuesTitle.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    revenuesTitle.setForeground(new java.awt.Color(0, 0, 0));
    revenuesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    revenuesTitle.setText("Receita Mensal");

    javax.swing.GroupLayout revenuesPainelLayout = new javax.swing.GroupLayout(revenuesPainel);
    revenuesPainel.setLayout(revenuesPainelLayout);
    revenuesPainelLayout.setHorizontalGroup(
      revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(revenuesPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(revenuesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(revenuesTitle)
          .addComponent(revenuesValue))
        .addContainerGap())
    );
    revenuesPainelLayout.setVerticalGroup(
      revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(revenuesPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(revenuesPainelLayout.createSequentialGroup()
            .addComponent(revenuesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(revenuesValue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(revenuesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    expensesPanel.setBackground(new java.awt.Color(255, 255, 255));
    expensesPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        expensesPanelMouseClicked(evt);
      }
    });

    expensesTitle.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    expensesTitle.setForeground(new java.awt.Color(0, 0, 0));
    expensesTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    expensesTitle.setText("Despesa Mensal");

    expensesValue.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    expensesValue.setForeground(new java.awt.Color(255, 0, 0));
    expensesValue.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    expensesValue.setText("R$ 3.577.90");

    expensesArrowIcon.setBackground(new java.awt.Color(0, 0, 0));
    expensesArrowIcon.setForeground(new java.awt.Color(0, 0, 0));

    javax.swing.GroupLayout expensesPanelLayout = new javax.swing.GroupLayout(expensesPanel);
    expensesPanel.setLayout(expensesPanelLayout);
    expensesPanelLayout.setHorizontalGroup(
      expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(expensesPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(expensesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(expensesTitle)
          .addComponent(expensesValue))
        .addContainerGap())
    );
    expensesPanelLayout.setVerticalGroup(
      expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(expensesPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(expensesPanelLayout.createSequentialGroup()
            .addComponent(expensesTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(expensesValue, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(expensesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addComponent(revenuesPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addGap(197, 197, 197)
        .addComponent(expensesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel1Layout.createSequentialGroup()
        .addGap(76, 76, 76)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(expensesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(revenuesPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(307, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void revenuesPainelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuesPainelMouseClicked
    RevenuesView revenuesView = new RevenuesView(revenueController);
    
    revenuesView.setVisible(true);
  }//GEN-LAST:event_revenuesPainelMouseClicked

  private void expensesPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesPanelMouseClicked
    ExpensesView expensesView = new ExpensesView(expenseController);
    
    expensesView.setVisible(true);
  }//GEN-LAST:event_expensesPanelMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel expensesArrowIcon;
  private javax.swing.JPanel expensesPanel;
  private javax.swing.JLabel expensesTitle;
  private javax.swing.JLabel expensesValue;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JLabel revenuesArrowIcon;
  private javax.swing.JPanel revenuesPainel;
  private javax.swing.JLabel revenuesTitle;
  private javax.swing.JLabel revenuesValue;
  // End of variables declaration//GEN-END:variables
}

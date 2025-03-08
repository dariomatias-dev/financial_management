package org.cafe.views.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalTime;
import java.util.ArrayList;
import org.cafe.database.controllers.BudgetController;
import org.cafe.database.controllers.BudgetItemController;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.database.controllers.RevenueController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.models.revenue.RevenueModel;
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.views.expenses.ExpensesView;
import org.cafe.views.revenues.RevenuesView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

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
    userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_icon.png")));
    greetingLabel.setText(getGreeting() + ",");

    double revenuesValue = calculateTotalByPeriod(revenueController.getAll());
    double expensesValue = calculateTotalByPeriod(expenseController.getAll());

    String formattedRevenuesValue = CurrencyFormatterUtil.format(revenuesValue);
    String formattedExpensesValue = CurrencyFormatterUtil.format(expensesValue);

    revenuesValueLabel.setText(formattedRevenuesValue);
    expensesValueLabel.setText(formattedExpensesValue);

    graphicAdd(revenuesValue, expensesValue);
  }

  /**
   * Obtém a saudação de acordo com o horário.
   */
  private static String getGreeting() {
    LocalTime currentTime = LocalTime.now();
    int hour = currentTime.getHour();

    if (hour >= 6 && hour < 12) {
      return "Bom dia";
    } else if (hour >= 12 && hour < 18) {
      return "Boa tarde";
    } else {
      return "Boa noite";
    }
  }

  /**
   * Criação do gráfico.
   */
  private void graphicAdd(double revenuesValue, double expensesValue) {
    // Criar os dados do gráfico.
    PieDataset dataset = createDataset(revenuesValue, expensesValue);

    // Cria o gráfico.
    JFreeChart chart = ChartFactory.createPieChart(
            "",
            dataset,
            false,
            true,
            false
    );

    // Cria o painel do gráfico.
    ChartPanel chartPanel = new ChartPanel(chart);
    PiePlot plot = (PiePlot) chart.getPlot();
    plot.setOutlineVisible(false);

    // Cor de fundo do gráfico.
    chart.setBackgroundPaint(Color.WHITE);
    plot.setBackgroundPaint(Color.WHITE);

    // Cor das fatias do gráfico.
    plot.setSectionPaint("Receitas", Color.GREEN);
    plot.setSectionPaint("Despesas", Color.RED);

    // Dimensões do gráfico.
    chartPanel.setPreferredSize(new Dimension(300, 200));

    // Remove todos os rótulos das fatias.
    plot.setLabelGenerator(null);

    // Deixa apenas as porcentagens das fatias nos rótulos.
    plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));

    // Adiciona o gráfico ao painel.
    graphicPanel.removeAll();
    graphicPanel.setLayout(new BorderLayout());
    graphicPanel.add(chartPanel, BorderLayout.CENTER);
    graphicPanel.revalidate();
    graphicPanel.repaint();
  }

  /**
   * Cria as fatias do gráfico.
   */
  private PieDataset createDataset(double revenuesValue, double expensesValue) {
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("Receitas", revenuesValue);
    dataset.setValue("Despesas", expensesValue);

    return dataset;
  }

  /**
   * Cálcula o valor total das receitas ou despesas.
   */
  private <T> double calculateTotalByPeriod(ArrayList<T> records) {
    double total = 0;

    for (T record : records) {
      total += switch (record) {
        case ExpenseModel expense ->
          calculateValueByPeriod(expense.getValue(), expense.getPeriod());
        case RevenueModel revenue ->
          calculateValueByPeriod(revenue.getValue(), revenue.getPeriod());
        default ->
          0;
      };
    }

    return total;
  }

  /**
   * Cálcula o valor com base no seu período.
   */
  private double calculateValueByPeriod(double value, String period) {
    return switch (period) {
      case "Diário" ->
        value;
      case "Semanal" ->
        value * 4;
      case "Mensal" ->
        value * 30;
      default ->
        throw new IllegalArgumentException("Invalid period: " + period);
    };
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
    revenuesValueLabel = new javax.swing.JLabel();
    revenuesTitleLabel = new javax.swing.JLabel();
    expensesPanel = new javax.swing.JPanel();
    expensesTitleLabel = new javax.swing.JLabel();
    expensesValueLabel = new javax.swing.JLabel();
    expensesArrowIcon = new javax.swing.JLabel();
    graphicPanel = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    greetingLabel = new javax.swing.JLabel();
    welcomeLabel = new javax.swing.JLabel();
    userIcon = new javax.swing.JLabel();

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

    revenuesValueLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    revenuesValueLabel.setForeground(new java.awt.Color(51, 204, 0));
    revenuesValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    revenuesValueLabel.setText("R$ 0.00");

    revenuesTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    revenuesTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
    revenuesTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    revenuesTitleLabel.setText("Receita Mensal");

    javax.swing.GroupLayout revenuesPainelLayout = new javax.swing.GroupLayout(revenuesPainel);
    revenuesPainel.setLayout(revenuesPainelLayout);
    revenuesPainelLayout.setHorizontalGroup(
      revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(revenuesPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(revenuesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(revenuesTitleLabel)
          .addComponent(revenuesValueLabel))
        .addContainerGap())
    );
    revenuesPainelLayout.setVerticalGroup(
      revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(revenuesPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(revenuesPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(revenuesPainelLayout.createSequentialGroup()
            .addComponent(revenuesTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(revenuesValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(revenuesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    expensesPanel.setBackground(new java.awt.Color(255, 255, 255));
    expensesPanel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        expensesPanelMouseClicked(evt);
      }
    });

    expensesTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
    expensesTitleLabel.setForeground(new java.awt.Color(0, 0, 0));
    expensesTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    expensesTitleLabel.setText("Despesa Mensal");

    expensesValueLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    expensesValueLabel.setForeground(new java.awt.Color(255, 0, 0));
    expensesValueLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    expensesValueLabel.setText("R$ 0.00");

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
          .addComponent(expensesTitleLabel)
          .addComponent(expensesValueLabel))
        .addContainerGap(46, Short.MAX_VALUE))
    );
    expensesPanelLayout.setVerticalGroup(
      expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(expensesPanelLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(expensesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(expensesPanelLayout.createSequentialGroup()
            .addComponent(expensesTitleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(expensesValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addComponent(expensesArrowIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    graphicPanel.setPreferredSize(new java.awt.Dimension(300, 200));

    javax.swing.GroupLayout graphicPanelLayout = new javax.swing.GroupLayout(graphicPanel);
    graphicPanel.setLayout(graphicPanelLayout);
    graphicPanelLayout.setHorizontalGroup(
      graphicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 300, Short.MAX_VALUE)
    );
    graphicPanelLayout.setVerticalGroup(
      graphicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 200, Short.MAX_VALUE)
    );

    jPanel2.setBackground(new java.awt.Color(0, 204, 0));
    jPanel2.setPreferredSize(new java.awt.Dimension(0, 100));
    jPanel2.setRequestFocusEnabled(false);

    greetingLabel.setForeground(new java.awt.Color(255, 255, 255));
    greetingLabel.setText("Bom dia,");

    welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
    welcomeLabel.setText("Seja Bem-Vindo(a)");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGap(12, 12, 12)
        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(welcomeLabel)
          .addComponent(greetingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(165, 165, 165))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel2Layout.createSequentialGroup()
        .addGap(34, 34, 34)
        .addComponent(greetingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        .addContainerGap(23, Short.MAX_VALUE)
        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(17, 17, 17))
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel1Layout.createSequentialGroup()
            .addComponent(revenuesPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGap(197, 197, 197)
            .addComponent(expensesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(graphicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(103, 103, 103)))
        .addContainerGap())
      .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(expensesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(revenuesPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(graphicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

  /**
   * Abre a tela de receitas.
   */
  private void revenuesPainelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuesPainelMouseClicked
    RevenuesView revenuesView = new RevenuesView(revenueController);

    revenuesView.setVisible(true);
  }//GEN-LAST:event_revenuesPainelMouseClicked

  /**
   * Abre a tela de despesas.
   */
  private void expensesPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesPanelMouseClicked
    ExpensesView expensesView = new ExpensesView(expenseController);

    expensesView.setVisible(true);
  }//GEN-LAST:event_expensesPanelMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel expensesArrowIcon;
  private javax.swing.JPanel expensesPanel;
  private javax.swing.JLabel expensesTitleLabel;
  private javax.swing.JLabel expensesValueLabel;
  private javax.swing.JPanel graphicPanel;
  private javax.swing.JLabel greetingLabel;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JLabel revenuesArrowIcon;
  private javax.swing.JPanel revenuesPainel;
  private javax.swing.JLabel revenuesTitleLabel;
  private javax.swing.JLabel revenuesValueLabel;
  private javax.swing.JLabel userIcon;
  private javax.swing.JLabel welcomeLabel;
  // End of variables declaration//GEN-END:variables
}

package org.cafe.views.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalTime;
import java.util.ArrayList;
import org.cafe.database.controllers.BudgetDatabaseController;
import org.cafe.database.controllers.BudgetItemDatabaseController;
import org.cafe.database.controllers.ExpenseDatabaseController;
import org.cafe.database.controllers.RevenueDatabaseController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.models.revenue.RevenueModel;
import org.cafe.utils.CurrencyFormatter;
import org.cafe.views.budgets.BudgetsView;
import org.cafe.views.expenses.ExpensesView;
import org.cafe.views.financial_overview.FinancialOverviewView;
import org.cafe.views.revenues.RevenuesView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class MainView extends javax.swing.JFrame {
  private final ExpenseDatabaseController expenseDatabaseController;
  private final RevenueDatabaseController revenueDatabaseController;
  private final BudgetDatabaseController budgetDatabaseController;
  private final BudgetItemDatabaseController budgetItemDatabaseController;

  /**
   * Construtor.
   *
   * @param expenseDatabaseController Controlador de despesas.
   * @param revenueDatabaseController Controlador de receitas.
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   */
  public MainView(ExpenseDatabaseController expenseDatabaseController, RevenueDatabaseController revenueDatabaseController, BudgetDatabaseController budgetDatabaseController, BudgetItemDatabaseController budgetItemDatabaseController) {
    this.expenseDatabaseController = expenseDatabaseController;
    this.revenueDatabaseController = revenueDatabaseController;
    this.budgetDatabaseController = budgetDatabaseController;
    this.budgetItemDatabaseController = budgetItemDatabaseController;

    initComponents();

    revenuesArrowIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_green.png")));
    expensesArrowIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/arrow_red.png")));
    userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_icon.png")));
    financialOverviewIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/financial_vision_icon.png")));
    budgetsIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/budgets_icon.png")));

    greetingLabel.setText(getGreeting() + ",");

    loadData();
  }

  /**
   * Carrega os dados que serão exibidos na tela.
   */
  private void loadData() {
    double revenuesValue = calculateTotalByPeriod(revenueDatabaseController.getAll());
    double expensesValue = calculateTotalByPeriod(expenseDatabaseController.getAll());

    String formattedRevenuesValue = CurrencyFormatter.format(revenuesValue);
    String formattedExpensesValue = CurrencyFormatter.format(expensesValue);

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

    background = new javax.swing.JPanel();
    revenuesPainel = new javax.swing.JPanel();
    revenuesArrowIcon = new javax.swing.JLabel();
    revenuesValueLabel = new javax.swing.JLabel();
    revenuesTitleLabel = new javax.swing.JLabel();
    expensesPanel = new javax.swing.JPanel();
    expensesTitleLabel = new javax.swing.JLabel();
    expensesValueLabel = new javax.swing.JLabel();
    expensesArrowIcon = new javax.swing.JLabel();
    graphicPanel = new javax.swing.JPanel();
    headerPainel = new javax.swing.JPanel();
    greetingLabel = new javax.swing.JLabel();
    welcomeLabel = new javax.swing.JLabel();
    userIcon = new javax.swing.JLabel();
    financialOverviewPainel = new javax.swing.JPanel();
    financialOverviewLabel = new javax.swing.JLabel();
    financialOverviewIcon = new javax.swing.JLabel();
    actionsLabel = new javax.swing.JLabel();
    budgetsPainel = new javax.swing.JPanel();
    budgetsLabel = new javax.swing.JLabel();
    budgetsIcon = new javax.swing.JLabel();
    separator1 = new javax.swing.JSeparator();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setBackground(new java.awt.Color(255, 255, 255));

    background.setBackground(new java.awt.Color(255, 255, 255));

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

    headerPainel.setBackground(new java.awt.Color(0, 204, 0));
    headerPainel.setPreferredSize(new java.awt.Dimension(0, 100));
    headerPainel.setRequestFocusEnabled(false);

    greetingLabel.setForeground(new java.awt.Color(255, 255, 255));
    greetingLabel.setText("Bom dia,");

    welcomeLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
    welcomeLabel.setText("Seja Bem-Vindo(a)");

    javax.swing.GroupLayout headerPainelLayout = new javax.swing.GroupLayout(headerPainel);
    headerPainel.setLayout(headerPainelLayout);
    headerPainelLayout.setHorizontalGroup(
      headerPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(headerPainelLayout.createSequentialGroup()
        .addGap(12, 12, 12)
        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(headerPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(welcomeLabel)
          .addComponent(greetingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addGap(165, 165, 165))
    );
    headerPainelLayout.setVerticalGroup(
      headerPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(headerPainelLayout.createSequentialGroup()
        .addGap(34, 34, 34)
        .addComponent(greetingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerPainelLayout.createSequentialGroup()
        .addContainerGap(23, Short.MAX_VALUE)
        .addComponent(userIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(17, 17, 17))
    );

    financialOverviewPainel.setBackground(new java.awt.Color(255, 255, 255));
    financialOverviewPainel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    financialOverviewPainel.setPreferredSize(new java.awt.Dimension(240, 120));
    financialOverviewPainel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        financialOverviewPainelMouseClicked(evt);
      }
    });

    financialOverviewLabel.setForeground(new java.awt.Color(0, 0, 0));
    financialOverviewLabel.setText("Visão Financeira");

    financialOverviewIcon.setBackground(new java.awt.Color(0, 0, 0));
    financialOverviewIcon.setForeground(new java.awt.Color(0, 0, 0));
    financialOverviewIcon.setPreferredSize(new java.awt.Dimension(44, 44));

    javax.swing.GroupLayout financialOverviewPainelLayout = new javax.swing.GroupLayout(financialOverviewPainel);
    financialOverviewPainel.setLayout(financialOverviewPainelLayout);
    financialOverviewPainelLayout.setHorizontalGroup(
      financialOverviewPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(financialOverviewPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(financialOverviewIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(financialOverviewLabel)
        .addContainerGap(89, Short.MAX_VALUE))
    );
    financialOverviewPainelLayout.setVerticalGroup(
      financialOverviewPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(financialOverviewPainelLayout.createSequentialGroup()
        .addGroup(financialOverviewPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(financialOverviewPainelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(financialOverviewIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(financialOverviewPainelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(financialOverviewLabel)))
        .addContainerGap(16, Short.MAX_VALUE))
    );

    actionsLabel.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    actionsLabel.setForeground(new java.awt.Color(0, 0, 0));
    actionsLabel.setText("Ações:");

    budgetsPainel.setBackground(new java.awt.Color(255, 255, 255));
    budgetsPainel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    budgetsPainel.setPreferredSize(new java.awt.Dimension(240, 120));
    budgetsPainel.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        budgetsPainelMouseClicked(evt);
      }
    });

    budgetsLabel.setForeground(new java.awt.Color(0, 0, 0));
    budgetsLabel.setText("Orçamentos");

    budgetsIcon.setBackground(new java.awt.Color(0, 0, 0));
    budgetsIcon.setForeground(new java.awt.Color(0, 0, 0));
    budgetsIcon.setPreferredSize(new java.awt.Dimension(44, 44));

    javax.swing.GroupLayout budgetsPainelLayout = new javax.swing.GroupLayout(budgetsPainel);
    budgetsPainel.setLayout(budgetsPainelLayout);
    budgetsPainelLayout.setHorizontalGroup(
      budgetsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(budgetsPainelLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(budgetsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(budgetsLabel)
        .addContainerGap(109, Short.MAX_VALUE))
    );
    budgetsPainelLayout.setVerticalGroup(
      budgetsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(budgetsPainelLayout.createSequentialGroup()
        .addGroup(budgetsPainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(budgetsPainelLayout.createSequentialGroup()
            .addContainerGap()
            .addComponent(budgetsIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(budgetsPainelLayout.createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(budgetsLabel)))
        .addContainerGap(16, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(headerPainel, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGap(108, 108, 108)
            .addComponent(graphicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(revenuesPainel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(197, 197, 197)
                .addComponent(expensesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
              .addComponent(separator1)))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addContainerGap()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(actionsLabel)
                .addGap(0, 0, Short.MAX_VALUE))
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(financialOverviewPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(budgetsPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        .addContainerGap())
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
        .addComponent(headerPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(expensesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(revenuesPainel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(graphicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(actionsLabel)
        .addGap(18, 18, 18)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(financialOverviewPainel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(budgetsPainel, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
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
   * Abre a tela de orçamentos.
   */
  private void budgetsPainelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_budgetsPainelMouseClicked
    BudgetsView budgetsView = new BudgetsView(budgetDatabaseController, budgetItemDatabaseController);

    budgetsView.setVisible(true);
  }//GEN-LAST:event_budgetsPainelMouseClicked

  /**
   * Abre a tela de visão financeira.
   */
  private void financialOverviewPainelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_financialOverviewPainelMouseClicked
    FinancialOverviewView financialOverviewView = new FinancialOverviewView(revenueDatabaseController, expenseDatabaseController);

    financialOverviewView.setVisible(true);
  }//GEN-LAST:event_financialOverviewPainelMouseClicked

  /**
   * Abre a tela de despesas.
   */
  private void expensesPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expensesPanelMouseClicked
    ExpensesView expensesView = new ExpensesView(expenseDatabaseController, this::loadData);

    expensesView.setVisible(true);
  }//GEN-LAST:event_expensesPanelMouseClicked

  /**
   * Abre a tela de receitas.
   */
  private void revenuesPainelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuesPainelMouseClicked
    RevenuesView revenuesView = new RevenuesView(revenueDatabaseController, this::loadData);

    revenuesView.setVisible(true);
  }//GEN-LAST:event_revenuesPainelMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel actionsLabel;
  private javax.swing.JPanel background;
  private javax.swing.JLabel budgetsIcon;
  private javax.swing.JLabel budgetsLabel;
  private javax.swing.JPanel budgetsPainel;
  private javax.swing.JLabel expensesArrowIcon;
  private javax.swing.JPanel expensesPanel;
  private javax.swing.JLabel expensesTitleLabel;
  private javax.swing.JLabel expensesValueLabel;
  private javax.swing.JLabel financialOverviewIcon;
  private javax.swing.JLabel financialOverviewLabel;
  private javax.swing.JPanel financialOverviewPainel;
  private javax.swing.JPanel graphicPanel;
  private javax.swing.JLabel greetingLabel;
  private javax.swing.JPanel headerPainel;
  private javax.swing.JLabel revenuesArrowIcon;
  private javax.swing.JPanel revenuesPainel;
  private javax.swing.JLabel revenuesTitleLabel;
  private javax.swing.JLabel revenuesValueLabel;
  private javax.swing.JSeparator separator1;
  private javax.swing.JLabel userIcon;
  private javax.swing.JLabel welcomeLabel;
  // End of variables declaration//GEN-END:variables
}

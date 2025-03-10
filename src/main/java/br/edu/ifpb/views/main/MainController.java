package br.edu.ifpb.views.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.*;
import br.edu.ifpb.database.controllers.BudgetDatabaseController;
import br.edu.ifpb.database.controllers.BudgetItemDatabaseController;
import br.edu.ifpb.database.controllers.ExpenseDatabaseController;
import br.edu.ifpb.database.controllers.RevenueDatabaseController;
import br.edu.ifpb.models.expense.ExpenseModel;
import br.edu.ifpb.models.revenue.RevenueModel;
import br.edu.ifpb.utils.CurrencyFormatter;
import br.edu.ifpb.views.budgets.BudgetsView;
import br.edu.ifpb.views.expenses.ExpensesView;
import br.edu.ifpb.views.financial_overview.FinancialOverviewView;
import br.edu.ifpb.views.revenues.RevenuesView;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class MainController {
  private final ExpenseDatabaseController expenseDatabaseController;
  private final RevenueDatabaseController revenueDatabaseController;
  private final BudgetDatabaseController budgetDatabaseController;
  private final BudgetItemDatabaseController budgetItemDatabaseController;

  private final JLabel revenuesValueLabel;
  private final JLabel expensesValueLabel;
  private final JPanel graphicPanel;

  /**
   * Construtor.
   *
   * @param expenseDatabaseController Controlador de despesas.
   * @param revenueDatabaseController Controlador de receitas.
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   * @param revenuesArrowIcon Íconede receitas.
   * @param expensesArrowIcon Ícone de despesas.
   * @param userIcon Ícone de usuário.
   * @param financialOverviewIcon Ícone de visão financeira.
   * @param budgetsIcon Ícone de orçamentos.
   * @param greetingLabel Rótulo de boas vindas.
   * @param revenuesValueLabel Valor das receitas.
   * @param expensesValueLabel Valor das despesas.
   * @param graphicPanel Gráfico de porcentagem das receitas e despesas.
   */
  public MainController(
          ExpenseDatabaseController expenseDatabaseController,
          RevenueDatabaseController revenueDatabaseController,
          BudgetDatabaseController budgetDatabaseController,
          BudgetItemDatabaseController budgetItemDatabaseController,
          JLabel revenuesArrowIcon,
          JLabel expensesArrowIcon,
          JLabel userIcon,
          JLabel financialOverviewIcon,
          JLabel budgetsIcon,
          JLabel greetingLabel,
          JLabel revenuesValueLabel,
          JLabel expensesValueLabel,
          JPanel graphicPanel
  ) {
    this.expenseDatabaseController = expenseDatabaseController;
    this.revenueDatabaseController = revenueDatabaseController;
    this.budgetDatabaseController = budgetDatabaseController;
    this.budgetItemDatabaseController = budgetItemDatabaseController;

    this.revenuesValueLabel = revenuesValueLabel;
    this.expensesValueLabel = expensesValueLabel;
    this.graphicPanel = graphicPanel;

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
        value * 30;
      case "Semanal" ->
        value * 4;
      case "Mensal" ->
        value;
      default ->
        0;
    };
  }

  // SCREEN ACTIONS
  /**
   * Abre a tela de orçamentos.
   */
  protected void budgetsButton() {
    BudgetsView budgetsView = new BudgetsView(budgetDatabaseController, budgetItemDatabaseController);

    budgetsView.setVisible(true);
  }

  /**
   * Abre a tela de visão financeira.
   */
  protected void financialOverviewButton() {
    FinancialOverviewView financialOverviewView = new FinancialOverviewView(revenueDatabaseController, expenseDatabaseController);

    financialOverviewView.setVisible(true);
  }

  /**
   * Abre a tela de despesas.
   */
  protected void expensesButton() {
    ExpensesView expensesView = new ExpensesView(expenseDatabaseController, this::loadData);

    expensesView.setVisible(true);
  }

  /**
   * Abre a tela de receitas.
   */
  protected void revenuesButton() {
    RevenuesView revenuesView = new RevenuesView(revenueDatabaseController, this::loadData);

    revenuesView.setVisible(true);
  }
}

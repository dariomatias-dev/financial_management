package org.cafe.views.expenses;

import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.utils.RecordVerificationUtil;
import org.cafe.views.expenses.components.manager_expense.ManagerExpenseView;

public class ExpensesView extends javax.swing.JFrame {
  private final ExpenseController expenseController;
  private ArrayList<ExpenseModel> expenses;

  /**
   * Construtor.
   *
   * @param expenseController Controlador de despesas.
   */
  public ExpensesView(ExpenseController expenseController) {
    this.expenseController = expenseController;

    initComponents();

    listExpenses();
  }

  /**
   * Lista todas as despesas.
   */
  private void listExpenses() {
    expenses = expenseController.getAll();

    showBudgetItems(expenses);
  }

  /**
   * Mostra os itens repassados.
   */
  private void showBudgetItems(
          ArrayList<ExpenseModel> value
  ) {
    DefaultListModel<String> model = new DefaultListModel<>();
    expenseList.setModel(model);

    for (ExpenseModel expense : value) {
      String formattedValue = CurrencyFormatterUtil.format(expense.getValue());
      String displayText = expense.getName() + "  |  " + formattedValue + "  |  " + expense.getPeriod();
      model.addElement(displayText);
    }
  }

  /**
   * Atualiza a lista de despesas para exibir somente as despesas que existem.
   */
  private void updateScreen() {
    DefaultListModel<String> model = new DefaultListModel<>();
    expenseList.setModel(model);
    model.clear();
    listExpenses();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jMenu1 = new javax.swing.JMenu();
    background = new javax.swing.JPanel();
    screenTitle = new javax.swing.JLabel();
    exitButton = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    expenseList = new javax.swing.JList<>();
    deleteButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    addButton = new javax.swing.JButton();
    searchField = new javax.swing.JTextField();
    searchButton = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    periodFilterField = new javax.swing.JComboBox<>();

    jMenu1.setText("jMenu1");

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
    exitButton.setText("Sair");
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

    expenseList.setBorder(null);
    jScrollPane1.setViewportView(expenseList);

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

    jLabel1.setText("Período:");

    periodFilterField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Diário", "Semanal", "Mensal" }));

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(exitButton)
            .addGap(34, 34, 34)
            .addComponent(screenTitle)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
            .addComponent(addButton))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(searchField)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(searchButton))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(updateButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton)))
        .addContainerGap())
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGap(19, 19, 19)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(screenTitle)
          .addComponent(exitButton)
          .addComponent(addButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(searchButton))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel1)
          .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deleteButton)
          .addComponent(updateButton))
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

  // Ação de sair da tela.
    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
      this.dispose();
    }//GEN-LAST:event_exitButtonMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

  // Ação de excluir registro.
    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
      if (RecordVerificationUtil.verifyRecords(expenseList, "excluir")) {
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
          ExpenseModel selectedExpense = expenses.get(expenseList.getSelectedIndex());
          expenseController.removeById(selectedExpense.getId());

          updateScreen();
        }
      }
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
      ManagerExpenseView createManagerRegisterView = new ManagerExpenseView(expenseController, null, this::updateScreen);

      createManagerRegisterView.setVisible(true);
    }//GEN-LAST:event_addButtonMouseClicked

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
      if (RecordVerificationUtil.verifyRecords(expenseList, "atualizar")) {
        ExpenseModel selectedExpense = expenses.get(expenseList.getSelectedIndex());
        ManagerExpenseView updateManagerRegisterView = new ManagerExpenseView(expenseController, selectedExpense, this::updateScreen);

        updateManagerRegisterView.setVisible(true);
      }
    }//GEN-LAST:event_updateButtonMouseClicked

  private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();

    if (query.equals("Pesquisar...") && periodFilter.equals("Todos")) {
      showBudgetItems(expenses);
    } else {
      ArrayList<ExpenseModel> results = new ArrayList<>();

      for (ExpenseModel expense : expenses) {
        boolean matchesQuery = query.equals("Pesquisar...") || expense.getName().contains(query) || expense.getDescription().contains(query);

        boolean matchesPeriod = periodFilter.equals("Todos") || expense.getPeriod().equals(periodFilter);

        if (matchesQuery && matchesPeriod) {
          results.add(expense);
        }
      }

      showBudgetItems(results);
    }
  }//GEN-LAST:event_searchButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel background;
  private javax.swing.JButton deleteButton;
  private javax.swing.JLabel exitButton;
  private javax.swing.JList<String> expenseList;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JButton updateButton;
  // End of variables declaration//GEN-END:variables
}

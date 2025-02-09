package org.cafe.views.Expense;

import java.util.ArrayList;
import java.util.Locale;
import javax.swing.DefaultListModel;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.models.expense.ExpenseModel;
import org.cafe.views.Expense.components.ManagerRegisterView;
import java.text.NumberFormat;

/**
 *
 * @author Dário
 */
public class ExpenseView extends javax.swing.JFrame {

    private final ExpenseController expenseController;
    private ArrayList<ExpenseModel> expenses;

    /**
     * Construtor.
     *
     * @param expenseController Controlador de despesas.
     */
    public ExpenseView(ExpenseController expenseController) {
        this.expenseController = expenseController;

        initComponents();

        listExpenses();
    }

    /**
     * Lista todas as despesas.
     */
    private void listExpenses() {
        expenses = expenseController.getAll();
        DefaultListModel<String> model = new DefaultListModel<>();
        expenseList.setModel(model);

        Locale brazilianLocale = new Locale.Builder().setLanguage("pt").setRegion("BR").build();
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(brazilianLocale);

        for (ExpenseModel expense : expenses) {
            String expenseName = expense.getName();
            String formattedValue = currencyFormat.format(expense.getValue());
            String displayText = expenseName + "     -     " + formattedValue;
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
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setText("Atualizar");

        addButton.setText("Adicionar");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updateButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton))
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(exitButton)
                        .addGap(34, 34, 34)
                        .addComponent(screenTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                        .addComponent(addButton)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(screenTitle)
                    .addComponent(exitButton)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton)
                    .addComponent(updateButton))
                .addContainerGap(361, Short.MAX_VALUE))
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

    private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_exitButtonMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        ExpenseModel selectedExpense = expenses.get(expenseList.getSelectedIndex());

        expenseController.removeById(selectedExpense.getId());
        
        updateScreen();
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        ManagerRegisterView createExpenseRegisterView = new ManagerRegisterView(expenseController, this::updateScreen);

        createExpenseRegisterView.setVisible(true);
    }//GEN-LAST:event_addButtonMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel background;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel exitButton;
    private javax.swing.JList<String> expenseList;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel screenTitle;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}

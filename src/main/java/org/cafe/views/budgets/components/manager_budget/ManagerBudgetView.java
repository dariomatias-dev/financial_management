package org.cafe.views.budgets.components.manager_budget;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.function.Consumer;
import javax.swing.JOptionPane;
import org.cafe.core.formatters.DateMaskFormatter;
import org.cafe.database.controllers.BudgetController;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget.CreateBudgetModel;
import org.cafe.utils.DateFormatter;
import org.cafe.utils.NumberValidator;

public class ManagerBudgetView extends javax.swing.JFrame {
  private final Consumer<String> onUpdateScreen;
  private final BudgetModel data;
  private final BudgetController budgetController;

  /**
   * Construtor.
   *
   * @param budgetController Controlador de orçamentos.
   * @param data Dados do registro selecionado.
   * @param onUpdateScreen Função para atualização da tela de listagem dos
   * orçamentos.
   */
  public ManagerBudgetView(BudgetController budgetController, BudgetModel data, Consumer<String> onUpdateScreen) {
    this.budgetController = budgetController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;

    initComponents();

    new DateMaskFormatter().applyMask(initialDateField);
    new DateMaskFormatter().applyMask(endDateField);

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    // Preenche os campos com os atuais dados do orçamento caso a tela seja de atualização.
    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      categoryField.setText(data.getCategory());
      statusSelect.setSelectedItem(data.getStatus());
      totalBudgetField.setText(String.valueOf(data.getTotalBudgetValue()));

      initialDateField.setText(formatter.format(data.getInitialDate()));
      endDateField.setText(formatter.format(data.getEndDate()));

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Orçamento");
      actionButton.setText("Atualizar");
    } else {
      Date currentDate = new Date();
      initialDateField.setText(formatter.format(currentDate));
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
    screenTitle = new javax.swing.JLabel();
    nameLabel = new javax.swing.JLabel();
    nameField = new javax.swing.JTextField();
    descriptionField = new javax.swing.JTextField();
    descriptionLabel = new javax.swing.JLabel();
    totalBudgetField = new javax.swing.JTextField();
    categoryLabel = new javax.swing.JLabel();
    actionButton = new javax.swing.JButton();
    cancelButton = new javax.swing.JButton();
    statusLabel = new javax.swing.JLabel();
    initialDateLabel = new javax.swing.JLabel();
    endDateLabel = new javax.swing.JLabel();
    statusSelect = new javax.swing.JComboBox<>();
    endDateField = new javax.swing.JFormattedTextField();
    initialDateField = new javax.swing.JFormattedTextField();
    totalBudgetLabel = new javax.swing.JLabel();
    categoryField = new javax.swing.JTextField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    background.setBackground(new java.awt.Color(255, 255, 255));

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Criar Orçamento");

    nameLabel.setBackground(new java.awt.Color(0, 0, 0));
    nameLabel.setForeground(new java.awt.Color(0, 0, 0));
    nameLabel.setText("Nome:");

    descriptionLabel.setBackground(new java.awt.Color(0, 0, 0));
    descriptionLabel.setForeground(new java.awt.Color(0, 0, 0));
    descriptionLabel.setText("Descrição:");

    categoryLabel.setBackground(new java.awt.Color(0, 0, 0));
    categoryLabel.setForeground(new java.awt.Color(0, 0, 0));
    categoryLabel.setText("Categoria:");

    actionButton.setText("Criar");
    actionButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        actionButtonMouseClicked(evt);
      }
    });

    cancelButton.setText("Cancelar");
    cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        cancelButtonMouseClicked(evt);
      }
    });

    statusLabel.setBackground(new java.awt.Color(0, 0, 0));
    statusLabel.setForeground(new java.awt.Color(0, 0, 0));
    statusLabel.setText("Status:");

    initialDateLabel.setBackground(new java.awt.Color(0, 0, 0));
    initialDateLabel.setForeground(new java.awt.Color(0, 0, 0));
    initialDateLabel.setText("Data Inicial:");

    endDateLabel.setBackground(new java.awt.Color(0, 0, 0));
    endDateLabel.setForeground(new java.awt.Color(0, 0, 0));
    endDateLabel.setText("Data Final:");

    statusSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rascunho", "Finalizado", "Negado", "Aprovado" }));

    totalBudgetLabel.setBackground(new java.awt.Color(0, 0, 0));
    totalBudgetLabel.setForeground(new java.awt.Color(0, 0, 0));
    totalBudgetLabel.setText("Valor");

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
        .addContainerGap(166, Short.MAX_VALUE)
        .addComponent(screenTitle)
        .addGap(120, 120, 120))
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(nameField)
          .addComponent(descriptionField)
          .addComponent(totalBudgetField)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(cancelButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(actionButton))
          .addComponent(endDateField)
          .addComponent(initialDateField)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(nameLabel)
              .addComponent(descriptionLabel)
              .addComponent(categoryLabel)
              .addComponent(statusLabel)
              .addComponent(statusSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
              .addComponent(totalBudgetLabel)
              .addComponent(initialDateLabel)
              .addComponent(endDateLabel))
            .addGap(0, 0, Short.MAX_VALUE))
          .addComponent(categoryField, javax.swing.GroupLayout.Alignment.TRAILING))
        .addContainerGap())
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGap(20, 20, 20)
        .addComponent(screenTitle)
        .addGap(29, 29, 29)
        .addComponent(nameLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(descriptionLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(categoryLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(categoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(statusLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(statusSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(totalBudgetLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(totalBudgetField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(initialDateLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(initialDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(endDateLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(endDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(18, 18, 18)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(actionButton)
          .addComponent(cancelButton))
        .addGap(35, 35, 35))
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
   * Método chamado para sair da tela.
   */
    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
      this.dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

  /**
   * Método de criação ou atualização de um orçamento.
   */
    private void actionButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actionButtonMouseClicked
      // Obtenção dos dados.
      String name = nameField.getText();
      String category = categoryField.getText();
      String initialDateText = initialDateField.getText();
      String endDateText = endDateField.getText();
      String valueText = totalBudgetField.getText();

      // Verificação da presença dos dados necessários.
      if (name.isEmpty() || category.isEmpty() || initialDateText.isEmpty() || endDateText.isEmpty()
              || initialDateText.contains("_") || endDateText.contains("_") || valueText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);

        return;
      }

      // Validação do valor numérico.
      NumberValidator numberValidator = new NumberValidator();
      if (!numberValidator.validate(this, valueText, "orçamento")) {
        return;
      }

      // Obtenção das datas.
      LocalDate initialDate = DateFormatter.parse(initialDateText);
      if (initialDate == null) {
        return;
      }
      LocalDate endDate = DateFormatter.parse(endDateText);
      if (endDate == null) {
        return;
      }

      if (initialDate.isAfter(endDate)) {
        JOptionPane.showMessageDialog(this, "A data de início não pode ser posterior à data de término.", "Erro", JOptionPane.ERROR_MESSAGE);
        return;
      }

      String status = (String) statusSelect.getSelectedItem();

      String budgetId;

      // Verifica se a tela é de atualização ou criação.
      if (data != null) {
        // Atualiza os dados do orçamento.
        budgetController.update(
                new BudgetModel(
                        data.getId(),
                        name,
                        category,
                        category,
                        status,
                        numberValidator.getNumber(),
                        data.getTotalSpent(),
                        initialDate,
                        endDate
                )
        );

        budgetId = data.getId();
      } else {
        // Cria o orçamento e obtém o ID do registro.
        budgetId = budgetController.create(
                new CreateBudgetModel(
                        name,
                        category,
                        category,
                        status,
                        numberValidator.getNumber(),
                        0.0,
                        initialDate,
                        endDate
                )
        );
      }

      onUpdateScreen.accept(budgetId);

      this.dispose();

    }//GEN-LAST:event_actionButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton actionButton;
  private javax.swing.JPanel background;
  private javax.swing.JButton cancelButton;
  private javax.swing.JTextField categoryField;
  private javax.swing.JLabel categoryLabel;
  private javax.swing.JTextField descriptionField;
  private javax.swing.JLabel descriptionLabel;
  private javax.swing.JFormattedTextField endDateField;
  private javax.swing.JLabel endDateLabel;
  private javax.swing.JFormattedTextField initialDateField;
  private javax.swing.JLabel initialDateLabel;
  private javax.swing.JTextField nameField;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JLabel statusLabel;
  private javax.swing.JComboBox<String> statusSelect;
  private javax.swing.JTextField totalBudgetField;
  private javax.swing.JLabel totalBudgetLabel;
  // End of variables declaration//GEN-END:variables
}

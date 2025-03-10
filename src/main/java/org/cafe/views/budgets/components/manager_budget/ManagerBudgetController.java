package org.cafe.views.budgets.components.manager_budget;

import java.awt.Window;
import java.time.LocalDate;
import java.util.function.Consumer;
import javax.swing.*;
import org.cafe.core.formatters.DateMaskFormatter;
import org.cafe.database.controllers.BudgetDatabaseController;
import org.cafe.models.budget.BudgetModel;
import org.cafe.models.budget.CreateBudgetModel;
import org.cafe.utils.DateFormatter;
import org.cafe.utils.NumberValidator;

public class ManagerBudgetController {
  private final Consumer<String> onUpdateScreen;
  private final BudgetModel data;
  private final BudgetDatabaseController budgetDatabaseController;

  private final Window parentComponent;
  private final JTextField nameField;
  private final JTextField descriptionField;
  private final JTextField categoryField;
  private final JComboBox<String> statusSelect;
  private final JTextField valueField;
  private final JFormattedTextField initialDateField;
  private final JFormattedTextField endDateField;

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param budgetDatabaseController Controlador de orçamentos.
   * @param data Dados do orçamento selecionado caso seja para atualizar.
   * @param onUpdateScreen Função para atualização da tela de listagem dos
   * orçamentos.
   * @param screenTitle Título da tela de gerenciamento de orçamentos.
   * @param nameField Campo de nome.
   * @param descriptionField Campo de descrição.
   * @param categoryField Campo de categoria.
   * @param statusSelect Seletor de status.
   * @param valueField Campo de valor.
   * @param initialDateField Campo de data inicial.
   * @param endDateField Campo de data final.
   * @param actionButton Botão de criação ou atualização.
   */
  public ManagerBudgetController(
          Window parentComponent,
          BudgetDatabaseController budgetDatabaseController,
          BudgetModel data,
          Consumer<String> onUpdateScreen,
          JLabel screenTitle,
          JTextField nameField,
          JTextField descriptionField,
          JTextField categoryField,
          JComboBox<String> statusSelect,
          JTextField valueField,
          JFormattedTextField initialDateField,
          JFormattedTextField endDateField,
          JButton actionButton
  ) {
    this.parentComponent = parentComponent;
    this.budgetDatabaseController = budgetDatabaseController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;
    this.nameField = nameField;
    this.descriptionField = descriptionField;
    this.categoryField = categoryField;
    this.statusSelect = statusSelect;
    this.valueField = valueField;
    this.initialDateField = initialDateField;
    this.endDateField = endDateField;

    new DateMaskFormatter().applyMask(initialDateField);
    new DateMaskFormatter().applyMask(endDateField);

    // Preenche os campos com os atuais dados do orçamento caso a tela seja de atualização.
    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      categoryField.setText(data.getCategory());
      statusSelect.setSelectedItem(data.getStatus());
      valueField.setText(String.valueOf(data.getTotalBudgetValue()));

      initialDateField.setText(DateFormatter.format(data.getInitialDate()));
      endDateField.setText(DateFormatter.format(data.getEndDate()));

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Orçamento");
      actionButton.setText("Atualizar");
    } else {
      LocalDate currentDate = LocalDate.now();
      initialDateField.setText(DateFormatter.format(currentDate));
    }
  }

  // SCREEN ACTIONS
  /**
   * Criação ou atualização de uma receita.
   */
  protected void actionButton() {
    // Obtenção dos dados.
    String name = nameField.getText();
    String description = descriptionField.getText();
    String category = categoryField.getText();
    String initialDateText = initialDateField.getText();
    String endDateText = endDateField.getText();
    String valueText = valueField.getText();

    // Verificação da presença dos dados necessários.
    if (name.isEmpty() || category.isEmpty() || initialDateText.isEmpty() || endDateText.isEmpty()
            || initialDateText.contains("_") || endDateText.contains("_") || valueText.isEmpty()) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, preencha todos os campos corretamente.", "Erro", JOptionPane.ERROR_MESSAGE);

      return;
    }

    // Validação do valor numérico.
    NumberValidator numberValidator = new NumberValidator();
    if (!numberValidator.validate(parentComponent, valueText, "orçamento")) {
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
      JOptionPane.showMessageDialog(parentComponent, "A data de início não pode ser posterior à data de término.", "Erro", JOptionPane.ERROR_MESSAGE);
      return;
    }

    String status = (String) statusSelect.getSelectedItem();

    String budgetId;

    // Verifica se a tela é de atualização ou criação.
    if (data != null) {
      // Atualiza os dados do orçamento.
      budgetDatabaseController.update(
              new BudgetModel(
                      data.getId(),
                      name,
                      description,
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
      budgetId = budgetDatabaseController.create(
              new CreateBudgetModel(
                      name,
                      description,
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

    parentComponent.dispose();
  }
}

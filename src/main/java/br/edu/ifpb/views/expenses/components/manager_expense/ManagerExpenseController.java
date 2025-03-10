package br.edu.ifpb.views.expenses.components.manager_expense;

import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import br.edu.ifpb.database.controllers.ExpenseDatabaseController;
import br.edu.ifpb.models.expense.CreateExpenseModel;
import br.edu.ifpb.models.expense.ExpenseModel;
import br.edu.ifpb.utils.NumberValidator;

public class ManagerExpenseController {
  private final Runnable onUpdateScreen;
  private final ExpenseDatabaseController expenseDatabaseController;
  private final ExpenseModel data;

  private final Window parentComponent;
  private final JTextField nameField;
  private final JTextField valueField;
  private final JTextField descriptionField;
  private final JComboBox<String> periodSelect;

  /**
   * Construtor.
   *
   * @param expenseDatabaseController Controlador de despesas.
   * @param data Dados do registro selecionado caso seja para atualizar.
   * @param onUpdateScreen Função para atualização da tela de listagem das
   * despesas.
   * @param parentComponent Componente pai.
   * @param screenTitle Título da tela de gerenciamento de despesas.
   * @param nameField Campo de nome.
   * @param valueField Campo de valor.
   * @param descriptionField Campo de descrição.
   * @param periodSelect Seletor de período.
   * @param actionButton Botão de criação ou atualização.
   */
  public ManagerExpenseController(
          Window parentComponent,
          ExpenseDatabaseController expenseDatabaseController,
          ExpenseModel data,
          Runnable onUpdateScreen,
          JLabel screenTitle,
          JTextField nameField,
          JTextField valueField,
          JTextField descriptionField,
          JComboBox<String> periodSelect,
          JButton actionButton
  ) {
    this.parentComponent = parentComponent;
    this.expenseDatabaseController = expenseDatabaseController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;
    this.nameField = nameField;
    this.valueField = valueField;
    this.descriptionField = descriptionField;
    this.periodSelect = periodSelect;

    // Preenche os campos com os atuais dados da despesa caso a tela seja de atualização.
    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      valueField.setText(String.valueOf(data.getValue()));
      periodSelect.setSelectedItem(data.getPeriod());

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Despesa");
      actionButton.setText("Atualizar");
    }
  }

  // SCREEN ACTIONS
  /**
   * Criação ou atualização de uma despesa.
   */
  protected void actionButton() {
    // Obtenção dos dados.
    String name = nameField.getText();
    String valueText = valueField.getText();
    String description = descriptionField.getText();

    // Verificação da presença dos dados necessários.
    if (name.isEmpty() || valueText.isEmpty() || description.isEmpty()) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);

      return;
    }

    // Validação do valor numérico.
    NumberValidator numberValidator = new NumberValidator();
    if (!numberValidator.validate(parentComponent, valueText, "despesa")) {
      return;
    }

    String period = (String) periodSelect.getSelectedItem();

    // Verifica se a tela é de atualização ou criação.
    if (data != null) {
      // Atualiza os dados da despesa.
      expenseDatabaseController.update(
              new ExpenseModel(
                      data.getId(),
                      name,
                      numberValidator.getNumber(),
                      period,
                      description
              )
      );
    } else {
      // Cria a despesa.
      expenseDatabaseController.create(
              new CreateExpenseModel(
                      name,
                      numberValidator.getNumber(),
                      period,
                      description
              )
      );
    }

    onUpdateScreen.run();

    parentComponent.dispose();
  }
}

package br.edu.ifpb.views.budget.components.manager_budget_item;

import br.edu.ifpb.database.controllers.BudgetItemDatabaseController;
import br.edu.ifpb.models.budget_item.BudgetItemModel;
import br.edu.ifpb.models.budget_item.CreateBudgetItemModel;
import br.edu.ifpb.utils.NumberValidator;
import br.edu.ifpb.utils.WindowClosure;
import java.awt.Window;
import java.time.LocalDate;
import java.util.function.Consumer;
import javax.swing.*;

public class ManagerBudgetItemController {
  private final String budgetId;
  private final Consumer<BudgetItemModel> onUpdateScreen;
  private final BudgetItemDatabaseController budgetItemDatabaseController;
  private final BudgetItemModel data;

  private final Window parentComponent;
  private final JTextField nameField;
  private final JTextField valueField;
  private final JTextField descriptionField;
  private final JComboBox<String> periodSelect;

  /**
   * Construtor.
   *
   * @param parentComponent Componente pai.
   * @param budgetId ID do orçamento.
   * @param budgetItemDatabaseController Controlador de itens de orçamento.
   * @param data Dados do registro selecionado caso seja para atualizar.
   * @param onUpdateScreen Função para atualização da tela de listagem das
   * despesas.
   * @param screenTitle Título da tela de gerenciamento de despesas.
   * @param nameField Campo de nome.
   * @param valueField Campo de valor.
   * @param descriptionField Campo de descrição.
   * @param periodSelect Seletor de período.
   * @param actionButton Botão de criação ou atualização.
   */
  public ManagerBudgetItemController(
          Window parentComponent,
          String budgetId,
          BudgetItemDatabaseController budgetItemDatabaseController,
          BudgetItemModel data,
          Consumer<BudgetItemModel> onUpdateScreen,
          JLabel screenTitle,
          JTextField nameField,
          JTextField valueField,
          JTextField descriptionField,
          JComboBox<String> periodSelect,
          JButton actionButton
  ) {
    this.parentComponent = parentComponent;
    this.budgetId = budgetId;
    this.budgetItemDatabaseController = budgetItemDatabaseController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;
    this.nameField = nameField;
    this.valueField = valueField;
    this.descriptionField = descriptionField;
    this.periodSelect = periodSelect;

    // Preenche os campos com os atuais dados do item de orçamento caso a tela seja de atualização.
    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      valueField.setText(String.valueOf(data.getValue()));
      periodSelect.setSelectedItem(data.getPeriod());

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Item de Orçamento");
      actionButton.setText("Atualizar");
    }

    WindowClosure.apply(parentComponent);
  }

  // SCREEN ACTIONS
  /**
   * Criação ou atualização de uma receita.
   */
  protected void actionButton() {
    // Obtenção dos dados.
    String name = nameField.getText();
    String description = descriptionField.getText();
    String valueText = valueField.getText();

    // Verificação da presença dos dados necessários.
    if (name.isEmpty() || valueText.isEmpty() || description.isEmpty()) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);

      return;
    }

    // Validação do valor numérico.
    NumberValidator numberValidator = new NumberValidator();
    if (!numberValidator.validate(parentComponent, valueText, "item de orçamento")) {
      return;
    }

    String periodicity = (String) periodSelect.getSelectedItem();

    BudgetItemModel budgetItem;

    // Verifica se a tela é de atualização ou criação.
    if (data != null) {
      // Atualiza os dados do item de orçamento.
      budgetItem = new BudgetItemModel(
              data.getId(),
              budgetId,
              name,
              description,
              numberValidator.getNumber(),
              periodicity,
              data.getCreatedAt()
      );

      budgetItemDatabaseController.update(budgetItem);
    } else {
      LocalDate createdAt = LocalDate.now();

      // Cria o modelo de criação de um novo item de orçamento.
      CreateBudgetItemModel createBudgetItem = new CreateBudgetItemModel(
              budgetId,
              name,
              description,
              numberValidator.getNumber(),
              periodicity,
              createdAt
      );

      // Registra o novo item de orçamento e obtém o seu ID.
      String budgetItemId = budgetItemDatabaseController.create(createBudgetItem);

      // Cria o modelo de item de orçamento com o ID do registro.
      budgetItem = new BudgetItemModel(
              budgetItemId,
              budgetId,
              name,
              description,
              numberValidator.getNumber(),
              periodicity,
              createdAt
      );
    }

    onUpdateScreen.accept(budgetItem);

    parentComponent.dispose();
  }
}

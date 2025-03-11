package br.edu.ifpb.views.revenues.components.manager_register;

import br.edu.ifpb.database.controllers.RevenueDatabaseController;
import br.edu.ifpb.models.revenue.CreateRevenueModel;
import br.edu.ifpb.models.revenue.RevenueModel;
import br.edu.ifpb.utils.NumberValidator;
import br.edu.ifpb.utils.WindowClosure;
import java.awt.Window;
import javax.swing.*;

public class ManagerRevenueController {
  private final Runnable onUpdateScreen;
  private final RevenueDatabaseController revenueDatabaseController;
  private final RevenueModel data;

  private final Window parentComponent;
  private final JTextField nameField;
  private final JTextField valueField;
  private final JTextField descriptionField;
  private final JComboBox<String> periodSelect;

  /**
   * Construtor.
   *
   * @param revenueDatabaseController Controlador de receitas.
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
  public ManagerRevenueController(
          Window parentComponent,
          RevenueDatabaseController revenueDatabaseController,
          RevenueModel data,
          Runnable onUpdateScreen,
          JLabel screenTitle,
          JTextField nameField,
          JTextField valueField,
          JTextField descriptionField,
          JComboBox<String> periodSelect,
          JButton actionButton
  ) {
    this.parentComponent = parentComponent;
    this.revenueDatabaseController = revenueDatabaseController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;
    this.nameField = nameField;
    this.valueField = valueField;
    this.descriptionField = descriptionField;
    this.periodSelect = periodSelect;

    // Preenche os campos com os atuais dados da receita caso a tela seja de atualização.
    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      valueField.setText(String.valueOf(data.getValue()));
      periodSelect.setSelectedItem(data.getPeriod());

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Receita");
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
    String valueText = valueField.getText();
    String description = descriptionField.getText();

    // Verificação da presença dos dados necessários.
    if (name.isEmpty() || valueText.isEmpty() || description.isEmpty()) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);

      return;
    }

    // Validação do valor numérico.
    NumberValidator numberValidator = new NumberValidator();
    if (!numberValidator.validate(parentComponent, valueText, "receita")) {
      return;
    }

    String period = (String) periodSelect.getSelectedItem();

    // Verifica se a tela é de atualização ou criação.
    if (data != null) {
      // Atualiza os dados da receita.
      revenueDatabaseController.update(
              new RevenueModel(
                      data.getId(),
                      name,
                      description,
                      numberValidator.getNumber(),
                      period
              )
      );
    } else {
      // Cria a receita.
      revenueDatabaseController.create(
              new CreateRevenueModel(
                      name,
                      description,
                      numberValidator.getNumber(),
                      period
              )
      );
    }

    onUpdateScreen.run();

    parentComponent.dispose();
  }
}

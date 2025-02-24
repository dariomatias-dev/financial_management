package org.cafe.views.revenues.components.manager_register;

import javax.swing.JOptionPane;
import org.cafe.database.controllers.RevenueController;
import org.cafe.models.revenue.CreateRevenueModel;
import org.cafe.models.revenue.RevenueModel;

public class ManagerRevenueView extends javax.swing.JFrame {
private final Runnable onUpdateScreen;
  private final RevenueController revenueController;
  private final RevenueModel data;

  /**
   * Construtor.
   *
   * @param revenueController Controlador de receitas.
   * @param data Dados do registro selecionado caso seja para atualizar.
   * @param onUpdateScreen Função para atualização da tela de listagem das
   * despesas.
   */
  public ManagerRevenueView(RevenueController revenueController, RevenueModel data, Runnable onUpdateScreen) {
    this.revenueController = revenueController;
    this.data = data;
    this.onUpdateScreen = onUpdateScreen;

    initComponents();

    if (data != null) {
      nameField.setText(data.getName());
      descriptionField.setText(data.getDescription());
      valueField.setText(String.valueOf(data.getValue()));
      periodLabel.setText(data.getPeriod());

      screenTitle.setFocusable(true);
      screenTitle.setText("Atualizar Despesa");
      actionButton.setText("Atualizar");
    }
  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        descriptionField = new javax.swing.JTextField();
        descriptionLabel = new javax.swing.JLabel();
        actionButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        periodSelect = new javax.swing.JComboBox<>();
        periodLabel = new javax.swing.JLabel();
        screenTitle = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        valueField = new javax.swing.JTextField();
        valueLabell = new javax.swing.JLabel();
        revenueTypeSelect = new javax.swing.JComboBox<>();
        periodLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        descriptionLabel.setBackground(new java.awt.Color(0, 0, 0));
        descriptionLabel.setText("Descrição:");

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

        periodSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diário", "Semanal", "Mensal" }));

        periodLabel.setBackground(new java.awt.Color(0, 0, 0));
        periodLabel.setText("Periodicidade :");

        screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        screenTitle.setText("Criar Receita");

        nameLabel.setBackground(new java.awt.Color(0, 0, 0));
        nameLabel.setText("Nome:");

        valueLabell.setBackground(new java.awt.Color(0, 0, 0));
        valueLabell.setText("Valor:");

        revenueTypeSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Passiva", "Ativa" }));
        revenueTypeSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                revenueTypeSelectActionPerformed(evt);
            }
        });

        periodLabel1.setBackground(new java.awt.Color(0, 0, 0));
        periodLabel1.setText("Tipo de receita:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionField)
                            .addComponent(nameField)
                            .addComponent(valueField)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descriptionLabel)
                                    .addComponent(nameLabel)
                                    .addComponent(valueLabell)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(periodLabel)
                                            .addComponent(periodSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(37, 37, 37)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(revenueTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(periodLabel1))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(actionButton)
                        .addGap(13, 13, 13))))
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(screenTitle)
                .addGap(0, 156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(screenTitle)
                .addGap(18, 18, 18)
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueLabell)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(periodLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(periodSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(periodLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revenueTypeSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(actionButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void actionButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        String name = nameField.getText();
        String valueText = valueField.getText();
        String description = descriptionField.getText();

        if (name.isEmpty() || valueText.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double value;
        try {
            value = Double.parseDouble(valueText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor numérico válido no campo 'Valor'.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String period = (String) periodSelect.getSelectedItem();
        String type = (String) revenueTypeSelect.getSelectedItem();

        if (data != null) {
            RevenueModel revenue = new RevenueModel(data.getId(), name, value, period, description, type);
            revenueController.update(revenue);
        } else {
            CreateRevenueModel revenue = new CreateRevenueModel(name, value, period, description, type);
            revenueController.create(revenue);
        }

        onUpdateScreen.run();

        this.dispose();
    }                                         

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {                                          
        this.dispose();
    }                                         

    private void revenueTypeSelectActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

 

    // Variables declaration - do not modify                     
    private javax.swing.JButton actionButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField descriptionField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel periodLabel;
    private javax.swing.JLabel periodLabel1;
    private javax.swing.JComboBox<String> periodSelect;
    private javax.swing.JComboBox<String> revenueTypeSelect;
    private javax.swing.JLabel screenTitle;
    private javax.swing.JTextField valueField;
    private javax.swing.JLabel valueLabell;
    // End of variables declaration                   
}

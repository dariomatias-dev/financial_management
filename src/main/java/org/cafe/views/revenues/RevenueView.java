package org.cafe.views.revenues;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import org.cafe.database.controllers.RevenueController;
import org.cafe.models.revenue.RevenueModel;
import org.cafe.utils.ConfirmDeleteDialog;
import org.cafe.utils.CurrencyFormatterUtil;
import org.cafe.utils.RecordVerificationUtil;
import org.cafe.utils.SearchFieldHandlerUtil;
import org.cafe.utils.SetBackIcon;
import org.cafe.utils.ValueRangeFilter;
import org.cafe.views.revenues.components.manager_register.ManagerRevenueView;

public class RevenueView extends javax.swing.JFrame {
  private final RevenueController revenueController;
  private ArrayList<RevenueModel> allRevenues;
  private ArrayList<RevenueModel> displayedRevenues;

  /**
   * Construtor.
   *
   * @param revenueController Controlador de receitas.
   */
  public RevenueView(RevenueController revenueController) {
    this.revenueController = revenueController;

    initComponents();

    initializeSearchField();

    listRevenues();

    new SetBackIcon().set(exitButton);
  }

  private void initializeSearchField() {
    screenTitle.setFocusable(true);

    new SearchFieldHandlerUtil(searchField).initialize();
  }

  /**
   * Lista todas as receitas.
   */
  private void listRevenues() {
    allRevenues = revenueController.getAll();
    displayedRevenues = allRevenues;
    showRevenues();
  }

  /**
   * Mostra as informações das receitas.
   */
  private void showRevenues() {
    DefaultTableModel tableModel = (DefaultTableModel) revenuesTable.getModel();
    tableModel.setRowCount(0);

    // Criação das linhas da tabela.
    for (RevenueModel revenue : displayedRevenues) {
      String formattedValue = CurrencyFormatterUtil.format(revenue.getValue());

      Object[] rowData = new Object[4];
      rowData[0] = revenue.getName();
      rowData[1] = revenue.getDescription();
      rowData[2] = formattedValue;
      rowData[3] = revenue.getPeriod();
      tableModel.addRow(rowData);
    }
  }

  /**
   * Atualiza a lista de receitas.
   */
  private void updateScreen() {
    listRevenues();

    search();
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
    addButton = new javax.swing.JButton();
    updateButton = new javax.swing.JButton();
    deleteButton = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    revenuesTable = new javax.swing.JTable();
    periodLabel = new javax.swing.JLabel();
    periodFilterField = new javax.swing.JComboBox<>();
    valueMinFilterLabel = new javax.swing.JLabel();
    valueMinFilterField = new javax.swing.JTextField();
    valueMaxFilterLabel = new javax.swing.JLabel();
    valueMaxFilterField = new javax.swing.JTextField();
    valueFilterLabel = new javax.swing.JLabel();
    searchField = new javax.swing.JTextField();
    searchButton = new javax.swing.JButton();
    exitButton = new javax.swing.JLabel();
    screenTitle = new javax.swing.JLabel();

    jMenu1.setText("jMenu1");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowOpened(java.awt.event.WindowEvent evt) {
        formWindowOpened(evt);
      }
    });

    background.setBackground(new java.awt.Color(255, 255, 255));
    background.setPreferredSize(new java.awt.Dimension(598, 427));

    addButton.setText("Adicionar");
    addButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        addButtonMouseClicked(evt);
      }
    });

    updateButton.setText("Atualizar");
    updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        updateButtonMouseClicked(evt);
      }
    });

    deleteButton.setForeground(new java.awt.Color(255, 0, 51));
    deleteButton.setText("Excluir");
    deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        deleteButtonMouseClicked(evt);
      }
    });

    revenuesTable.setModel(new javax.swing.table.DefaultTableModel(
      new Object [][] {
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null},
        {null, null, null, null}
      },
      new String [] {
        "Nome", "Descrição", "Valor", "Período"
      }
    ) {
      Class[] types = new Class [] {
        java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
      };
      boolean[] canEdit = new boolean [] {
        false, true, true, false
      };

      public Class getColumnClass(int columnIndex) {
        return types [columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit [columnIndex];
      }
    });
    jScrollPane1.setViewportView(revenuesTable);
    if (revenuesTable.getColumnModel().getColumnCount() > 0) {
      revenuesTable.getColumnModel().getColumn(0).setMinWidth(120);
      revenuesTable.getColumnModel().getColumn(0).setPreferredWidth(120);
      revenuesTable.getColumnModel().getColumn(0).setMaxWidth(120);
      revenuesTable.getColumnModel().getColumn(2).setMinWidth(88);
      revenuesTable.getColumnModel().getColumn(2).setPreferredWidth(88);
      revenuesTable.getColumnModel().getColumn(2).setMaxWidth(88);
      revenuesTable.getColumnModel().getColumn(3).setMinWidth(60);
      revenuesTable.getColumnModel().getColumn(3).setPreferredWidth(60);
      revenuesTable.getColumnModel().getColumn(3).setMaxWidth(60);
    }

    periodLabel.setText("Período:");

    periodFilterField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Diário", "Semanal", "Mensal" }));

    valueMinFilterLabel.setText("Mínimo:");

    valueMaxFilterLabel.setText("Máximo");

    valueFilterLabel.setText("Valor:");

    searchButton.setText("Filtrar");
    searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        searchButtonMouseClicked(evt);
      }
    });

    exitButton.setForeground(new java.awt.Color(255, 0, 51));
    exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
      public void mouseClicked(java.awt.event.MouseEvent evt) {
        exitButtonMouseClicked(evt);
      }
    });

    screenTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    screenTitle.setForeground(new java.awt.Color(0, 0, 0));
    screenTitle.setText("Receitas");

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(jScrollPane1)
          .addGroup(backgroundLayout.createSequentialGroup()
            .addComponent(searchField)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(searchButton))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addComponent(valueMinFilterLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(valueMaxFilterLabel)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(addButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(updateButton)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(deleteButton))
          .addGroup(backgroundLayout.createSequentialGroup()
            .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(valueFilterLabel)
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(periodLabel)
                .addGap(18, 18, 18)
                .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addGroup(backgroundLayout.createSequentialGroup()
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(screenTitle)))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    backgroundLayout.setVerticalGroup(
      backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(backgroundLayout.createSequentialGroup()
        .addGap(16, 16, 16)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(screenTitle))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(searchButton))
        .addGap(1, 1, 1)
        .addComponent(valueFilterLabel)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(valueMinFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMinFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(valueMaxFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(periodLabel)
          .addComponent(periodFilterField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(deleteButton)
          .addComponent(updateButton)
          .addComponent(addButton))
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
      .addComponent(background, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    }//GEN-LAST:event_formWindowOpened

  /**
   * Abre a tela de criação de uma receita.
   */
  private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
    new ManagerRevenueView(
            revenueController,
            null,
            this::updateScreen
    ).setVisible(true);
  }//GEN-LAST:event_addButtonMouseClicked

  /**
   * Abre a tela de atualização da receita selecionada.
   */
  private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
    if (RecordVerificationUtil.verifyRecords(revenuesTable, "atualizar")) {
      RevenueModel selectedRevenue = displayedRevenues.get(revenuesTable.getSelectedRow());
      new ManagerRevenueView(
              revenueController,
              selectedRevenue,
              this::updateScreen
      ).setVisible(true);
    }
  }//GEN-LAST:event_updateButtonMouseClicked

  /**
   * Remove a receita selecionada.
   */
  private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
    ConfirmDeleteDialog.showDeleteConfirmation(
            revenuesTable,
            () -> {
              RevenueModel selectedRevenue = displayedRevenues.get(revenuesTable.getSelectedRow());
              revenueController.removeById(selectedRevenue.getId());

              updateScreen();
            }
    );
  }//GEN-LAST:event_deleteButtonMouseClicked

  /**
   * Método de pesquisa das despesas.
   */
  private void search() {
    // Obtenção dos filtros.
    String query = searchField.getText().trim();
    String periodFilter = (String) periodFilterField.getSelectedItem();
    String valueMinFilterText = valueMinFilterField.getText().trim();
    String valueMaxFilterText = valueMaxFilterField.getText().trim();

    // Verificação da presença de filtragem.
    if (query.equals("Pesquisar...") && periodFilter.equals("Todos") && valueMinFilterText.isEmpty() && valueMaxFilterText.isEmpty()) {
      displayedRevenues = allRevenues;
      showRevenues();

      return;
    }

    // Obtenção dos filtros de valor mínimo e máximo.
    ValueRangeFilter valueRangeFilter = new ValueRangeFilter();
    if (!valueRangeFilter.validate(this, valueMinFilterText, valueMaxFilterText)) {
      return;
    }

    ArrayList<RevenueModel> results = new ArrayList<>();

    // Filtragem dos orçamentos.
    for (RevenueModel revenue : allRevenues) {
      // Filtro de texto e período.
      boolean matchesQuery = query.equals("Pesquisar...") || revenue.getName().contains(query) || revenue.getDescription().contains(query);
      boolean matchesPeriod = periodFilter.equals("Todos") || revenue.getPeriod().equals(periodFilter);

      // Filtros de valor.
      boolean matchesValue = true;
      if (valueRangeFilter.getApplyValueMinFilter()) {
        matchesValue = revenue.getValue() >= valueRangeFilter.getValueMinFilter();
      }
      if (valueRangeFilter.getApplyValueMaxFilter() && matchesValue) {
        matchesValue = revenue.getValue() <= valueRangeFilter.getValueMaxFilter();
      }

      // Checagem das filtragens.
      if (matchesQuery && matchesPeriod && matchesValue) {
        results.add(revenue);
      }
    }

    displayedRevenues = results;

    showRevenues();
  }

  /**
   * Método chamado para filtrar as receitas de acordo com os filtros definidos.
   */
  private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
    search();
  }//GEN-LAST:event_searchButtonMouseClicked

  /**
   * Método chamado para sair da tela.
   */
  private void exitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseClicked
    this.dispose();
  }//GEN-LAST:event_exitButtonMouseClicked

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JPanel background;
  private javax.swing.JButton deleteButton;
  private javax.swing.JLabel exitButton;
  private javax.swing.JMenu jMenu1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JComboBox<String> periodFilterField;
  private javax.swing.JLabel periodLabel;
  private javax.swing.JTable revenuesTable;
  private javax.swing.JLabel screenTitle;
  private javax.swing.JButton searchButton;
  private javax.swing.JTextField searchField;
  private javax.swing.JButton updateButton;
  private javax.swing.JLabel valueFilterLabel;
  private javax.swing.JTextField valueMaxFilterField;
  private javax.swing.JLabel valueMaxFilterLabel;
  private javax.swing.JTextField valueMinFilterField;
  private javax.swing.JLabel valueMinFilterLabel;
  // End of variables declaration//GEN-END:variables
}

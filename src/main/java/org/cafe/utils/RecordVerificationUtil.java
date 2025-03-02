package org.cafe.utils;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RecordVerificationUtil {
  public static boolean verifyRecords(JTable table, String actionName) {
    // Verifica se existe registros.
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    if (model.getRowCount() == 0) {
      JOptionPane.showMessageDialog(
              null,
              String.format("Não há registros para %s.", actionName),
              "Aviso",
              JOptionPane.WARNING_MESSAGE
      );
      return false;
    }

    // Verifica se um registro foi selecionada.
    int selectedRow = table.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(
              null,
              "Por favor, selecione um registro.",
              "Aviso",
              JOptionPane.WARNING_MESSAGE
      );
      return false;
    }

    return true;
  }
}

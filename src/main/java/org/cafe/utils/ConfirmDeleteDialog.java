package org.cafe.utils;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ConfirmDeleteDialog {
  public static void showDeleteConfirmation(
          JTable table,
          Runnable action
  ) {
    if (RecordVerification.verifyRecords(table, "excluir")) {
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
        action.run();
      }
    }
  }
}

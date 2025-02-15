package org.cafe.utils;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class RecordVerificationUtil {
    public static boolean verifyRecords(JList<?> list, String actionName) {
        // Verifica se existe registros.
        if (((DefaultListModel<?>) list.getModel()).isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    String.format("Não há registros para %s.", actionName),
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }

        // Verifica se um registro foi selecionado.
        int selectedIndex = list.getSelectedIndex();
        if (selectedIndex == -1) {
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

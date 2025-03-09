package org.cafe.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class NumberValidator {
  private double number;

  public double getNumber() {
    return number;
  }

  public boolean validate(Component parentComponent, String value, String term) {
    try {
      number = Double.parseDouble(value);

      if (number <= 0) {
        JOptionPane.showMessageDialog(parentComponent, String.format("O valor para %s deve ser maior que zero.", term), "Erro", JOptionPane.ERROR_MESSAGE);

        return false;
      }

      return true;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, insira valor(es) numérico(s) válido(s).", "Erro", JOptionPane.ERROR_MESSAGE);

      return false;
    }
  }
}

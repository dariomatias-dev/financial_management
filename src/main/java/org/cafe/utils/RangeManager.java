package org.cafe.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class RangeManager {
  private double valueMaxFilter = Double.MAX_VALUE;
  private double valueMinFilter = Double.MIN_VALUE;
  private boolean applyValueMinFilter = false;
  private boolean applyValueMaxFilter = false;

  public double getValueMaxFilter() {
    return valueMaxFilter;
  }

  public double getValueMinFilter() {
    return valueMinFilter;
  }

  public boolean getApplyValueMinFilter() {
    return applyValueMinFilter;
  }

  public boolean getApplyValueMaxFilter() {
    return applyValueMaxFilter;
  }

  public boolean validate(
          Component parentComponent,
          String valueMinFilterText,
          String valueMaxFilterText
  ) {
    try {
      if (!valueMinFilterText.isEmpty()) {
        valueMinFilter = Double.parseDouble(valueMinFilterText);
        applyValueMinFilter = true;
      }
      if (!valueMaxFilterText.isEmpty()) {
        valueMaxFilter = Double.parseDouble(valueMaxFilterText);
        applyValueMaxFilter = true;
      }
      if (applyValueMinFilter && applyValueMaxFilter && valueMinFilter > valueMaxFilter) {
        JOptionPane.showMessageDialog(parentComponent, "O valor mínimo não pode ser maior que o valor máximo.", "Erro", JOptionPane.ERROR_MESSAGE);

        return false;
      }

      return true;
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(parentComponent, "Por favor, insira valores numéricos válidos nos campos de valor.", "Erro", JOptionPane.ERROR_MESSAGE);

      return false;
    }
  }
}

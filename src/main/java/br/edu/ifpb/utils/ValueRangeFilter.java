package br.edu.ifpb.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Gerencia a validação e aplicação dos filtros de valor mínimo e máximo.
 */
public class ValueRangeFilter {
  private final NumberValidator numberValidator = new NumberValidator();

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
      if (!valueMinFilterText.isEmpty() && numberValidator.validate(parentComponent, valueMinFilterText, "valor mínimo")) {
        applyValueMinFilter = true;
        valueMinFilter = numberValidator.getNumber();
      }
      if (!valueMaxFilterText.isEmpty() && numberValidator.validate(parentComponent, valueMaxFilterText, "valor máximo")) {
        applyValueMaxFilter = true;
        valueMaxFilter = numberValidator.getNumber();
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

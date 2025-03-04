package org.cafe.core.formatters;

import java.text.ParseException;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;
import org.cafe.utils.DateFormatter;

/**
 * Classe de máscara de data no formato "dd/MM/yyyy" para aplicação a um campo
 * de texto. (JFormattedTextField).
 */
public class DateMaskFormatter {
  private MaskFormatter maskFormatter;

  /**
   * Construtor.
   */
  public DateMaskFormatter() {
    try {
      maskFormatter = new MaskFormatter("##/##/####");
      maskFormatter.setPlaceholderCharacter('_');
    } catch (ParseException e) {
      System.err.println("Error creating MaskFormatter: " + e.getMessage());
    }
  }

  /**
   * Aplica a máscara de data ao campo de texto.
   *
   * @param field Campo de texto ao qual a máscara será aplicada.
   */
  public void applyMask(JFormattedTextField field) {
    if (maskFormatter != null) {
      try {
        maskFormatter.install(field);
      } catch (Exception e) {
      }

      AbstractDocument doc = (AbstractDocument) field.getDocument();
      doc.setDocumentFilter(new DateDocumentFilter());
    }
  }

  /**
   * Filtro para controlar a inserção, substituição e remoção de texto.
   */
  private class DateDocumentFilter extends DocumentFilter {
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
      if (canInsert(fb)) {
        super.insertString(fb, offset, string, attr);
        formatText(fb);
      }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
      if (text.equals(DateFormatter.PLACEHOLDER_DATE)) {
        fb.replace(0, fb.getDocument().getLength(), DateFormatter.PLACEHOLDER_DATE, null);
        return;
      }

      if (canInsert(fb)) {
        super.replace(fb, offset, length, text, attrs);
        formatText(fb);
      }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
      super.remove(fb, offset, length);
      fb.replace(0, fb.getDocument().getLength(), DateFormatter.PLACEHOLDER_DATE, null);
    }

    private boolean canInsert(FilterBypass fb) throws BadLocationException {
      String text = fb.getDocument().getText(0, fb.getDocument().getLength());
      return text.contains("_");
    }

    private void formatText(FilterBypass fb) throws BadLocationException {
      String text = fb.getDocument().getText(0, fb.getDocument().getLength());
      String formatted = formatDate(text);
      fb.replace(0, fb.getDocument().getLength(), formatted, null);
    }

    private String formatDate(String text) {
      StringBuilder formatted = new StringBuilder();
      for (int i = 0; i < text.length(); i++) {
        char c = text.charAt(i);
        if (Character.isDigit(c)) {
          formatted.append(c);
        }
      }

      while (formatted.length() < 8) {
        formatted.append('_');
      }

      if (formatted.length() >= 2) {
        formatted.insert(2, '/');
      }
      if (formatted.length() >= 5) {
        formatted.insert(5, '/');
      }

      return formatted.toString();
    }
  }
}

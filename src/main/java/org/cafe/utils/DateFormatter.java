package org.cafe.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class DateFormatter {
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

  private static void messageDialog() {
    JOptionPane.showMessageDialog(null, "Por favor, insira a(s) data(s) no formato dia/mês/ano.", "Erro", JOptionPane.ERROR_MESSAGE);
  }

  public static String format(LocalDate date) {
    if (date == null) {
      return "";
    }

    try {
      return date.format(DATE_FORMATTER);
    } catch (Exception e) {
      System.err.println("Erro ao formatar a data: " + e.getMessage());
      messageDialog();

      return "";
    }
  }

  public static LocalDate parse(String dateString) {
    if (dateString == null || dateString.trim().isEmpty()) {
      messageDialog();

      return null;
    }

    try {
      return LocalDate.parse(dateString, DATE_FORMATTER);
    } catch (Exception e) {
      System.err.println("Erro ao converter a string para LocalDate: " + e.getMessage());
      messageDialog();

      return null;
    }
  }
}

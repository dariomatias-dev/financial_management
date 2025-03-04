package org.cafe.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

public class DateFormatter {
  public static final String PLACEHOLDER_DATE = "__/__/____";
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  public static DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private static void messageDialog() {
    JOptionPane.showMessageDialog(null, "Por favor, insira a(s) data(s) no formato dia/mês/ano.", "Erro", JOptionPane.ERROR_MESSAGE);
  }

  // Formata a data no formato dd/MM/yyyy.
  public static String format(LocalDate date) {
    if (date == null) {
      return null;
    }

    try {
      return date.format(DATE_FORMATTER);
    } catch (Exception e) {
      System.err.println("Erro ao formatar a data: " + e.getMessage());
      messageDialog();

      return null;
    }
  }

  // Formata a data no formato ISO (yyyy-MM-dd).
  public static String formatISO(LocalDate date) {
    if (date == null) {
      return null;
    }

    try {
      return date.format(ISO_DATE_FORMATTER);
    } catch (Exception e) {
      System.err.println("Erro ao formatar a data no formato ISO: " + e.getMessage());
      messageDialog();

      return null;
    }
  }

  // Converte a string no formato dd/MM/yyyy para LocalDate.
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

  // Converte a string no formato ISO (yyyy-MM-dd) para LocalDate.
  public static LocalDate parseISO(String dateString) {
    if (dateString == null || dateString.trim().isEmpty()) {
      messageDialog();

      return null;
    }

    try {
      return LocalDate.parse(dateString, ISO_DATE_FORMATTER);
    } catch (Exception e) {
      System.err.println("Erro ao converter a string para LocalDate no formato ISO: " + e.getMessage());
      messageDialog();

      return null;
    }
  }
}

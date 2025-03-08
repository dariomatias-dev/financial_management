package org.cafe.utils;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;

public class SearchFieldHandler {
  private final JTextField searchField;

  public SearchFieldHandler(JTextField searchField) {
    this.searchField = searchField;
  }

  public void initialize() {
    searchField.setText("Pesquisar...");

    searchField.addFocusListener(new FocusListener() {
      @Override
      public void focusGained(FocusEvent e) {
        if (searchField.getText().equals("Pesquisar...")) {
          searchField.setText("");
        }
      }

      @Override
      public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
          searchField.setText("Pesquisar...");
        }
      }
    });
  }
}

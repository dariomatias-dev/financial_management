package br.edu.ifpb.utils;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

// Reescreve comportamentos padrões adicionados pelo NetBeans.
public class BehaviorOverride {
  public static void apply(Window parentComponent) {
    // Fecha a janela de maneira individual.
    JFrame frame = (JFrame) parentComponent;
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        frame.dispose();
      }
    });

    // Centraliza a tela.
    parentComponent.setLocationRelativeTo(null);
  }
}

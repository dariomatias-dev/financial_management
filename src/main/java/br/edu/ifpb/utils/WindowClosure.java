package br.edu.ifpb.utils;

import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class WindowClosure {
  public static void apply(Window parentComponent) {
      JFrame frame = (JFrame) parentComponent;
      frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
          frame.dispose();
        }
      });
  }
}

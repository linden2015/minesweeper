package com.linden2015.Minesweeper;

import com.linden2015.Minesweeper.Ui.MainFrame;
import javax.swing.SwingUtilities;

public class App implements Runnable {
    public static String EOL = System.getProperty("line.separator");

    private MainFrame mainFrame;

    @Override
    public void run() {
        mainFrame = new MainFrame();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
    }
}

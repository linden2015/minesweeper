package com.linden2015.Minesweeper.Ui;

import com.linden2015.Minesweeper.Domain.Field;
import com.linden2015.Minesweeper.Domain.MsField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        buildUI();
    }

    private void buildUI() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 800));
        setLocationRelativeTo(null);
        buildContentPanel();
        setVisible(true);
    }

    private void buildContentPanel() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        StartResetButton startResetButton = new StartResetButton();
        startResetButton.addMouseListener(new StartResetButtonMouseListener());
        contentPanel.add(startResetButton, BorderLayout.NORTH);

        MsField field = new MsField(16, 16, 40);

        FieldPanel fieldPanel = new FieldPanel(field);
        field.addObserver(fieldPanel);
        contentPanel.add(fieldPanel, BorderLayout.CENTER);

        FieldStatusLabel fieldStatusLabel = new FieldStatusLabel();
        field.addObserver(fieldStatusLabel);
        contentPanel.add(fieldStatusLabel, BorderLayout.SOUTH);

        add(contentPanel);
    }

    private void rebuildUI()
    {
        remove(contentPanel);
        buildContentPanel();
        validate();
        repaint();
    }

    private class StartResetButtonMouseListener extends MouseAdapter
    {
        @Override
        public void mousePressed(final MouseEvent e) {
            rebuildUI();
        }
    }
}

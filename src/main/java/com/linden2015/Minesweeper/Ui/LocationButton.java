package com.linden2015.Minesweeper.Ui;

import javax.swing.JButton;

public class LocationButton extends JButton {

    private int colPosition;

    private int rowPosition;

    public LocationButton(int colPosition, int rowPosition, String text) {
        super(text);
        this.colPosition = colPosition;
        this.rowPosition = rowPosition;
    }

    public int colPosition() {
        return colPosition;
    }

    public int rowPosition() {
        return rowPosition;
    }
}

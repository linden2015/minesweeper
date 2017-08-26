package com.linden2015.Minesweeper.Ui;

import com.linden2015.Minesweeper.Domain.Field;
import com.linden2015.Minesweeper.Domain.FieldStatus;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

public class FieldStatusLabel extends JLabel implements Observer {

    public FieldStatusLabel() {
        super(FieldStatus.UNCLEARED.text(), CENTER);
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (o instanceof Field) {
            setText(
                ((Field)o).status().text()
            );
        }
    }
}

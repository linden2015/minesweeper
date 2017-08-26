package com.linden2015.Minesweeper.Ui;

import static com.linden2015.Minesweeper.App.EOL;
import com.linden2015.Minesweeper.Domain.Field;
import com.linden2015.Minesweeper.Domain.Location;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

public class FieldPanel extends JPanel implements Observer {

    private Field field;

    private List<LocationButton> locationButtons;

    public FieldPanel(Field field) {
        this.field = field;
        buildUI();
    }

    private void buildUI() {
        setLayout(
            new GridLayout(field.numberOfRows(), field.numberOfColumns())
        );
        locationButtons = new ArrayList<>();
        for (int c = 0; c < field.numberOfColumns(); c++) {
            for (int r = 0; r < field.numberOfRows(); r++) {
                LocationButton locationButton = new LocationButton(c, r, "?");
                locationButton.addMouseListener(new LocationButtonMouseListener());
                locationButtons.add(locationButton);
                add(locationButton);
            }
        }
    }

    private class LocationButtonMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(final MouseEvent e) {
            LocationButton locationButton = (LocationButton) e.getSource();
            field.stepOnLocationByCoordinate(
                locationButton.colPosition(), locationButton.rowPosition()
            );
        }
    }

    private LocationButton getLocationButtonByCoordinate(int column, int row) {
        for (LocationButton locationButton : locationButtons) {
            if (
                locationButton.colPosition() == column &&
                locationButton.rowPosition() == row
            ) {
                return locationButton;
            }
        }
        return null;
    }

    @Override
    public void update(final Observable o, final Object arg) {
        if (arg instanceof Location) {
            Location location = (Location) arg;
            LocationButton locationButton = getLocationButtonByCoordinate(
                location.column(), location.row()
            );
            locationButton.setEnabled(false);
            if (location.isMine()) {
                locationButton.setText("*");
            } else {
                locationButton.setText(
                    String.valueOf(field.surroundingMinesCount(location))
                );
            }
        }
    }
}

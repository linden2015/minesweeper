package com.linden2015.Minesweeper.Domain;

import java.util.List;

public interface Field {

    FieldStatus status();

    List<Location> locations();

    int numberOfColumns();

    int numberOfRows();

    void stepOnLocationByCoordinate(int column, int row);

    int surroundingMinesCount(Location location);
}

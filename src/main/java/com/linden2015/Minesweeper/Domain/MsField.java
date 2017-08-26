package com.linden2015.Minesweeper.Domain;

import static com.linden2015.Minesweeper.App.EOL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

public class MsField extends Observable implements Field {

    private List<Location> locations;

    private int numberOfColumns;

    private int numberOfRows;

    private int numberOfMines;

    public MsField(
        int numberOfColumns, int numberOfRows, int numberOfMines
    ) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.numberOfMines = numberOfMines;
        generateMines();
    }

    private void generateMines() {
        // Create a bag of booleans representing a mine or not.
        // Add all the mines, then add all the non-mines.
        // Shuffle list.
        // Apply bag items one by one to grid of location elements.
        List<Boolean> shuffledMaybeMineBag = new ArrayList<>();
        for (int i = 0; i < numberOfMines; i++) {
            shuffledMaybeMineBag.add(true);
        }
        for (int i = 0; i < (numberOfLocations() - numberOfMines); i++) {
            shuffledMaybeMineBag.add(false);
        }
        Collections.shuffle(shuffledMaybeMineBag);
        int bagCounter = 0;
        locations = new ArrayList<>();
        for (int i = 0; i < numberOfColumns; i++) {
            for (int j = 0; j < numberOfRows; j++) {
                locations.add(
                    new MsLocation(i, j, shuffledMaybeMineBag.get(bagCounter))
                );
                bagCounter++;
            }
        }
    }

    private int numberOfLocations() {
        return numberOfColumns * numberOfRows;
    }

    @Override
    public FieldStatus status() {
        boolean anyUnCleared = false;
        boolean anyHit = false;
        for (Location location : locations) {
            if (location.isStepped()) {
                if (location.isMine()) {
                    return FieldStatus.HIT;
                }
            } else {
                if (!location.isMine()) {
                    anyUnCleared = true;
                }
            }
        }
        if (anyUnCleared) {
            return FieldStatus.UNCLEARED;
        } else {
            return FieldStatus.CLEARED;
        }
    }

    @Override
    public List<Location> locations() {
        return locations;
    }

    @Override
    public int numberOfColumns() {
        return numberOfColumns;
    }

    @Override
    public int numberOfRows() {
        return numberOfRows;
    }

    @Override
    public void stepOnLocationByCoordinate(int column, int row) {
        Location location = getLocationByCoordinate(column, row);
        if (location.isStepped()) {
            return;
        }
        location.stepOn();
        setChanged();
        notifyObservers(location);
        revealSurroundingLocations(location);
    }

    private void revealSurroundingLocations(Location qLocation) {
        if (surroundingMinesCount(qLocation) != 0) {
            return;
        }
        for (Location location : locations) {
            if (location.isStepped()) {
                continue;
            }
            if (!location.isAdjacentTo(qLocation)) {
                continue;
            }
            location.stepOn();
            setChanged();
            notifyObservers(location);
            if (surroundingMinesCount(location) == 0) {
                revealSurroundingLocations(location);
            }
        }
    }

    @Override
    public int surroundingMinesCount(Location qLocation) {
        List<Location> surroundingLocations = getSurroundingLocations(qLocation);
        int minesCount = 0;
        for (Location location : surroundingLocations) {
            if (location.isMine()) {
                minesCount++;
            }
        }
        return minesCount;
    }

    private List<Location> getSurroundingLocations(Location qLocation) {
        List<Location> surroundingLocations = new ArrayList<>();
        for (Location location : locations) {
            if (location.isAdjacentTo(qLocation)) {
                surroundingLocations.add(location);
            }
        }
        return surroundingLocations;
    }

    private Location getLocationByCoordinate(int column, int row) {
        for (Location location : locations) {
            if (location.column() == column && location.row() == row) {
                return location;
            }
        }
        return null;
    }
}

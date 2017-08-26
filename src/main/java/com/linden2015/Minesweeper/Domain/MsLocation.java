package com.linden2015.Minesweeper.Domain;

import java.util.Random;

public class MsLocation implements Location {

    private boolean isStepped = false;

    private final boolean isMine;

    private int randomIdentity;

    private int column;

    private int row;

    public MsLocation(int column, int row, final boolean isMine) {
        this.column = column;
        this.row = row;
        this.isMine = isMine;
        generateRandomIdentity();
    }

    private void generateRandomIdentity() {
        randomIdentity = new Random().nextInt(1000);
    }

    @Override
    public boolean isMine() {
        return isMine;
    }

    @Override
    public boolean isStepped() {
        return isStepped;
    }

    @Override
    public int randomIdentity() {
        return randomIdentity;
    }

    @Override
    public void stepOn() {
        if (isStepped) {
            return;
        }
        isStepped = true;
    }

    @Override
    public int column() {
        return column;
    }

    @Override
    public int row() {
        return row;
    }

    @Override
    public boolean equals(Object obj) {
        return
            obj instanceof Location &&
            column == ((Location) obj).column() &&
            row == ((Location) obj).row();
    }

    @Override
    public boolean isAdjacentTo(Location otherLocation) {
        if (equals(otherLocation)) {
            return false;
        }
        return
            Math.abs(column - otherLocation.column()) <= 1 &&
            Math.abs(row - otherLocation.row()) <= 1;
    }
}

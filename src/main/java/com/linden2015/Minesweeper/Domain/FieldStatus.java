package com.linden2015.Minesweeper.Domain;

public enum FieldStatus {
    UNCLEARED("Uncleared"), HIT("Hit: you lost"), CLEARED("Cleared");

    private String text;

    FieldStatus(String text) {
        this.text = text;
    }

    public String text() {
        return text;
    }
}

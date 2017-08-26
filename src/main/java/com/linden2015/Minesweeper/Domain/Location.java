package com.linden2015.Minesweeper.Domain;

public interface Location {

    public boolean isMine();

    public boolean isStepped();

    public int randomIdentity();

    public void stepOn();

    public int column();

    public int row();

    public boolean isAdjacentTo(Location l);
}

package com.codegym.games.minesweeper;

public class GameObject {

    public int x;
    public int y;
    public boolean isMine;
    public int countMineNeighbors;

    public GameObject (int xCor, int yCor, boolean cellStatus) {
        x = xCor;
        y = yCor;
        isMine = cellStatus;
    }

    // Getter method for getting cell status
    public boolean getCellStatus() {
        return isMine;
    }

    // Setter method for setting number of adjacent mined cells
    public void setCountMineNeighbors(int numberOfMinesNeighboring) {
        countMineNeighbors = numberOfMinesNeighboring;
    }

}

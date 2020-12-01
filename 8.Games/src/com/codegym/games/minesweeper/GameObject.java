package com.codegym.games.minesweeper;

public class GameObject {

    public int x;
    public int y;
    public boolean isMine;
    public int countMineNeighbors;
    public boolean isOpen;
    public boolean isFlag;

    public GameObject (int xCor, int yCor, boolean cellStatus) {
        x = xCor;
        y = yCor;
        isMine = cellStatus;
    }

    // Getter method for getting cell status
    public boolean getCellStatus() {
        return isMine;
    }

    // Getter method for getting number of adjacent mines
    public int getNumberOfAdjacentMines() {
        return countMineNeighbors;
    }

    // Setter method for setting number of adjacent mined cells
    public void setCountMineNeighbors(int numberOfMinesNeighboring) {
        countMineNeighbors = numberOfMinesNeighboring;
    }


    // Setter method for setting isOpen to true
    public void setIsOpen() {
        isOpen = true;
    }
}

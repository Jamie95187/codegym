package com.codegym.games.minesweeper;

public class GameObject {

    public int x;
    public int y;
    public boolean isMine;
    public int countMineNeighbors;
    public boolean isOpen = false;
    public boolean isFlag = false;

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

    // Getter method for getting y coordinate
    public int getY() {
        return y;
    }

    // Getter method for getting x coordinate
    public int getX() {
        return x;
    }

    // Getter method for isFlag
    public boolean getFlagStatus() {
        return isFlag;
    }

    // Getter method for get is open
    public boolean getIsOpen() {
        return isOpen;
    }

    // Setter method for setting number of adjacent mined cells
    public void setCountMineNeighbors(int numberOfMinesNeighboring) {
        countMineNeighbors = numberOfMinesNeighboring;
    }

    // Setter method for setting isOpen to true
    public void setIsOpen() {
        isOpen = true;
    }

    // Setter method for setting isFlag to true
    public void setFlag(boolean status) {
        isFlag = status;
    }
}

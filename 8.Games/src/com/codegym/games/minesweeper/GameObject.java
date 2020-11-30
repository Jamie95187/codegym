package com.codegym.games.minesweeper;

public class GameObject {

    public int x;
    public int y;
    public boolean isMine;

    public GameObject (int xCor, int yCor, boolean mineStatus) {
        x = xCor;
        y = yCor;
        isMine = mineStatus;
    }

}

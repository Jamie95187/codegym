package com.codegym.games.minesweeper;
import com.codegym.engine.cell.*;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;
    private int countMinesOnField = 0;

    private GameObject[][] gameField = new GameObject[SIDE][SIDE];

    public void initialize(){
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {
                if (getRandomNumber(10) == 1) {
                    gameField[y][x] = new GameObject(x, y, true);
                    countMinesOnField++;
                } else {
                    gameField[y][x] = new GameObject(x, y, false);
                }
                setCellColor(x, y, Color.ORANGE);
            }
        }
    }

}

package com.codegym.games.minesweeper;
import com.codegym.engine.cell.*;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;

    private GameObject[][] gameField = new GameObject[SIDE][SIDE];

    public void initialize(){
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for(int x = 0; x < SIDE; x++) {
            for(int y = 0; y < SIDE; y++) {
                gameField[y][x] = new GameObject(x, y);
                setCellColor(x, y, Color.ORANGE);
            }
        }
    }

}

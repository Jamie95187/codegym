package com.codegym.games.minesweeper;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private int countFlags;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }

    private void countMineNeighbors() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                if (gameField[y][x].getCellStatus() == false) {
                    int minedNeighbors = 0;
                    List<GameObject> result = getNeighbors(gameField[y][x]);
                    for (int i = 0; i < result.size(); i++) {
                        if (result.get(i).getCellStatus()) {
                            minedNeighbors++;
                        }
                    }
                    gameField[y][x].setCountMineNeighbors(minedNeighbors);
                }
            }
        }
    }

    private void openTile(int x, int y) {
        if (gameField[y][x].getCellStatus()) {
            gameField[y][x].setIsOpen();
            setCellValue(x, y, MINE);
        } else {
            gameField[y][x].setIsOpen();
            // No mined neighbors, recursively call openTile on each neighbor
            if (gameField[y][x].countMineNeighbors == 0) {
                setCellValue(x, y, "");
                List<GameObject> result = getNeighbors(gameField[y][x]);
                for (GameObject cell : result) {
                    if (cell.getIsOpen() == false) {
                        openTile(cell.getX(), cell.getY());
                    }
                }
            } else {
                gameField[y][x].setIsOpen();
                setCellNumber(x, y, gameField[y][x].getNumberOfAdjacentMines());
            }
        }
        setCellColor(x, y, Color.GREEN);
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        super.onMouseLeftClick(x, y);
        openTile(x, y);
    }
}
package com.codegym.games.minesweeper;

import com.codegym.engine.cell.Color;
import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    // Constants for for game grid
    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";

    // Field variables
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField = 0;
    private int countFlags;
    private int countClosedTiles = SIDE*SIDE;
    private int score = 0;
    private boolean isGameStopped;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                setCellValue(x, y, "");
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
        GameObject cell = gameField[y][x];
        if (cell.getIsOpen() || cell.getFlagStatus() || isGameStopped) {
            return;
        }
        if (cell.getCellStatus()) {
            countClosedTiles--;
            cell.setIsOpen();
            setCellValue(x, y, MINE);
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        } else {
            // No mined neighbors, recursively call openTile on each neighbor
            if (cell.countMineNeighbors == 0) {
                setCellValue(x, y, "");
                List<GameObject> result = getNeighbors(cell);
                for (GameObject currentCell : result) {
                    if (currentCell.getIsOpen() == false) {
                        openTile(currentCell.getX(), currentCell.getY());
                    }
                }
            } else {
                setCellNumber(x, y, cell.getNumberOfAdjacentMines());
            }
            countClosedTiles--;
            cell.setIsOpen();
            score = score + 5;
            setScore(score);
            if (countClosedTiles == countMinesOnField) {
                win();
                return;
            }
        }
        setCellColor(x, y, Color.GREEN);
    }

    private void markTile(int x, int y) {
        if(isGameStopped) {
            return;
        }
        GameObject cell = gameField[y][x];
        if (cell.getIsOpen() == false && countFlags > 0) {
            if (cell.getFlagStatus() == false) {
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.YELLOW);
                cell.setFlag(true);
            } else {
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.ORANGE);
                cell.setFlag(false);
            }
        }
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "GAME OVER! YOU LOSE", Color.WHITE, 30);
    }

    private void win(){
        isGameStopped = true;
        showMessageDialog(Color.WHITE, "CONGRATULATIONS YOU WON!", Color.LIGHTGOLDENRODYELLOW, 30);
    }

    private void restart(){
        isGameStopped = false;
        countClosedTiles = SIDE*SIDE;
        score = 0;
        setScore(score);
        countMinesOnField = 0;
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        super.onMouseLeftClick(x, y);
        if(isGameStopped) {
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        super.onMouseRightClick(x, y);
        markTile(x, y);
    }
}
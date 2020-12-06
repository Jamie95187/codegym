package com.codegym.games.snake;

import com.codegym.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class Snake {

    private List<GameObject> snakeParts = new ArrayList<>();

    public Snake(int xCor, int yCor) {
        GameObject snakeHead = new GameObject(xCor, yCor);
        GameObject snakeBody = new GameObject(xCor + 1, yCor);
        GameObject snakeTail = new GameObject(xCor + 2, yCor);

        snakeParts.add(snakeHead);
        snakeParts.add(snakeBody);
        snakeParts.add(snakeTail);
    }

}

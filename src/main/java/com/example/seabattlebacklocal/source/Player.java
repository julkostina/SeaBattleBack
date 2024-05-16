package com.example.seabattlebacklocal.source;

import java.util.Dictionary;

import com.example.seabattlebacklocal.source.StrategyPattern.RandomPlacementStrategy;


public class Player {
    enum Placement {
        RANDOM,
        CUSTOM
    }

    private Dictionary<String, String> data = null;
    private String name;
    private GameBoard gameBoard;
    private int miss = 0;
    private int hit = 0;
    private Serialization serialization;

    public Player(String name, GameBoard gameBoard) {
        data = serialization.readFile();
        this.SetName(name);
        this.gameBoard = gameBoard;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    public String getName() {
        return name;
    }

    public void SetName(String value) {
        this.name = value;
        data.put("player" + data.get("turn"), this.name);
        updateBoard();
    }

    public int getMiss() {
        return miss;
    }

    public int getHit() {
        return hit;
    }

    public void makeMove(Coordinate target) {
        if (gameBoard.getBoard()[target.getRow()][target.getColumn()] == 0) {
            gameBoard.getBoard()[target.getRow()][target.getColumn()] = 3;
            miss++;
            data.put("miss" + data.get("turn"), String.valueOf(this.miss));
        } else if (gameBoard.getBoard()[target.getRow()][target.getColumn()] == 1) {
            gameBoard.getBoard()[target.getRow()][target.getColumn()] = 2;
            hit++;
            data.put("hit" + data.get("turn"), String.valueOf(this.hit));
        }
        gameBoard.makeMove(target);
        updateBoard();
    }

    private void updateBoard() {
        serialization.writeFile(data);
    }

    public void placeShips(Placement placement) {
        if (placement == Placement.RANDOM) {
            data.put(data.get("placementStrategy"+data.get("turn")), "RANDOM");
            RandomPlacementStrategy placementStrategy  = new RandomPlacementStrategy();
            placementStrategy.placeShips(this);
            data.put(data.get("placed"+data.get("turn")), "true");
        }
        else{
            data.put(data.get("placementStrategy"+data.get("turn")), "CUSTOM");
            data.put(data.get("placed"+data.get("turn")), "true");
        }
        this.updateBoard();
    }
}

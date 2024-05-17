package com.example.seabattlebacklocal.source;

import java.util.Dictionary;
import java.util.Hashtable;

import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.StrategyPattern.RandomPlacementStrategy;


public class Player {
    public enum Placement {
        RANDOM,
        CUSTOM
    }

    private String name;
    private GameBoard gameBoard;
    private int miss = 0;
    private int hit = 0;

    public Player(String name, GameBoard gameBoard) {
        this.name = name;
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
    }

    public int getMiss() {
        return miss;
    }

    public int getHit() {
        return hit;
    }

    public Dictionary<String, Integer> makeMove(Coordinate target,int miss, int hit,  int turn) {
        Dictionary<String, Integer> data = new Hashtable<String, Integer>();
        data.put("miss", miss);
        data.put("hit", hit);
        if(gameBoard.isHit(target.getRow(), target.getColumn()) || gameBoard.isMiss(target.getRow(), target.getColumn())) {
            return data;
        }
        if (gameBoard.isEmpty(target.getRow(), target.getColumn())) {
            gameBoard.setMiss(target.getRow(), target.getColumn());
            miss++;
            data.put("miss" + turn,this.miss);
        } 
        if (gameBoard.isShip(target.getRow(), target.getColumn())) {
            gameBoard.setHit(target.getRow(), target.getColumn());
            hit++;
            data.put("hit" + turn, this.hit);
        }
        gameBoard.makeMove(target);
        return data;
    }



    public Dictionary<String, String> placeShips(Placement placement, int turn) {
        Dictionary<String, String> data = new Hashtable<String, String>();
        if (placement == Placement.RANDOM) {
            data.put("placementStrategy"+turn, "RANDOM");
            RandomPlacementStrategy placementStrategy  = new RandomPlacementStrategy();
            placementStrategy.placeShips(this);
            data.put(data.get("placed"+turn), "true");
        }
        else{
            data.put("placementStrategy"+turn,  "CUSTOM");
            data.put("placed"+turn, "true");
        }
        return data;
    }
}

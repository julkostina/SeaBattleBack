package com.example.seabattlebacklocal.source;

import java.util.Dictionary;
import java.util.Hashtable;

import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.StrategyPattern.CustomPlacementStrategy;
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

    public boolean makeMove(Coordinate target, int sizeOfShip) {
        boolean res= true;
        if(sizeOfShip==1){
            if(!gameBoard.isShipNearby(target.getRow(), target.getColumn())){
                gameBoard.makeMove(target);
            }
            else{
                res=false;
            }
        }
        else{}
        return res;
    }

    public Dictionary<String, String> placeShips(Placement placement, int turn) {
        Dictionary<String, String> data = new Hashtable<String, String>();
        if (placement == Placement.RANDOM) {
            data.put("placementStrategy"+turn, "RANDOM");
            RandomPlacementStrategy placementStrategy  = new RandomPlacementStrategy();
            placementStrategy.placeShips(this.getGameBoard());
            data.put(data.get("placed"+turn), "true");
        }
        else{
            data.put("placementStrategy"+turn,  "CUSTOM");
            CustomPlacementStrategy placementStrategy = new CustomPlacementStrategy();
            placementStrategy.placeShips(this.getGameBoard());
            data.put("placed"+turn, "true");
        }
        return data;
    }
}

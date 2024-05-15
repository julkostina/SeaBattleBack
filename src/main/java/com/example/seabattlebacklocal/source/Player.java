package com.example.seabattlebacklocal.source;

import java.io.IOException;
import java.util.Dictionary;

import com.example.seabattlebacklocal.source.StrategyPattern.PlacementStrategy;

public class Player {
    private Dictionary<Character, Character> data=null;
    private String name;
    private GameBoard gameBoard;
    private int miss=0;
    private int hit=0;
    private PlacementStrategy placementStrategy;
    private Serialization serialization;

    public Player(String name, GameBoard gameBoard){
        this.name=name;
        this.gameBoard=gameBoard;
        try {
            data = serialization.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return name;
    }
    public void SetName(String value){
        this.name=value;
        // try{
        //     serialization.writeFile(data);
        // }catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
    public int getMiss(){
        return miss;
    }

    public int getHit(){
        return hit;
    }


    public void makeMove( Coordinate target){
        if(gameBoard.getBoard()[target.getRow()][target.getColumn()]==0){
            gameBoard.getBoard()[target.getRow()][target.getColumn()]=3;
            miss++;
        }
        else if(gameBoard.getBoard()[target.getRow()][target.getColumn()]==1){
            gameBoard.getBoard()[target.getRow()][target.getColumn()]=2;
            hit++;
        }
    }
    public void placeShips(){
        placementStrategy.placeShips(gameBoard, this);
    }

}

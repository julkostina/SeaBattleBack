package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.Ships.Ship;

public abstract class PlacementStrategy {
    public abstract List<Ship> placeShips(GameBoard gameBoard);
    protected void setDistanceAroundShip(Ship ship, GameBoard gameBoard){
        List<Coordinate> coordinates = ship.getCoordinates();
        for(int i =0; i< coordinates.size();i++){
            gameBoard.setDistanceForShip(coordinates.get(i).getRow(), coordinates.get(i).getColumn());
            if(i>0){
                gameBoard.setShip(coordinates.get(i-1).getRow(), coordinates.get(i-1).getColumn());
            }
        }
    }
}

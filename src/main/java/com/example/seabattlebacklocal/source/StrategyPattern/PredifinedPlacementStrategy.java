package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.ArrayList;
import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.Ships.*;

public class PredifinedPlacementStrategy extends PlacementStrategy{
    private int sizeOfBoard=8;
    public void setSize(int size){
        this.sizeOfBoard=size;
    }
    @Override
    public List<Ship> placeShips(GameBoard gameBoard) {
        // For testing, we'll just place all the ships along the top row of the board
        List<Ship> ships = new ArrayList<>(); 
        for(int i=0; i<4; i++){
            ships.add(new OneDeckShip(createCoordinates(1, 0,i)));
        }
        for(int i=0; i<3; i++){
            ships.add(new TwoDeckShip(createCoordinates(2, 2,i)));
        }
        for(int i=0; i<2; i++){
            ships.add(new ThreeDeckShip(createCoordinates(2, 4,i)));
        }
        ships.add(new FourDeckShip(createCoordinates(4, 6,0))); 
        for (Ship ship : ships) {
            for (int i = 0; i < ship.getSize(); i++) {
                gameBoard.placeShip(ship,ship.getCoordinates().get(0), ship.getCoordinates().get(1));
                this.setDistanceAroundShip(ship, gameBoard);
            }
        }
        return ships;
    }

    private List<Coordinate> createCoordinates(int size, int row, int col){
        List<Coordinate> coordinates = new ArrayList<>();
        for(int i = 0; i < size; i++){
            coordinates.add(new Coordinate(row, col, sizeOfBoard));
        }
        return coordinates;
    }
}
    


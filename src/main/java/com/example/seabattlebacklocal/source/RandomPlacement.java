package com.example.seabattlebacklocal.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.GameBoard;
import com.example.seabattlebacklocal.source.FactoryPattern.*;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class RandomPlacement  {
    private Random random = new Random();
    private int sizeOfBoard=8;
    enum SHIPTYPE {
        ONE_DECK, TWO_DECK, THREE_DECK, FOUR_DECK
    }

    public void setSize(int size){
        this.sizeOfBoard=size;
    }
    public List<Ship> placeShips( GameBoard gameBoard) {
        List<Ship> ships = new ArrayList<>();
        for (SHIPTYPE type : SHIPTYPE.values()) {
            Ship ship;
            List<Coordinate> coordinates = new ArrayList<Coordinate>();
            coordinates.add(new Coordinate(0, 0, sizeOfBoard));
            ShipFactory factory;
                switch (type) {
                    case ONE_DECK:
                        factory = new OneDeckFactory();
                        break;
                    case TWO_DECK:
                        factory = new TwoDeckFactory();
                        break;
                    case THREE_DECK:
                        factory = new ThreeDeckFactory();
                        break;
                    case FOUR_DECK:
                        factory = new FourDeckFactory();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid ship type");
                
            }
            ship = factory.createShip(coordinates);
            boolean placed=false;
            while (placed==false) {
                int startRow = random.nextInt(gameBoard.getSize());
                int startColumn = random.nextInt(gameBoard.getSize());
                int endRow = startRow;
                int endColumn = startColumn;
                if (random.nextBoolean()) {
                    if (startRow + ship.getSize() - 1 < gameBoard.getSize()) {
                        endRow += ship.getSize() - 1;
                    } else {
                        startRow -= ship.getSize() - 1;
                    }
                }
                else {
                    if (startColumn + ship.getSize() - 1 < gameBoard.getSize()) {
                        endColumn += ship.getSize() - 1;
                    } else {
                        startColumn -= ship.getSize() - 1;
                    }
                }
                placed=true;
                Coordinate start= new Coordinate();
                Coordinate end=new Coordinate();
                try{
                     start = new Coordinate(startRow, startColumn,sizeOfBoard );
                     end = new Coordinate(endRow, endColumn, sizeOfBoard);
                }
                catch (Exception e){
                    placed=false;
                }
                ship.getCoordinates().set(0,start);
                ship.getCoordinates().set(ship.getSize()-1,end);
                gameBoard.placeShip(ship);
            }
            ships.add(ship);
            this.setDistanceAroundShip(ship, gameBoard);
        }
        return ships;
    }
    private void setDistanceAroundShip(Ship ship, GameBoard gameBoard){
        List<Coordinate> coordinates = ship.getCoordinates();
        for(int i =0; i< coordinates.size();i++){
            gameBoard.setDistanceForShip(coordinates.get(i).getRow(), coordinates.get(i).getColumn());
            if(i>0){
                gameBoard.setShip(coordinates.get(i-1).getRow(), coordinates.get(i-1).getColumn());
            }
        }
    }
}
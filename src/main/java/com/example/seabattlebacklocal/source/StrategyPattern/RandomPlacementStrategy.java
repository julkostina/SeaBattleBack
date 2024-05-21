package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.FactoryPattern.*;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class RandomPlacementStrategy extends PlacementStrategy {
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
            boolean placed = false;
            while (!placed) {
                int startRow = random.nextInt(gameBoard.getSize());
                int startColumn = random.nextInt(gameBoard.getSize());
                int endRow = startRow;
                int endColumn = startColumn;
                // If the ship is vertical, adjust the end row
                if (random.nextBoolean()) {
                    if (startRow + ship.getSize() - 1 < gameBoard.getSize()) {
                        endRow += ship.getSize() - 1;
                    } else {
                        startRow -= ship.getSize() - 1;
                    }
                }
                // If the ship is horizontal, adjust the end column
                else {
                    if (startColumn + ship.getSize() - 1 < gameBoard.getSize()) {
                        endColumn += ship.getSize() - 1;
                    } else {
                        startColumn -= ship.getSize() - 1;
                    }
                }
                Coordinate start = new Coordinate(startRow, startColumn,sizeOfBoard );
                Coordinate end = new Coordinate(endRow, endColumn, sizeOfBoard);

                // Try to place the ship. If it doesn't fit, we'll try again with new
                // coordinates.
                if (gameBoard.placeShip(ship, start, end)) {
                    placed = true;
                }
            }
            ships.add(ship);
            this.setDistanceAroundShip(ship, gameBoard);
        }
        return ships;
    }

}
package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.Random;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.GameBoard;
import com.example.seabattlebacklocal.source.Player;
import com.example.seabattlebacklocal.source.FactoryPattern.*;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class RandomPlacementStrategy implements PlacementStrategy {
    private Random random = new Random();

    enum SHIPTYPE {
        ONE_DECK, TWO_DECK, THREE_DECK, FOUR_DECK
    }

    @Override
    public void placeShips(GameBoard gameBoard, Player player) {
        for (SHIPTYPE type : SHIPTYPE.values()) {
            Ship ship;
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
            ship = factory.createShip();
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
                Coordinate start = new Coordinate(startRow, startColumn);
                Coordinate end = new Coordinate(endRow, endColumn);

                // Try to place the ship. If it doesn't fit, we'll try again with new
                // coordinates.
                if (gameBoard.placeShip(ship, start, end)) {
                    placed = true;
                }
            }
        }
    }
}
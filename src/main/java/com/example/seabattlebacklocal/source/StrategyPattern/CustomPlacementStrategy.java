package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.Dictionary;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.GameBoard;
import com.example.seabattlebacklocal.source.Player;
import com.example.seabattlebacklocal.source.Serialization;
import com.example.seabattlebacklocal.source.Ships.Ship;
import com.example.seabattlebacklocal.source.FactoryPattern.*;

public class CustomPlacementStrategy implements PlacementStrategy {

    @Override
    public void placeShips(GameBoard gameBoard, Player player) {
        Serialization serialization = new Serialization();
        Dictionary<Character, Character> data = null;
        try{data  = serialization.readFile();}
        catch(Exception e){ System.out.println("Error reading file");}
        int playerNumber = data.get("turn");
        int startRow = data.get("");
        int startColumn ;
        int endRow;
        int endColumn;
        Ship ship;
        ShipFactory factory = new OneDeckFactory();// realize
        // Create the ship
        ship = factory.createShip();

        // Place the ship
//        boolean placed = gameBoard.placeShip(ship, new Coordinate(startRow, startColumn), new Coordinate(endRow, endColumn));

        // If the ship couldn't be placed, you could prompt the player to enter new coordinates, or handle it however you want
//        if (!placed) {
//            // Handle failed placement
//        }
    }
}

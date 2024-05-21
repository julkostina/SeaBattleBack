package com.example.seabattlebacklocal.source;

import java.util.ArrayList;
import java.util.List;

import com.example.seabattlebacklocal.source.GameBoard;
import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.GameEngine;
import com.example.seabattlebacklocal.source.FactoryPattern.FourDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.OneDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.ShipFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.ThreeDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.TwoDeckFactory;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class CustomPlacement  {
    GameEngine gameEngine = new GameEngine();
    List<Coordinate> coordinatesForShip = new ArrayList();
    
    public void setGameEngine(GameEngine gameEngine){
        this.gameEngine= gameEngine;
    }
    private Ship setShip(int size){
            ShipFactory factory;
                switch (size) {
                    case 1:
                        factory = new OneDeckFactory();
                        break;
                    case 2:
                        factory = new TwoDeckFactory();
                        break;
                    case 3:
                        factory = new ThreeDeckFactory();
                        break;
                    case 4:
                        factory = new FourDeckFactory();
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid ship type");   
            }
            return factory.createShip(coordinatesForShip);
    }
    
    public void setCoordinate(int x, int y){
        try{
            coordinatesForShip.add(new Coordinate(x,y,gameEngine.getGame().getSizeOfBoard()));
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public List<Ship> placeShips(GameBoard gameBoard) {
        List<Ship> ships = new ArrayList<>();
        int repeats=(gameEngine.getShipSize()%4==0)? 1: gameEngine.getShipSize()%4;
        for(int i=0;i<repeats; i++){
            ships.add(setShip(gameEngine.getShipSize()));
            this.setDistanceAroundShip(ships.get(i), gameBoard);
            if(repeats!=1){
                gameBoard.placeShip(ships.get(i),ships.get(i).getCoordinates().get(0), ships.get(i).getCoordinates().get(ships.get(i).getCoordinates().size()-1));
            }
            else{
                gameBoard.placeShip(ships.get(i),ships.get(i).getCoordinates().get(0), ships.get(i).getCoordinates().get(0));
            }
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

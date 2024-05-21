package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.ArrayList;
import java.util.List;

import com.example.seabattlebacklocal.source.ObserverPattern.EventType;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.ObserverPattern.Updater;
import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.GameEngine;
import com.example.seabattlebacklocal.source.Player;
import com.example.seabattlebacklocal.source.FactoryPattern.FourDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.OneDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.ShipFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.ThreeDeckFactory;
import com.example.seabattlebacklocal.source.FactoryPattern.TwoDeckFactory;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class CustomPlacementStrategy extends PlacementStrategy {
    Updater subscribers = new Updater();
    GameEngine gameEngine = new GameEngine();
    List<Coordinate> coordinatesForShip = new ArrayList();
    
    public void setGameEngine(GameEngine gameEngine){
        this.gameEngine= gameEngine;
        subscribers.addSubscriber(EventType.UPDATE_SHIPS, gameEngine);
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
            subscribers.notifySubscribers(EventType.UPDATE_SHIPS, 1);
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
    @Override
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
}

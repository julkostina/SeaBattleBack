package com.example.seabattlebacklocal.source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import com.example.seabattlebacklocal.source.ObserverPattern.EventType;
import com.example.seabattlebacklocal.source.ObserverPattern.Updater;
import com.example.seabattlebacklocal.source.Player.Placement;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.ObserverPattern.Observer;

public class GameEngine implements Observer{
    private final Dictionary<String, Player> players= new Hashtable<>();
    private SoundPlayer backgroundSound;
    private final Updater subscriber = new Updater();
    private final Serialization serialization = new Serialization();
    private Game data;
    private int shipSize;
    private final Dictionary<String, GameBoard> gameBoards = new Hashtable<>();

    public GameEngine() {
        data = serialization.readFile();
        backgroundSound = SoundPlayer.getInstance();
        backgroundSound.setVolume(data.volume);
        backgroundSound.playSoundContinuously();
    }

    public Game getGame(){
        return data;
    }
    public void setShipSize(int size){
        this.shipSize = size;
    }
    public int getShipSize(){
        return shipSize;
    }
    public Coordinate setCoordinate(int row, int col){
        return new Coordinate(row, col, data.sizeOfBoard);
    }

    public void initGame(String name1, String name2, int size, float volume) {
//        data.volume=volume;
        gameBoards.put("ofPlayer1", new GameBoard(size));
        gameBoards.put("ofPlayer2Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer1Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer2", new GameBoard(size));
        players.put("player1", new Player(name1, gameBoards.get("ofPlayer1")));
        players.put("player2", new Player(name2, gameBoards.get("ofPlayer2")));
//        subscriber.addSubscriber(EventType.UPDATE_TURN,gameBoards.get("ofPlayer1"));
//        subscriber.addSubscriber(EventType.UPDATE_TURN,gameBoards.get("ofPlayer2"));
//        subscriber.addSubscriber(EventType.UPDATE_TURN,gameBoards.get("ofPlayer1Opponent"));
//        subscriber.addSubscriber(EventType.UPDATE_TURN,gameBoards.get("ofPlayer2Opponent"));
    }

    public void updateGame(int x, int y, int sizeOfShip) {
        if(data.turn==1){
            makeAShot(x, y, 1, sizeOfShip);
        } else {
            makeAShot(x, y, 2,sizeOfShip);
        }
        subscriber.notifySubscribers(EventType.UPDATE_TURN,data.turn);
    }

    public void chooseStrartegy(Placement strategy, int playerNum) {
        if (strategy.equals(Placement.RANDOM)) {
            players.get("player"+playerNum).placeShips(Placement.RANDOM, playerNum);
        } else {
            players.get("player"+playerNum).placeShips(Placement.CUSTOM, playerNum);
        }
    }

    public Boolean saveGame() {
        try (FileWriter file = new FileWriter("gameState.json")) {
            file.write(data.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean getSavedGame(String path) {
        try {
            serialization.setFileName(path);
            data = serialization.readFile();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int endGame() {
        if (!players.get("player1").getGameBoard().isGameOver()
                && !players.get("player2").getGameBoard().isGameOver()) {
            return 0;
        }
        if (players.get("player1").getGameBoard().isGameOver()) {
            return 2;
        } else {
            return 1;
        }
    }

    private void makeAShot(int x, int y, int player, int sizeOfShip) {
        // SoundPlayer shotSound = new SoundPlayer(data.volume);
        // shotSound.setSound("src\\main\\java\\com\\example\\seabattlebacklocal\\source\\sounds\\249101-Light_Naval_Cannon_Blast_4.wav");
        // shotSound.playSound();
        if(player == 1){
            players.get("player1").makeMove(new Coordinate(x, y, data.sizeOfBoard),sizeOfShip);
        } else {
            players.get("player2").makeMove(new Coordinate(x, y, data.sizeOfBoard),sizeOfShip);
        }
    }

    @Override
    public int update(EventType event, int turn) {
        if(event == EventType.UPDATE_SHIPS){
            return 1;
        }
        return -1;
    }

}

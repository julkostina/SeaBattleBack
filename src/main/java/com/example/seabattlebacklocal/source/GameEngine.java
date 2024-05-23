package com.example.seabattlebacklocal.source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import com.example.seabattlebacklocal.source.Player.Placement;


public class GameEngine {
    private final Dictionary<String, Player> players= new Hashtable<>();
    private final Serialization serialization = Serialization.getInstance();
    private Game data;
    private int shipSize;
    private final Dictionary<String, GameBoard> gameBoards = new Hashtable<>();

    public GameEngine() {
        data = serialization.readFile();
    }

    public GameEngine(Game data) {
        this.data = data;
    }

    public Game getGame(){
        return data;
    }
    public Dictionary<String, Player> getPlayers(){
        return players;
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
        gameBoards.put("ofPlayer1", new GameBoard(size));
        gameBoards.put("ofPlayer2Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer1Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer2", new GameBoard(size));
        players.put("player1", new PlayerBuilder().setName(name1).setGameBoard(gameBoards.get("ofPlayer1")).build());
        players.put("player2", new PlayerBuilder().setName(name2).setGameBoard(gameBoards.get("ofPlayer2")).build());
        data.volume = volume;
        data.player1=name1;
        data.player2=name2;
        data.sizeOfBoard=size;
        serialization.writeFile(data);
    }

    public void updateGame(int x, int y, int sizeOfShip) {
        if(data.turn==1){
            makeAShot(x, y, 1, sizeOfShip);
        } else {
            makeAShot(x, y, 2,sizeOfShip);
        }
    }

    public void chooseStrategy(Placement strategy, int playerNum) {
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
        if(player == 1){
            players.get("player1").makeMove(new Coordinate(x, y, data.sizeOfBoard),sizeOfShip);
        } else {
            players.get("player2").makeMove(new Coordinate(x, y, data.sizeOfBoard),sizeOfShip);
        }
    }



}

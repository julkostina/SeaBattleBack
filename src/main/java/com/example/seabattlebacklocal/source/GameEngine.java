package com.example.seabattlebacklocal.source;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Dictionary;

import com.example.seabattlebacklocal.source.Player.Placement;
import com.example.seabattlebacklocal.source.ObserverPattern.GameBoard;
import com.example.seabattlebacklocal.source.ObserverPattern.Subscriber;

public class GameEngine {
    private Dictionary<String, Player> players;
    private SoundPlayer backgroundSound;
    private Subscriber subscriber;
    private Serialization serialization;
    private Dictionary<String, String> data;
    private Dictionary<String, GameBoard> gameBoards;

    public GameEngine() {
        backgroundSound = new SoundPlayer("sounds\\249101-Light_Naval_Cannon_Blast_4.wav",
                Integer.parseInt(data.get("volume")));
        backgroundSound.playSoundContinuously();
        data = serialization.readFile();
    }

    public void initGame(String name1, String name2, int size, int volume) {
        data.put("volume", String.valueOf(volume));
        gameBoards.put("ofPlayer1", new GameBoard(size));
        gameBoards.put("ofPlayer2Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer1Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer2", new GameBoard(size));
        players.put("player1", new Player(name1, gameBoards.get("ofPlayer1")));
        players.put("player2", new Player(name2, gameBoards.get("ofPlayer2")));
        subscriber.addSubscriber(gameBoards.get("ofPlayer1"));
        subscriber.addSubscriber(gameBoards.get("ofPlayer2"));
        subscriber.addSubscriber(gameBoards.get("ofPlayer1Opponent"));
        subscriber.addSubscriber(gameBoards.get("ofPlayer2Opponent"));
    }

    public void updateGame(int x, int y) {
        if(data.get("turn").equals("1")){
            makeAShoot(x, y, 1);
        } else {
            makeAShoot(x, y, 2);
        }
        notifyObservers(Integer.parseInt(data.get("turn")));
    }
    public void chooseStrartegy(Placement strategy, int playerNum) {
        if (strategy.equals(Placement.RANDOM.toString())) {
            players.get("player"+playerNum).placeShips(Placement.RANDOM);
        } else {
            players.get("player"+playerNum).placeShips(Placement.CUSTOM);
        }
    }
    public void notifyObservers(int turn) {
        subscriber.notifySubscribers(turn);
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

    public Boolean getGame(String path) {
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

    private void makeAShoot(int x, int y, int player) {
        SoundPlayer shotSound = new SoundPlayer("sounds\\249101-Light_Naval_Cannon_Blast_4.wav",
                Integer.parseInt(data.get("volume")));
                shotSound.playSound();
        if(player == 1){
            players.get("player1").makeMove(new Coordinate(x, y), Integer.parseInt(data.get("miss1")), Integer.parseInt(data.get("hit1")),Integer.parseInt(data.get("turn")));
        } else {
            players.get("player2").makeMove(new Coordinate(x, y), Integer.parseInt(data.get("miss2")), Integer.parseInt(data.get("hit2")),Integer.parseInt(data.get("turn")));
        }
    }
}

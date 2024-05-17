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
    private Serialization serialization = new Serialization();
    private Game data = new Game();
    private Dictionary<String, GameBoard> gameBoards;

    public GameEngine() {
        data = serialization.readFile();
        backgroundSound = new SoundPlayer("src\\main\\java\\com\\example\\seabattlebacklocal\\source\\sounds\\328713-Ambience_Exterior_Wave_Boulders_Pier_Between_Rocks_More_Distant_Waves_Hard_Loop.wav",
                data.volume);
        backgroundSound.playSoundContinuously();
    }

    public void initGame(String name1, String name2, int size, float volume) {
        data.volume=volume;
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
        if(data.turn==1){
            makeAShoot(x, y, 1);
        } else {
            makeAShoot(x, y, 2);
        }
        notifyObservers(data.turn);
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
        SoundPlayer shotSound = new SoundPlayer("src\\main\\java\\com\\example\\seabattlebacklocal\\source\\sounds\\249101-Light_Naval_Cannon_Blast_4.wav",
                data.volume);
                shotSound.playSound();
        if(player == 1){
            players.get("player1").makeMove(new Coordinate(x, y), data.miss1, data.hit1,data.turn);
        } else {
            players.get("player2").makeMove(new Coordinate(x, y), data.miss2, data.hit2,data.turn);
        }
    }

}

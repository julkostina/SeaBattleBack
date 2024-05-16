package com.example.seabattlebacklocal.source;

import java.util.Dictionary;

import com.example.seabattlebacklocal.source.ObserverPattern.Observer;
import com.example.seabattlebacklocal.source.ObserverPattern.Subscriber;

public class GameEngine implements Observer{
    private Dictionary<String,Player> players;
    private int volume;
    private int sizeOfBoard;
    private SoundPlayer backgroundSound;
    private Subscriber subscriber;
    private Serialization serialization;
    private Dictionary<String, String> data;
    private Dictionary<String,GameBoard> gameBoards;
    public GameEngine(){
        data = serialization.readFile();
        backgroundSound = new SoundPlayer("sounds\\249101-Light_Naval_Cannon_Blast_4.wav",volume);
        backgroundSound.playSound();
    }
    // 	startGame(): Initializes and starts the game.
// o	updateGameState(): Updates the game state based on player moves and notifies observers.
// o	notifyObservers(): Notifies observers (e.g., the game board) of state changes.
// o	endGame(): Ends the game and determines the winner.
    public void startGame(String name1, String name2, int size, int volume) {
        this.volume = volume;
        this.sizeOfBoard = size;
        gameBoards.put("ofPlayer1", new GameBoard(size));
        gameBoards.put("ofPlayer2Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer1Opponent", new GameBoard(size));
        gameBoards.put("ofPlayer2", new GameBoard(size));
        players.put("player1", new Player(name1, gameBoards.get("ofPlayer1")));
        players.put("player2", new Player(name2, gameBoards.get("ofPlayer2")));
        subscriber.addSubscriber(this);
    }

    public void updateGameState() {
        // TODO implement here
    }

    public void notifyObservers() {
        // TODO implement here
    }

    public void endGame() {
        // TODO implement here
    }

    @Override
    public int updateTurn() {
        if(data.get(data.get("turn")).equals("1")){
            return 2;
        }
        else{
            return 1;
        }
    }
}

package com.example.seabattlebacklocal.source;

public class GameState {
    private Player currentPlayer;
    private GameBoard gameBoard;
    private boolean isGameOver;

    public GameState(Player currentPlayer, GameBoard gameBoard) {
        this.currentPlayer = currentPlayer;
        this.gameBoard = gameBoard;
        this.isGameOver = false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
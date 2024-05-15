package com.example.seabattlebacklocal.source;

public class ObserverImpl implements Observer{
    private GameState gameState;


    // However, you might want to consider adding some functionality to the ObserverImpl 
    // class to do something with the new GameState after it's updated. Right now, it just 
    // stores the new state and does nothing else with it. For example, you could add a method 
    // to print out the new state, or update a user interface, or whatever else makes sense in 
    // your application.
    @Override
    public void update(GameState newState) {
        gameState=newState;
        // Display the new state in some way
        System.out.println("Current player: " + gameState.getCurrentPlayer());
        System.out.println("Game over: " + gameState.isGameOver());
        // etc.
    }
}

package com.example.seabattlebacklocal.source;

import java.util.List;


public class Game {
    String player1;
    String player2;
    int turn;
    float volume;
    int sizeOfBoard;
    ShipTypes ships1;
    String placementStrategy1;
    String placementStrategy2;
    PlacedShips placedShips1;
    PlacedShips placedShips2;
    boolean placed1;
    boolean placed2;
    ShipTypes ships2;
    int hit1;
    int hit2;
    int miss1;
    int miss2;
}

class ShipTypes {
    int oneDeck;
    int twoDeck;
    int threeDeck;
    int fourDeck;
}


class PlacedShips {
    List<String> oneDeck;
    List<String> twoDeck;
    List<String> threeDeck;
    List<String> fourDeck;
}
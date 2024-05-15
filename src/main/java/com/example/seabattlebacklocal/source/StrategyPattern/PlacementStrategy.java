package com.example.seabattlebacklocal.source.StrategyPattern;

import com.example.seabattlebacklocal.source.GameBoard;
import com.example.seabattlebacklocal.source.Player;

public interface PlacementStrategy {
    void placeShips(GameBoard gameBoard, Player player);
}

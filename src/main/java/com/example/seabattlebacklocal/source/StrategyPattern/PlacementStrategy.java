package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.List;

import com.example.seabattlebacklocal.source.Player;
import com.example.seabattlebacklocal.source.Ships.Ship;

public interface PlacementStrategy {
    List<Ship> placeShips(Player player);
}

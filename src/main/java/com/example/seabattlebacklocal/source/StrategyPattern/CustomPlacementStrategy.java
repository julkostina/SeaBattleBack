package com.example.seabattlebacklocal.source.StrategyPattern;

import java.util.Dictionary;

import com.example.seabattlebacklocal.source.Player;
import com.example.seabattlebacklocal.source.Serialization;


public class CustomPlacementStrategy implements PlacementStrategy {

    @Override
    public void placeShips( Player player) {
        Serialization serialization = new Serialization();
        Dictionary<String, String> data = serialization.readFile();
        data.put(data.get("placementStrategy"+data.get("turn")), "CUSTOM");
        serialization.writeFile(data);
    }
}

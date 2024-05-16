package com.example.seabattlebacklocal.source.ObserverPattern;

import java.util.Dictionary;

import com.example.seabattlebacklocal.source.Serialization;

public class PlayerTurn implements Observer{
    private Serialization serialization = new Serialization();
    private Dictionary<String, String> data = serialization.readFile();

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

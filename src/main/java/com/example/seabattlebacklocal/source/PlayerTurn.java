package com.example.seabattlebacklocal.source;

import java.util.Dictionary;

public class PlayerTurn implements Observer{
    private Serialization serialization = new Serialization();
    private Dictionary<String, String> data = serialization.readFile();

    @Override
    public void update() {
        if(data.get(data.get("turn")).equals("1")){
            data.put("turn", "2");
        }
        else{
            data.put("turn", "1");
        }
        serialization.writeFile(data);
    }
}

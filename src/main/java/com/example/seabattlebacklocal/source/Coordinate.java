package com.example.seabattlebacklocal.source;

import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class Coordinate {

    private int row;
    private int column;
    private Dictionary<Character, Character> data;
    public Coordinate(int row, int column){
        this.column=column;
        this.row=row;
        Serialization serialization=new Serialization();
        Dictionary<Character, Character> data = new Hashtable<>();
        try{
            this.data  = serialization.readFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public Boolean isValid(){
        return row <= data.get("sizeOfBoard") && column <= data.get("sizeOfBoard");
    }
}

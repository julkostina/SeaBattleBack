package com.example.seabattlebacklocal.source;
import java.util.Dictionary;

public class Coordinate {

    private int row;
    private int column;
    private Dictionary<String, String> data;

    public Coordinate(int row, int column) {
        if (isValid()) {
            this.column = column;
            this.row = row;
        }
        else {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Boolean isValid() {
        return row <= Integer.parseInt(data.get("sizeOfBoard")) && column <= Integer.parseInt(data.get("sizeOfBoard"));
    }
}

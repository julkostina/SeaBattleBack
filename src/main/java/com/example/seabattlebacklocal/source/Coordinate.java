package com.example.seabattlebacklocal.source;

public class Coordinate {

    private int row;
    private int column;

    public Coordinate(int row, int column, int size) {
        if (isValid(size)) {
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

    public Boolean isValid(int size) {
        return row <= size && column <= size;
    }
}

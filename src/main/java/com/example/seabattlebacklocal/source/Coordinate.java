package com.example.seabattlebacklocal.source;

public class Coordinate {

    private  int row;
    private  int column;
     public Coordinate(){

     }
    public Coordinate(int row, int column, int size) {
        if (isValid(row,column, size)) {
            this.column = column;
            this.row = row;
        }
        else {
            throw new IllegalArgumentException("Invalid coordinate");
        }
    }
    public void setRow(int row){
        this.row=row;
    }
    public void setColumn(int col) {
        this.column=col;
    }
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Boolean isValid(int row, int col, int size) {
        return (row<size && column<size);
    }
}

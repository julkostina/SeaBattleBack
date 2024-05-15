package com.example.seabattlebacklocal.source;

import com.example.seabattlebacklocal.source.Ships.Ship;


public class GameBoard {
    private static final int EMPTY = 0;
    private static final int SHIP = 1;
    private static final int HIT = 2;
    private static final int MISS = 3;
    private int size;
    private final int[][] board;
    enum Placement {
        VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_RIGHT, HORIZONTAL_LEFT
    }

    public GameBoard(int size) {
        this.size=size;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                board[i][j] = EMPTY;
        }
    }

    public int[][] getBoard() {
        return board;
    }

    public void setShip(int x, int y) {
        board[x][y] = SHIP;
    }

    public void setHit(int x, int y) {
        board[x][y] = HIT;
    }

    public void setMiss(int x, int y) {
        board[x][y] = MISS;
    }

    public void setEmpty(int x, int y) {
        board[x][y] = EMPTY;
    }

    public int getSize() {
        return size;
    }

    public boolean isShip(int x, int y) {
        return board[x][y] == SHIP;
    }

    public boolean isHit(int x, int y) {
        return board[x][y] == HIT;
    }

    public boolean isMiss(int x, int y) {
        return board[x][y] == MISS;
    }

    public boolean isEmpty(int x, int y) {
        return board[x][y] == EMPTY;
    }

    public boolean isShipNearby(int x, int y) {
        if (x > 0 && board[x - 1][y] == SHIP)
            return true;
        if (x < size - 1 && board[x + 1][y] == SHIP)
            return true;
        if (y > 0 && board[x][y - 1] == 1)
            return true;
        if (y < size - 1 && board[x][y + 1] == SHIP)
            return true;
        return false;
    }

    public boolean placeShip(Ship ship, Coordinate start, Coordinate end){
        boolean result=false;
        if(isShip(start.getRow(),start.getColumn())||isShip(end.getRow(),end.getColumn())){
            return result;
        }
        Placement placement=null;
        if(ship.getSize()==1){
            for(int i=start.getColumn(); i<=end.getColumn(); i++){
                board[start.getRow()][i]=SHIP;
                board[i][start.getColumn()]=SHIP;
            }
        }
        else{
            result=true;
            if((start.getRow()-end.getRow())>0){
                placement=Placement.VERTICAL_DOWN;
            }
            if((start.getRow()-end.getRow())<0){
                placement=Placement.VERTICAL_UP;
            }
            if((start.getColumn()-end.getColumn())>0){
                placement=Placement.HORIZONTAL_LEFT;
            }
            if((start.getColumn()-end.getColumn())<0){
                placement=Placement.HORIZONTAL_RIGHT;
            }
            switch (placement){
                case VERTICAL_UP:
                    for(int i=start.getRow(); i<=end.getRow(); i++){
                        board[i][start.getColumn()]=SHIP;
                    }
                    break;
                case VERTICAL_DOWN:
                    for(int i=start.getRow(); i>=end.getRow(); i--){
                        board[i][start.getColumn()]=SHIP;
                    }
                    break;
                case HORIZONTAL_RIGHT:
                    for(int i=start.getColumn(); i<=end.getColumn(); i++){
                        board[start.getRow()][i]=SHIP;
                    }
                    break;
                default:
                    for(int i=start.getColumn(); i>=end.getColumn(); i--){
                        board[start.getRow()][i]=SHIP;
                    }
                    break;
            }
        }
        return result;
    }
   
    public void makeMove(Coordinate target) {
        if (isShip(target.getRow(), target.getColumn())) {
            setHit(target.getRow(), target.getColumn());
        } else {
            setMiss(target.getRow(), target.getColumn());
        }
    }

    public boolean checkHit(Coordinate target) {
        return isHit(target.getRow(), target.getColumn());
    }

    public boolean isGameOver() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (isShip(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
} 


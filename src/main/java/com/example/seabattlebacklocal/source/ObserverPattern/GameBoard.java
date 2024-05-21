package com.example.seabattlebacklocal.source.ObserverPattern;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class GameBoard implements Observer {
    private static final int EMPTY = 0;
    private static final int SHIP = 1;
    private static final int HIT = 2;
    private static final int MISS = 3;
    private static final int DISTANCE = 4;

    private final int size;
    private final int[][] board;
    private final Dictionary<String, Integer> numberOfShips =new Hashtable<>();
    private final Dictionary<String, List<Coordinate>> coordinatesOfShips=new Hashtable<>();
    private final List<Ship> ships= new ArrayList<>();

    enum Placement {
        VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_RIGHT, HORIZONTAL_LEFT
    }

    public GameBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                board[i][j] = EMPTY;
        }
        numberOfShips.put("oneDeck", 4);
        numberOfShips.put("twoDeck", 3);
        numberOfShips.put("threeDeck", 2);
        numberOfShips.put("fourDeck", 1);
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
    public void setDistance(int x, int y) {
        board[x][y] = DISTANCE;
    }
    public int getSize() {
        return size;
    }

    public List<Ship> getShips() {
        return ships;
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
        return board[x][y] == DISTANCE;
    }

//        if ((placement=="start")&&((row > 0 && board[row - 1][col] == SHIP)||(row < size - 1 && board[row + 1][col] == SHIP)||(col > 0 && board[row][col - 1] == 1)||(col < size - 1 && board[row][col + 1] == SHIP)
//        ||(row>0&&col>0&& board[row-1][col-1]==SHIP)||(row>0&&col<size&& board[row-1][col+1]==SHIP)||(row<size-1&&col>0&& board[row+1][col-1]==SHIP)||(row<size-1&&col>0&& board[row+1][col+1]==SHIP))){
//            return true;
//        }
//        if ((placement!="end")&&((row > 0 && board[row - 1][col] == SHIP)||(row < size - 1 && board[row + 1][col] == SHIP)||(col > 0 && board[row][col - 1] == 1)||(col < size - 1 && board[row][col + 1] == SHIP)
//                ||(row>0&&col>0&& board[row-1][col-1]==SHIP)||(row>0&&col<size&& board[row-1][col+1]==SHIP)||(row<size-1&&col>0&& board[row+1][col-1]==SHIP)||(row<size-1&&col>0&& board[row+1][col+1]==SHIP))){
//            return true;
//        }
//         if ((placement=="middle")&&((row > 0 && board[row - 1][col] == SHIP)||(row < size - 1 && board[row + 1][col] == SHIP))){
//            return true;
//        }
//        else{
//            return false;
//        }


    public boolean placeShip(Ship ship, Coordinate start, Coordinate end) {
        boolean result = false;
        if (isShip(start.getRow(), start.getColumn()) || isShip(end.getRow(), end.getColumn())||isShipNearby(start.getRow(), start.getColumn()) || isShipNearby(end.getRow(), end.getColumn())) {
            return result;
        }
        Placement placement = Placement.HORIZONTAL_LEFT;
        ships.add(ship);
        if (ship.getSize() == 1) {
            result = true;
            updateNumberOfShips("oneDeck", '-');
            updateCoordinatesOfShips("oneDeck", ship);
            for (int i = start.getColumn(); i <= end.getColumn(); i++) {
                board[start.getRow()][i] = SHIP;
                board[i][start.getColumn()] = SHIP;
            }
        } else {
            result = true;
            if ((start.getRow() - end.getRow()) > 0) {
                placement = Placement.VERTICAL_DOWN;
            }
            if ((start.getRow() - end.getRow()) < 0) {
                placement = Placement.VERTICAL_UP;
            }
            if ((start.getColumn() - end.getColumn()) > 0) {
                placement = Placement.HORIZONTAL_LEFT;
            }
            if ((start.getColumn() - end.getColumn()) < 0) {
                placement = Placement.HORIZONTAL_RIGHT;
            }
            switch (placement) {
                case VERTICAL_UP:
                    for (int i = start.getRow(); i <= end.getRow(); i++) {
                        board[i][start.getColumn()] = SHIP;
                    }
                    break;
                case VERTICAL_DOWN:
                    for (int i = start.getRow(); i >= end.getRow(); i--) {
                        board[i][start.getColumn()] = SHIP;
                    }
                    break;
                case HORIZONTAL_RIGHT:
                    for (int i = start.getColumn(); i <= end.getColumn(); i++) {
                        board[start.getRow()][i] = SHIP;
                    }
                    break;
                default:
                    for (int i = start.getColumn(); i >= end.getColumn(); i--) {
                        board[start.getRow()][i] = SHIP;
                    }
                    break;

            }
            switch (ship.getSize()) {
                case 2:
                    updateNumberOfShips("twoDeck", '-');
                    updateCoordinatesOfShips("twoDeck", ship);
                    break;
                case 3:
                    updateNumberOfShips("threeDeck", '-');
                    updateCoordinatesOfShips("threeDeck", ship);
                    break;
                case 4:
                    updateNumberOfShips("fourDeck", '-');
                    updateCoordinatesOfShips("fourDeck", ship);
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

    public void setDistanceForShip(int row, int col ){
        if(row==0){
            if(col==0){
                setDistance(row+1, col);
                setDistance(row+1, col+1);
                setDistance(row, col+1);
            }
            if(col==size-1){
                setDistance(row+1, col);
                setDistance(row+1, col-1);
                setDistance(row, col-1);
            }
            if(col!=size-1&&col!=0){
                setDistance(row+1, col);
                setDistance(row+1, col-1);
                setDistance(row, col-1);
                setDistance(row+1, col+1);
                setDistance(row, col+1);
            }
        }
        if(row==size-1){
            if(col==0){
                setDistance(row-1, col);
                setDistance(row-1, col+1);
                setDistance(row, col-1);
            }
            if(col==size-1){
                setDistance(row-1, col);
                setDistance(row-1, col-1);
                setDistance(row, col+1);
            }
            if(col!=size-1&&col!=0){
                setDistance(row-1, col);
                setDistance(row-1, col-1);
                setDistance(row, col-1);
                setDistance(row-1, col+1);
                setDistance(row, col+1);
            }
        }
        if(row!=0&&row==size-1){
            if(col==0){
                setDistance(row-1, col);
                setDistance(row-1, col+1);
                setDistance(row+1, col);
                setDistance(row+1, col+1);
                setDistance(row, col+1);
            }
            if(col==size-1){
                setDistance(row-1, col);
                setDistance(row-1, col-1);
                setDistance(row+1, col);
                setDistance(row+1, col-1);
                setDistance(row, col-1);
            }
            if(col!=size-1&&col!=0){
                setDistance(row-1,col);
                setDistance(row+1, col);
                setDistance(row-1, col-1);
                setDistance(row-1, col+1);
                setDistance(row+1, col+1);
                setDistance(row+1, col-1);
                setDistance(row, col-1);
                setDistance(row, col+1);
            }

        }

    }
//    public boolean checkHit(Coordinate target) {
//        return isHit(target.getRow(), target.getColumn());
//    }

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

    private Dictionary<String,Integer> updateNumberOfShips(String shipType, char typeOfOperation) {
        Dictionary<String,Integer> listOfShips = this.numberOfShips;
        if (typeOfOperation == '+') {
                switch(shipType){
                    case "oneDeck":
                    listOfShips.put("oneDeck", numberOfShips.get("oneDeck") + 1);
                        break;
                    case "twoDeck":
                    listOfShips.put("twoDeck", numberOfShips.get("twoDeck") + 1);
                        break;
                    case "threeDeck":
                    listOfShips.put("threeDeck", numberOfShips.get("threeDeck") + 1);
                        break;
                    case "fourDeck":
                    listOfShips.put("fourDeck", numberOfShips.get("fourDeck") + 1);
                        break;
                }
            } else {
                switch(shipType){
                    case "oneDeck":
                    listOfShips.put("oneDeck", numberOfShips.get("oneDeck") - 1);
                        break;
                    case "twoDeck":
                    listOfShips.put("twoDeck", numberOfShips.get("twoDeck") - 1);
                        break;
                    case "threeDeck":
                    listOfShips.put("threeDeck", numberOfShips.get("threeDeck") - 1);
                        break;
                    case "fourDeck":
                    listOfShips.put("fourDeck", numberOfShips.get("fourDeck") - 1);
                        break;
                }
            }
    return listOfShips;
    }

    private Dictionary<String, List<Coordinate>> updateCoordinatesOfShips(String shipType, Ship ship) {
        Dictionary<String, List<Coordinate>> coordinatesOfShips = this.coordinatesOfShips;
            // Get the coordinates of the ship
            List<Coordinate> shipCoordinates = ship.getCoordinates();

            // Convert the coordinates to a list of arrays of integers
            List<Coordinate> coordinates = new ArrayList<>();
            for (Coordinate coordinate : shipCoordinates) {
                coordinates.add(new Coordinate( coordinate.getRow(), coordinate.getColumn(),size));
            }

            // Update the dictionary with the new coordinates
            coordinatesOfShips.put(shipType, coordinates);
        return coordinatesOfShips;
    }

    @Override
    public int update(EventType event,int turn) {
        if (turn==1) {
            return 2;
        } else {
            return 1;
        }
    }
}

package com.example.seabattlebacklocal.source;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

import com.example.seabattlebacklocal.source.Ships.Ship;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GameBoard {
    private static final int EMPTY = 0;
    private static final int SHIP = 1;
    private static final int HIT = 2;
    private static final int MISS = 3;

    private int size;
    private final int[][] board;

    private List<Ship> ships;
    private Serialization serialization = new Serialization();
    private Dictionary<String, String> data;

    enum Placement {
        VERTICAL_UP, VERTICAL_DOWN, HORIZONTAL_RIGHT, HORIZONTAL_LEFT
    }

    public GameBoard(int size) {
        this.size = size;
        data = serialization.readFile();
        this.board = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                board[i][j] = EMPTY;
        }
        data.put("size", String.valueOf(size));
        serialization.writeFile(data);
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

    public boolean placeShip(Ship ship, Coordinate start, Coordinate end) {
        boolean result = false;
        if (isShip(start.getRow(), start.getColumn()) || isShip(end.getRow(), end.getColumn())) {
            return result;
        }
        Placement placement = null;
        ships.add(ship);
        if (ship.getSize() == 1) {
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
                    updateNumberOfShips("twoDeck",'-');
                    updateCoordinatesOfShips("twoDeck",ship);
                    break;
                case 3:
                    updateNumberOfShips("threeDeck",'-');
                    updateCoordinatesOfShips("threeDeck",ship);
                    break;
                case 4:
                    updateNumberOfShips("fourDeck",'-');
                    updateCoordinatesOfShips("fourDeck",ship);
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

    private void updateNumberOfShips(String shipType, char typeOfOperation) {
        try {
            String stringOfShips = data.get("ships" + data.get("turn"));
            Gson gson = new Gson();
            java.lang.reflect.Type type = new TypeToken<Dictionary<String, Integer>>() {
            }.getType();
            Dictionary<String, Integer> dictionary = gson.fromJson(stringOfShips, type);
            if(typeOfOperation == '+'){
                dictionary.put(shipType, dictionary.get(shipType) + 1);
            } else {
                dictionary.put(shipType, dictionary.get(shipType) - 1);
            }
            stringOfShips = gson.toJson(dictionary);
            data.put("ships" + data.get("turn"), stringOfShips);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateCoordinatesOfShips(String shipType, Ship ship) {
    try {
        String stringOfShips = data.get("ships" + data.get("turn"));
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<Dictionary<String, List<Integer[]>>>(){}.getType();
        Dictionary<String, List<Integer[]>> dictionary = gson.fromJson(stringOfShips, type);

        // Get the coordinates of the ship
        List<Coordinate> shipCoordinates = ship.getCoordinates();

        // Convert the coordinates to a list of arrays of integers
        List<Integer[]> coordinates = new ArrayList<>();
        for (Coordinate coordinate : shipCoordinates) {
            coordinates.add(new Integer[] {coordinate.getRow(), coordinate.getColumn()});
        }

        // Update the dictionary with the new coordinates
        dictionary.put(shipType, coordinates);

        stringOfShips = gson.toJson(dictionary);
        data.put("ships" + data.get("turn"), stringOfShips);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}
}

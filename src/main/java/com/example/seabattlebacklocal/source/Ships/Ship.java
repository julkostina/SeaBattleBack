package com.example.seabattlebacklocal.source.Ships;
import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;

public abstract class Ship {
    protected Boolean isSunk = false;
    int size = 0;
    int hitPoints = 0;
    List<Coordinate> coordinates;

    public Ship(){

    }
    public Ship(List<Coordinate> coordinates){
        this.coordinates = coordinates;
    }
    public int getSize() {
        return size;
    };
    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
    abstract public void setSize();

    public int getHitPoints() {
        return hitPoints;
    }

    public Boolean getIsSunk() {
        return isSunk;
    }

    public void hit() {
        if (size == hitPoints && hitPoints != 0) {
            isSunk = true;
        } else {
            hitPoints++;
            size--;
        }
    }
}

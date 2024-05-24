package com.example.seabattlebacklocal.source.Ships;
import java.util.ArrayList;
import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;

public abstract class Ship {
    protected Boolean isSunk = false;
    int size = 0;
    int hitPoints = 0;
    List<Coordinate> coordinates =  new ArrayList();

    public Ship(){
    }
    public Ship(Integer size, Integer hitPoints, Boolean isSunk, List<Coordinate> coordinates){
        this.size = size;
        this.hitPoints = hitPoints;
        this.isSunk = isSunk;
        this.coordinates = coordinates;
    }
    public Ship(List<Coordinate> coordinates){
        this.coordinates = coordinates;
    }
    public int getSize() {
        return size;
    };
    protected void setSize(int size){
        this.size=size;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public Boolean getIsSunk() {
        return isSunk;
    }

    public void hit() {
        hitPoints++;
        if (size == hitPoints) {
            isSunk = true;
        }
        size--;
    }
}

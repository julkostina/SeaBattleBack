package com.example.seabattlebacklocal.source.Ships;

public abstract class Ship {
    protected Boolean isSunk=false;
    int size=0;
    int hitPoints=0;
    public int  getSize(){
        return size;
    };
    abstract public void setSize();
    public int getHitPoints(){
        return hitPoints;
    }
    public Boolean getIsSunk(){
        return isSunk;
    }
    public void hit(){
        if(size==hitPoints){
            isSunk=true;

        }
        else{
            hitPoints++;
        }
    }
}

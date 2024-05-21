package com.example.seabattlebacklocal.source.Ships;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;

public class FourDeckShip  extends Ship{
    public FourDeckShip(List<Coordinate> coordinates){
        super(coordinates);
        size=4;
    }

}


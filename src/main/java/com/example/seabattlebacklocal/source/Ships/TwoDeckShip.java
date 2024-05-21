package com.example.seabattlebacklocal.source.Ships;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;

public class TwoDeckShip extends Ship{
    public TwoDeckShip(List<Coordinate> coordinates){
        super(coordinates);
        size=2;
    }

}

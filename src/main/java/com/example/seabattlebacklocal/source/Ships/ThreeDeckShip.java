package com.example.seabattlebacklocal.source.Ships;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;

public class ThreeDeckShip extends Ship{
    public ThreeDeckShip(List<Coordinate> coordinates){
        super(coordinates);
        size=3;
    }

}

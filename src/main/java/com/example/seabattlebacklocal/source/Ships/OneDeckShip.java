package com.example.seabattlebacklocal.source.Ships;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;

public class OneDeckShip extends Ship{
 public OneDeckShip(List<Coordinate> coordinates){
        super(coordinates);
        size = 1;
    }

}

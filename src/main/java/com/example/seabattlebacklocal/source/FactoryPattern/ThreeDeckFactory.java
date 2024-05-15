package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.Ship;
import com.example.seabattlebacklocal.source.Ships.ThreeDeckShip;
import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;

public class ThreeDeckFactory implements ShipFactory{
    @Override
    public Ship createShip(List<Coordinate> coordinates) {
        return new ThreeDeckShip(coordinates);
    }
}
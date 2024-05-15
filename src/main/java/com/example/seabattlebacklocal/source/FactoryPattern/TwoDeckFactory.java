package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.Ship;
import com.example.seabattlebacklocal.source.Ships.TwoDeckShip;
import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;

public class TwoDeckFactory implements ShipFactory{
    @Override
    public Ship createShip(List<Coordinate> coordinates) {
        return new TwoDeckShip(coordinates);
    }
}
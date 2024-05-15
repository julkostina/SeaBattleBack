package com.example.seabattlebacklocal.source.FactoryPattern;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.Ships.FourDeckShip;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class FourDeckFactory implements ShipFactory{
    @Override
    public Ship createShip(List<Coordinate> coordinates) {
        return new FourDeckShip(coordinates);
    }
}
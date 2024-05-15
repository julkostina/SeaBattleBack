package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.OneDeckShip;
import com.example.seabattlebacklocal.source.Ships.Ship;

import java.util.List;
import com.example.seabattlebacklocal.source.Coordinate;

public class OneDeckFactory implements ShipFactory{
    @Override
    public  Ship createShip(List<Coordinate> coordinates) {
        return new OneDeckShip(coordinates);
    }
}
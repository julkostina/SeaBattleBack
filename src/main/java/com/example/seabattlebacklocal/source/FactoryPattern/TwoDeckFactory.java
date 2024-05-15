package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.Ship;
import com.example.seabattlebacklocal.source.Ships.TwoDeckShip;

public class TwoDeckFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new TwoDeckShip();
    }
}
package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.FourDeckShip;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class FourDeckFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new FourDeckShip();
    }
}
package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.Ship;
import com.example.seabattlebacklocal.source.Ships.ThreeDeckShip;

public class ThreeDeckFactory implements ShipFactory{
    @Override
    public Ship createShip() {
        return new ThreeDeckShip();
    }
}
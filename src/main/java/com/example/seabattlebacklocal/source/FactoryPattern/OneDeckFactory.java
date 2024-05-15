package com.example.seabattlebacklocal.source.FactoryPattern;

import com.example.seabattlebacklocal.source.Ships.OneDeckShip;
import com.example.seabattlebacklocal.source.Ships.Ship;

public class OneDeckFactory implements ShipFactory{
    @Override
    public  Ship createShip() {
        return new OneDeckShip();
    }
}
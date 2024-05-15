package com.example.seabattlebacklocal.source.FactoryPattern;

import java.util.List;

import com.example.seabattlebacklocal.source.Coordinate;
import com.example.seabattlebacklocal.source.Ships.*;

    public interface ShipFactory{
         Ship createShip(List<Coordinate> coordinates);
    }




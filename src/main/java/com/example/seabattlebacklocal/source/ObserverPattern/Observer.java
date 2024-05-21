package com.example.seabattlebacklocal.source.ObserverPattern;

public interface Observer {
    int update(EventType event, int turn);
}
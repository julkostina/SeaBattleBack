package com.example.seabattlebacklocal.source.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private List<Observer> subscribers = new ArrayList<Observer>();
    
    public void addSubscriber(Observer observer){
        subscribers.add(observer);
    }

    public void removeSubscriber(Observer observer){
        subscribers.remove(observer);
    }

    public void notifySubscribers(){
        for(Observer observer : subscribers){
            observer.updateTurn();
        }
    }
}

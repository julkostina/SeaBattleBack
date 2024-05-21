package com.example.seabattlebacklocal.source.ObserverPattern;

import java.util.ArrayList;
import java.util.List;

public class Updater {
    private List<Observer> subscribersForTurn = new ArrayList<Observer>();
    private List<Observer> subscribersForShips = new ArrayList<Observer>();
    public void addSubscriber(EventType event, Observer observer){
        if(event == EventType.UPDATE_TURN){
            subscribersForTurn.add(observer);
        }
        else{
            subscribersForShips.add(observer);
        }
    }

    public void removeSubscriber(EventType event, Observer observer){
        if(event == EventType.UPDATE_TURN){
            subscribersForTurn.remove(observer);
        }
        else{
            subscribersForShips.remove(observer);
        }
    }

    public void notifySubscribers(EventType event, int turn){
        if(event == EventType.UPDATE_TURN){
            for(Observer observer : subscribersForTurn){
                observer.update(EventType.UPDATE_TURN, turn);
            }
        }
        else{
            for(Observer observer : subscribersForShips){
                observer.update(EventType.UPDATE_SHIPS, turn);
            }
        }
    }


}

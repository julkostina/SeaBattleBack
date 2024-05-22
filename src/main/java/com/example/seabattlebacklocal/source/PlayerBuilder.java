package com.example.seabattlebacklocal.source;

public class PlayerBuilder {
    private String name;
    private GameBoard gameBoard;
    private int miss ;
    private int hit;
    public PlayerBuilder(Player player){
        this.name = player.getName();
        this.gameBoard=player.getGameBoard();
        this.miss=player.getMiss();
        this.hit = player.getHit();
    }
    public PlayerBuilder setName(String name){
        this.name=name;
        return this;
    }
    public PlayerBuilder setGameBoard(GameBoard gameBoard){
        this.gameBoard=gameBoard;
        return this;

    }
    public PlayerBuilder setHit(int hit){
        this.hit=hit;
        return this;

    }
    public PlayerBuilder setMiss(int miss){
        this.miss=miss;
        return this;
    }
    public Player build(){
        return new Player(name, gameBoard);
    }

}

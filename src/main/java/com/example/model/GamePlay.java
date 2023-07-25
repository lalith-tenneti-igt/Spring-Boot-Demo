package com.example.model;

import org.kie.api.definition.type.PropertyReactive;

import java.io.Serializable;

@PropertyReactive
public class GamePlay implements Serializable {
    private int userId;
    private long wager;
    private static int gamePlays;
    private int gamePlayNumber;
    private GameStarted gameStarted;
    private GameEnded gameEnded;

    public GamePlay(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return userId;
    }

    public int getGamePlays(){
        return gamePlays;
    }
    public int getGamePlayNumber(){
        return gamePlayNumber;
    }
    public void startGame(){
        gameStarted = new GameStarted(userId);
        this.wager = gameStarted.wagerAmount;
        this.gamePlays += 1;
        this.gamePlayNumber = gamePlays;
        //System.out.println("gamePlayNumber is " + gamePlayNumber);
    }
    public long playGame(){
        long winAmount = gameStarted.playGame();
        return winAmount;
    }

    public void endGame(long winAmount, int gamePlayNumber ){
        gameEnded = new GameEnded(userId, winAmount, gamePlayNumber);
    }
}


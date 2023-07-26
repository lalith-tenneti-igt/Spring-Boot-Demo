package com.example.model;

import org.kie.api.definition.type.PropertyReactive;

import java.io.Serializable;

@PropertyReactive
public class GamePlay implements Serializable {
    private int userId;
    private long wager;
    private int gamePlayId;
    private GameStarted gameStarted;
    private GameEnded gameEnded;

    private int sessionId;

    public GamePlay(int userId, int gamePlayId, int sessionId){
        this.userId = userId;
        this.gamePlayId = gamePlayId;
        this.sessionId = sessionId;
    }
    public int getUserId(){
        return userId;
    }

    public void startGame(){
        gameStarted = new GameStarted(userId);
        this.wager = gameStarted.wagerAmount;
        //System.out.println("gamePlayNumber is " + gamePlayNumber);
    }
    public long playGame(){
        long winAmount = gameStarted.playGame();
        return winAmount;
    }

    public int getGamePlayId() {
        return gamePlayId;
    }

    public void endGame(long winAmount, int gamePlayNumber ){
        gameEnded = new GameEnded(userId, winAmount, gamePlayNumber);
    }
}


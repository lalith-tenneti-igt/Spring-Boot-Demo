package com.example.model;

import java.io.Serializable;

public class GameStarted implements Serializable {
    //sessionID
    public long gamePlayNumber;
    public long wagerCredits;
    public boolean isMaxBet;
    public long wagerDenom;
    public long wagerAmount;
    public String cardId;
    public int playerId;
    public String gameId;
    public String gameOtherId;
    public String paytableId;
    public long basePercentage;
    public long[][] meters;
    public long[][] bbpgMeters;

    public GameStarted(int userId){
        this.playerId = userId;
        this.gamePlayNumber += 1;
        this.wagerAmount = 10;
    }

    public GameStarted(long gamePlayNumber, long wagerCredits, boolean isMaxBet, long wagerDenom, long wagerAmount, String cardId,
                       int playerId, String gameId, String gameOtherId, String paytableId, long basePercentage,
                       long[][] meters, long[][] bbpgMeters) {
        this.gamePlayNumber = gamePlayNumber;
        this.wagerCredits = wagerCredits;
        this.isMaxBet = isMaxBet;
        this.wagerDenom = wagerDenom;
        this.wagerAmount = wagerAmount;
        this.cardId = cardId;
        this.playerId = playerId;
        this.gameId = gameId;
        this.gameOtherId = gameOtherId;
        this.paytableId = paytableId;
        this.basePercentage = basePercentage;
        this.meters = meters;
        this.bbpgMeters = bbpgMeters;
    }

    public long playGame(){
        int winningRate = -30 + (int) (Math.random() * ((3 - (-30)) + 1));
        if(winningRate < 0){
            return -wagerAmount;
        } else{
            int randomNum = 20+ (int) (Math.random() * ((30 - 20) + 1));
            long winAmount = randomNum;
            return winAmount;
        }
    }
}


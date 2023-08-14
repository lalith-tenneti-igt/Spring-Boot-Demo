package com.example.model;

import java.io.Serializable;

public class GameEnded implements Serializable {
    public long gamePlayNumber;
    public long winAmount;
    public String cardId;
    public long playerId;
    public String gameId;
    public String gameOtherId;
    public String paytableId;
    public long basePercentage;
    public long[][] meters;

    public GameEnded(int userId, long winAmount, int gamePlayNumber){
        this.playerId = userId;
        this.winAmount = winAmount;
        this.gamePlayNumber = gamePlayNumber;
    }

    public GameEnded(long gamePlayNumber, long winAmount, String cardId, long playerId, String gameId, String gameOtherId,
                     String paytableId, long basePercentage, long[][] meters) {
        this.gamePlayNumber = gamePlayNumber;
        this.winAmount = winAmount;
        this.cardId = cardId;
        this.playerId = playerId;
        this.gameId = gameId;
        this.gameOtherId = gameOtherId;
        this.paytableId = paytableId;
        this.basePercentage = basePercentage;
        this.meters = meters;
    }
}


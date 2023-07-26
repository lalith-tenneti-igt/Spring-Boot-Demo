package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private int userId;
    private int sessionBalance;
    private List<GamePlay> gamePlays = new ArrayList<GamePlay>();
    private static int gamePlayId;
    private int sessionId;

    public GameSession(int userId) {
        this.userId = userId;
        sessionBalance = 100;
    }


    public GamePlay playGame() {

        System.out.println("sessionBalance is " + sessionBalance);
        GamePlay gamePlay = new GamePlay(userId, gamePlayId, sessionId);
        gamePlays.add(gamePlay);
        gamePlay.startGame();
        long winAmount = gamePlay.playGame();
        sessionBalance += winAmount;

//        TODO: fix this
        gamePlay.endGame(winAmount, gamePlayId);

        this.gamePlayId += 1;

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping between sends");
        }
        return gamePlay;
    }

    public boolean isDone() {
        return sessionBalance <= 0;
    }

    public int getSessionBalance() {
        return sessionBalance;
    }

    public int getSessionId() {
        return sessionId;
    }
}

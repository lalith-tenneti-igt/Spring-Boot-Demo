package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private static int sessionNumber;
    private long userBalance;
    private List<GameSession> sessions = new ArrayList<GameSession>();
    private GameSession gameSession;

    public User() {
        this.userBalance = 400;
        this.sessionNumber = 0;
        this.userId = (int) (Math.random() * 100) + 1;
        gameSession = new GameSession(userId);
    }

    public GamePlay playSession() {
        if (gameSession.isDone()) {
            gameSession = new GameSession(userId);
            sessionNumber += 1;
            userBalance -= 100;
            System.out.println("userBalance is " + userBalance);
        }
//            userBalance -= gameSession.getSessionBalance();
        return gameSession.playGame();
    }

    public boolean isDone() {
        return userBalance < 100;
    }

    public void setUserId() {
        userId = (int) (Math.random() * 100) + 1;
    }

    public int getUserId() {
        return userId;
    }

    public long getBalance() {
        return userBalance;
    }

    public void addBalance(long amount) {
        this.userBalance += amount;
    }
}

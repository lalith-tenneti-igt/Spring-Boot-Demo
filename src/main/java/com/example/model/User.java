package com.example.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private static int sessionNumber;
    private long userBalance;
    private List<GameSession> sessions = new ArrayList<GameSession>();
    private GameSession gameSession;
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);
    private int age;

    public User() {
        this.userBalance = 400;
        this.sessionNumber = 0;
        this.userId = (int) (Math.random() * 2_000_000_000) + 1;
        this.age = (int) (Math.random() * 50) + 21;
//        this.age = 52;
        gameSession = new GameSession(this);
    }

    public GamePlay playSession() {
        if (gameSession.isDone()) {
            gameSession = new GameSession(this);
            sessionNumber += 1;
            userBalance -= 100;
            LOGGER.info("userBalance is " + userBalance);
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

    public int getAge() {
        return age;
    }
}

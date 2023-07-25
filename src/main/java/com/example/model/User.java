package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private static int sessionNumber;
    private long userBalance;
    private List<GameSession> sessions = new ArrayList<GameSession>();

    public User(){
        this.userBalance = 100;
        this.sessionNumber = 0;
        this.userId = (int)(Math.random()*100) + 1;
    }

    public void startSession() throws InterruptedException {
        while (userBalance > 0){
            System.out.println("userBalance is " + userBalance);
            GameSession gameSession = new GameSession(userId);
            gameSession.playGame();
            sessionNumber += 1;
//            userBalance -= gameSession.getSessionBalance();
            userBalance -= 100;
        }
    }

    public void setUserId(){
        userId = (int)(Math.random()*100) + 1;
    }

    public int getUserId() {
        return userId;
    }

    public long getBalance(){
        return userBalance;
    }

    public void addBalance(long amount){
        this.userBalance += amount;
    }
}

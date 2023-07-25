package com.example.model;

import com.example.publisher.generator.StartGenerator;

import java.util.ArrayList;
import java.util.List;

public class GameSession {
    private int userId;
    private int sessionBalance;
    private List<GamePlay> gamePlays = new ArrayList<GamePlay>();
    private static int sessions;
    private int sessionNumber;

    public GameSession(int userId){
        this.userId = userId;
        sessionBalance = 100;
    }


    public void playGame() throws InterruptedException {
        while (sessionBalance > 0){
            System.out.println("sessionBalance is " + sessionBalance);
            GamePlay gamePlay = new GamePlay(userId);
            gamePlays.add(gamePlay);
            gamePlay.startGame();
            long winAmount = gamePlay.playGame();
            sessionBalance += winAmount;
            Thread.sleep(500);
            gamePlay.endGame(winAmount, gamePlay.getGamePlays());
            //ADD TO BLOCKING QUEUE
            StartGenerator.blockingQueue.put(gamePlay);
        }
        this.sessions += 1;
        this.sessionNumber = sessions;
    }

    public int getSessionBalance(){
        return sessionBalance;
    }

    public int getSessionNumber(){
        return sessionNumber;
    }
}

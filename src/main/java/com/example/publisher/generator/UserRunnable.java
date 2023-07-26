package com.example.publisher.generator;

import com.example.model.GamePlay;
import com.example.model.User;

import java.util.concurrent.BlockingQueue;

public class UserRunnable implements Runnable {
    private BlockingQueue blockingQueue;
    User user;

    public UserRunnable(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.user = new User();
    }

    @Override
    public void run() {

        while (!user.isDone()) {
//            System.out.println("Thread " + Thread.currentThread().getName() + ": userId is " + user.getUserId() + " balance is " + user.getBalance());
            GamePlay gamePlay = user.playSession();
            try {
                blockingQueue.put(gamePlay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("END SESSION " + "Thread " + Thread.currentThread().getName() + ": userId is " + user.getUserId() + " user balance is " + user.getBalance());
    }
}

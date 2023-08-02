package com.example.publisher.generator;

import com.example.model.GamePlay;
import com.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class UserRunnable implements Runnable {
    private BlockingQueue blockingQueue;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRunnable.class);
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

        LOGGER.info("End User Thread " + Thread.currentThread().getName() + ": userId=" + user.getUserId() + " userBalance=" + user.getBalance());
    }
}

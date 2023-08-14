package com.example;

import com.example.publisher.GamePlayPublisher;
import com.example.publisher.generator.GameUserManager;

import java.util.concurrent.ArrayBlockingQueue;


public class StartPublisher {
    public static void main(String[] args) throws Exception {


        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(90);


        String endpointURL = args[0];
        String endpointQueue = args[1];
        int numUsers = Integer.parseInt(args[2]);

        GameUserManager gameUserManager = new GameUserManager(blockingQueue, numUsers);
        GamePlayPublisher publisher = new GamePlayPublisher(blockingQueue, endpointURL, endpointQueue);

        publisher.startEventPublisherThreads();
        gameUserManager.generate();
    }
}

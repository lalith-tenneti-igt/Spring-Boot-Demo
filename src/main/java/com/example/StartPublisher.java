package com.example;

import com.example.publisher.GamePlayPublisher;
import com.example.publisher.generator.GameUserManager;

import java.util.concurrent.ArrayBlockingQueue;


public class StartPublisher {
    public static void main(String[] args) throws Exception {


        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(90);

//        System.out.print("Number of generator threads to run: ");
//
//        Scanner scanner = new Scanner(System.in);
//        int numUsers = scanner.nextInt();
        int numUsers = 1;

        GameUserManager gameUserManager = new GameUserManager(blockingQueue, numUsers);
        GamePlayPublisher publisher = new GamePlayPublisher(blockingQueue);

        publisher.startEventPublisherThreads();
        gameUserManager.generate();
    }
}

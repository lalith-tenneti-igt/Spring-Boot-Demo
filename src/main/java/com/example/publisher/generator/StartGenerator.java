package com.example.publisher.generator;

import com.example.model.GamePlay;

import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class StartGenerator {
    static public BlockingQueue<GamePlay> blockingQueue = new ArrayBlockingQueue<>(100);
    static volatile boolean stop = false;

    public static void generate() throws InterruptedException {
        System.out.print("Number of threads to run: ");
        Scanner scanner = new Scanner(System.in);
        int numThread = scanner.nextInt();

        System.out.print("Type stop to terminate all threads\n");
        ThreadManager manager = new ThreadManager();
        manager.addMultipleThreads(numThread);

        Scanner scanner2 = new Scanner(System.in);
        String input = scanner2.nextLine();
        if(input.equals("stop")){
            stop = true;
        }
        scanner.close();
        scanner2.close();
        System.out.println("All threads terminated");
        System.out.println("PUSH DATA TO QUEUE SUCCESSFULLY ");
//        for (GamePlay gamePlay : blockingQueue){
//            System.out.println(gamePlay.getGamePlayNumber());
//        }

        manager.stopAndJoinThreads();
    }

//    public static void main(String[] args) throws InterruptedException {
//        System.out.print("Number of threads to run: ");
//        Scanner scanner = new Scanner(System.in);
//        int numThread = scanner.nextInt();
//
//        System.out.print("Type stop to terminate all threads\n");
//        ThreadManager manager = new ThreadManager();
//        manager.addMultipleThreads(numThread);
//
//        Scanner scanner2 = new Scanner(System.in);
//        String input = scanner2.nextLine();
//        if(input.equals("stop")){
//            stop = true;
//        }
//        scanner.close();
//        scanner2.close();
//        System.out.println("All threads terminated");
//        System.out.println("DISPLAYING ALL SESSIONS: ");
//        for (GamePlay gamePlay : blockingQueue){
//            System.out.println(gamePlay.getGamePlayNumber());
//        }
//
//        manager.stopAndJoinThreads();
//
//    }
}
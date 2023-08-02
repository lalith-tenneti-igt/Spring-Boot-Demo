package com.example.publisher.generator;

import com.example.model.GamePlay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class GameUserManager {
    public BlockingQueue<GamePlay> blockingQueue;
    int numUsers;
    private static final Logger LOGGER = LoggerFactory.getLogger(GameUserManager.class);

    ArrayList<Thread> userThreads;


    volatile boolean stop = false;
    public GameUserManager(BlockingQueue blockingQueue, int numUsers){
        this.blockingQueue = blockingQueue;
        this.numUsers = numUsers;
        userThreads = new ArrayList<>();
    }

    public void generate() {
//        manager.stopAndJoinThreads();
        addMultipleUsers(numUsers);
//        joinUsers();
    }


    public void addMultipleUsers(int numUsers) {
        for (int i = 0; i < numUsers; i++) {
            Thread t = new Thread(new UserRunnable(blockingQueue));
            t.setName(String.valueOf(String.format("UserThread %d", i)));
            userThreads.add(t);
            t.start();
        }
    }

    public void joinUsers() {
        try {
            for (Thread t : userThreads) {
                t.join();
            }
        } catch (Exception e) {
            LOGGER.error("Error joining threads");
        }
    }

//    public static void main(String[] args) {
//        BlockingQueue<GamePlay> bq = new ArrayBlockingQueue<>(100);
//        GameUserManager gum = new GameUserManager(bq, 1);
//
//        gum.generate();
//
//        System.out.println(bq);
//    }


//    public void stop() {
////        vklah vakg vkahg
//    }

//    public void stopAndJoinThreads(){
//        for(Thread thread:userThreads){
//            try{
//                thread.join();
//            } catch(InterruptedException e){
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public void addThread(UserRunnable runnable){
//        Thread thread = new Thread(runnable);
//        userThreads.add(thread);
//    }
//
//    public void startAll(){
//        for (Thread thread:userThreads){
//            thread.start();
//        }
//    }
//
//    public void startThread(int index){
//        userThreads.get(index).start();
//    }
//
//    public static void stopAThread(int index){
//        Thread thread = userThreads.get(index);
//        thread.interrupt();
//        try{
//            thread.join();
//            userThreads.remove(index);
//        } catch (InterruptedException e){
//        }
//        System.out.println("Thread " + index + " is terminated");
//    }


}
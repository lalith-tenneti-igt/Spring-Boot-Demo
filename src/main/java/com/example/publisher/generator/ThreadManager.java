package com.example.publisher.generator;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
    private static List<Thread> threads;

    public ThreadManager(){
        threads = new ArrayList<>();
    }

    public void addMultipleThreads(int numThread) throws InterruptedException {
        Generator userGenerator = new Generator();

        for (int i = 0; i < numThread; i++) {
            Thread t = new Thread(new MyRunnable(userGenerator));
            t.setName(String.valueOf(i));
            threads.add(t);
            t.start();
        }
    }

    public void stopAndJoinThreads(){
        for(Thread thread:threads){
            try{
                thread.join();
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void addThread(MyRunnable runnable){
        Thread thread = new Thread(runnable);
        threads.add(thread);
    }

    public void startAll(){
        for (Thread thread:threads){
            thread.start();
        }
    }

    public void startThread(int index){
        threads.get(index).start();
    }

    public static void stopAThread(int index){
        Thread thread = threads.get(index);
        thread.interrupt();
        try{
            thread.join();
            threads.remove(index);
        } catch (InterruptedException e){
        }
        System.out.println("Thread " + index + " is terminated");
    }
}

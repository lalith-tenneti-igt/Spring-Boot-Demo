package com.example.publisher.generator;

import com.example.model.User;

public class Generator {
    public Generator() {
    }
    public void generate() throws InterruptedException {
        User user = new User();
        while(!StartGenerator.stop){
            while (user.getBalance() > 0){
                Thread.sleep(1000);
                System.out.println("Thread "+ Thread.currentThread().getName() + ": userId is " + user.getUserId() + " balance is "+ user.getBalance());
                user.startSession();
            }
//            System.out.println("TERMINATE " + (Integer.parseInt(Thread.currentThread().getName())-1));
            ThreadManager.stopAThread(Integer.parseInt(Thread.currentThread().getName()));
            Thread.sleep(1000);
            System.out.println("END SESSION " + "Thread " + Thread.currentThread().getName() + ": userId is " + user.getUserId() + " balance is "+ user.getBalance());
            StartGenerator.stop = true;
        }
    }
}

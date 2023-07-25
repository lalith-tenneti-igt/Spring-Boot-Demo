package com.example.publisher.generator;

public class MyRunnable implements Runnable {
    private Generator generator;
//    private boolean stop;

    public MyRunnable(Generator userGenerator){
        generator = userGenerator;
//        this.stop = stop;
    }

    @Override
    public void run() {
        try {
            generator.generate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

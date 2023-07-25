package com.example;

import com.example.publisher.GamePlayPublisher;

public class StartPublisher {
    public static void main(String[] args) throws Exception {
        GamePlayPublisher publisher = new GamePlayPublisher();
        publisher.setBlockingQueue();
        publisher.startEventPublisherThreads();
        Thread.sleep(3000);
        publisher.stop();
//        consumer.interrupt();
//        consumer.join();
    }
}

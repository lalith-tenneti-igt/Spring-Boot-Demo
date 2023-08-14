package com.example.publisher;


import com.example.model.GamePlay;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

public class GamePlayPublisher {
    private Connection connection;
    private Channel channel;
    private boolean isRunning = true;
    private BlockingQueue<GamePlay> blockingQueue;

    public Thread publisherThread;

    private static final Logger LOGGER = LoggerFactory.getLogger(GamePlayPublisher.class);
    private String endpointURL;
    private String endpointQueue;

    public GamePlayPublisher(BlockingQueue blockingQueue, String endpointURL, String endpointQueue) {
        this.blockingQueue = blockingQueue;
        //create rabbitmq connection and channel
        ConnectionFactory connectionFactory = new ConnectionFactory();
        this.endpointQueue = endpointQueue;
        this.endpointURL = endpointURL;
        try {
            connection = connectionFactory.newConnection(this.endpointURL);
            channel = connection.createChannel();
            channel.queueDeclare(this.endpointQueue, true, false, false, null);
            LOGGER.info("CHANNEL CREATED");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Start event publisher thread
     */
    public void startEventPublisherThreads() {
        publisherThread = new Thread(new EventPublisherThread(this.endpointURL, this.endpointQueue));
        publisherThread.start();
    }

    /**
     * EventPublisherThread: publish rabbitmq event to rabbitmq server
     */
    private class EventPublisherThread implements Runnable {
        private String endpointURL;
        private String endpointQueue;
        public EventPublisherThread(String endpointURL, String endpointQueue) {
            super();
            this.endpointURL = endpointURL;
            this.endpointQueue = endpointQueue;
        }
        @Override
        public void run(){
                try{
                    while (isRunning || !blockingQueue.isEmpty()){
                        GamePlay gamePlay = blockingQueue.take();
                        if (channel.isOpen()) {
                            byte[] data = serializeGamePlay(gamePlay);
                            channel.basicPublish("", this.endpointQueue, null, data);
                            LOGGER.info("Serialized and published: " + gamePlay);
                        }
                        else{
                            LOGGER.info("Channel is closed");
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                    LOGGER.error("Error publishing to channel");
                }
        }
    }

    public void stop()
    {
        try {
            LOGGER.info("Publisher thread stops");
            isRunning = false;
            blockingQueue.clear();
            channel.close();
            connection.close();
            LOGGER.info("Channel is closed");
        } catch (Exception e) {
        }
    }

    private byte[] serializeGamePlay(GamePlay session) throws IOException {
        // Serialize the session object
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
        try {
            objectStream.writeObject(session);
        } catch (Exception e) {
            System.out.println(e);
        }
        objectStream.flush();
        return byteStream.toByteArray();
    }

}

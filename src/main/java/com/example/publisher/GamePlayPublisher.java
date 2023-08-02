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

    public GamePlayPublisher(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
        //create rabbitmq connection and channel
        ConnectionFactory connectionFactory = new ConnectionFactory();
        try {
            connection = connectionFactory.newConnection(CommonConfigs.AMQP_URL);
            channel = connection.createChannel();
            channel.queueDeclare(CommonConfigs.DEFAULT_QUEUE, true, false, false, null);
            LOGGER.info("CHANNEL CREATED");
        } catch (Exception e){
        }
    }

    /**
     * Start event publisher thread
     */
    public void startEventPublisherThreads() {
        publisherThread = new Thread(new EventPublisherThread());
        publisherThread.start();
    }

    /**
     * EventPublisherThread: publish rabbitmq event to rabbitmq server
     */
    private class EventPublisherThread implements Runnable {
        @Override
        public void run(){
                try{
                    while (isRunning || !blockingQueue.isEmpty()){
                        GamePlay gamePlay = blockingQueue.take();
                        if (channel.isOpen()) {
                            byte[] data = serializeGamePlay(gamePlay);
                            channel.basicPublish("", CommonConfigs.DEFAULT_QUEUE, null, data);
                            LOGGER.info("Serialized and published: " + gamePlay);
                        }
                        else{
                            LOGGER.info("Channel is closed");
                        }
                    }
                }
                catch(Exception e){
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

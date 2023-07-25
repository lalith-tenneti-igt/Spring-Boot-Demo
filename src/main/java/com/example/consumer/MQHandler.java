package com.example.consumer;

import com.example.model.GamePlay;
import com.rabbitmq.client.*;
import org.kie.api.runtime.KieSession;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MQHandler {
    public KieSession kieSession;
    private static class Factory {
        private static MQHandler INSTANCE = new MQHandler();
    }

    public static MQHandler getInstance() {
        return Factory.INSTANCE;
    }

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
    }

    private static GamePlay deserializePlayerSession(byte[] serializedData) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteStream = new ByteArrayInputStream(serializedData);
        ObjectInputStream objectStream = new ObjectInputStream(byteStream);
        return (GamePlay) objectStream.readObject();
    }

    public void subscribeMessage(Listener callback) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection(CommonConfigs.AMQP_URL);
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (s, delivery) -> {
            try {
                byte[] serializedData = delivery.getBody();
                GamePlay gamePlay = deserializePlayerSession(serializedData);
                callback.process(kieSession, gamePlay);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        CancelCallback cancelCallback = (s) -> {
            System.out.println(s);
        };
        channel.basicConsume(CommonConfigs.DEFAULT_QUEUE, true, deliverCallback, cancelCallback);
    }
}

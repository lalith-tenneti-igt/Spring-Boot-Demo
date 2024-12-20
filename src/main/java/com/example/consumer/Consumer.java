package com.example.consumer;

import com.example.model.GamePlay;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Consumer extends Thread {

    private MQHandler mq;

    public static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    public Consumer(){
        mq = MQHandler.getInstance();
    }

    public Consumer(KieSession kSession, EntryPoint gamePlayStream) {
        mq = MQHandler.getInstance();
        mq.setKieSession(kSession);
        mq.setEp(gamePlayStream);
    }

    @Override
    public void run() {
        try {
            mq.subscribeMessage(new Listener() {
                @Override
                public void process(KieSession kSession, GamePlay gamePlay, EntryPoint entryPoint) throws Exception {
                    try {
//                        int gamePlayId = gamePlay.getGamePlayId();
                        LOGGER.info("Received gamePlay " + gamePlay + " winAMount is " + gamePlay.getWinAmount() + " userId is " + gamePlay.getUserId());
//                        FactHandle gamePlaysHandle = kSession.insert(gamePlay);
//                        gamePlayStream.insert(gamePlayEvent);
                        entryPoint.insert(gamePlay);
                        kSession.fireAllRules();
                    }
                    catch (Exception e) {
                        System.err.println("Error inserting fact: " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}



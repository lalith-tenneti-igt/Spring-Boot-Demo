package com.example.consumer;

import com.example.model.GamePlay;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class Consumer extends Thread {

    private MQHandler mq;

    public Consumer(){
        mq = MQHandler.getInstance();
    }

    public Consumer(KieSession kSession) {
        mq = MQHandler.getInstance();
        mq.setKieSession(kSession);
    }

    @Override
    public void run() {
        try {
            mq.subscribeMessage(new Listener() {
                @Override
                public void process(KieSession kSession, GamePlay gamePlay) throws Exception {
                    try {
                        int gamePlayNumber = gamePlay.getGamePlayId();
                        System.out.println("Received gamePlay. gamePlayId is " + gamePlayNumber);
                        FactHandle gamePlaysHandle = kSession.insert(gamePlay);
//                        System.out.println("handle is " + gamePlaysHandle);
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



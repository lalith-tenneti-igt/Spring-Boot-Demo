package com.example.controller;

import com.example.consumer.Consumer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class ConsumerController {
    @Autowired
//    private KieContainer kieContainer;
    private KieSession kieSession;

    @PostConstruct
    public void startConsumer() throws InterruptedException {
//        KieSession kieSession = kieContainer.newKieSession();
        EntryPoint gamePlayStream = kieSession.getEntryPoint("GamePlayStream");



        Consumer consumerService = new Consumer(kieSession, gamePlayStream);
        consumerService.start();
//        consumer.interrupt();
//        consumer.join();
    }

}
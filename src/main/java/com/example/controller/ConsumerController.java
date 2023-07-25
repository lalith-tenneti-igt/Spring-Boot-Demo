package com.example.controller;

import com.example.consumer.Consumer;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;

@Controller
public class ConsumerController {
    @Autowired
    private KieContainer kieContainer;

    @PostConstruct
    public void startConsumer() throws InterruptedException {
        KieSession kieSession = kieContainer.newKieSession();
        Consumer consumerService = new Consumer(kieSession);
        consumerService.start();
//        consumer.interrupt();
//        consumer.join();
    }

}
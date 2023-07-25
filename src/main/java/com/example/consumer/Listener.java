package com.example.consumer;

import com.example.model.GamePlay;
import org.kie.api.runtime.KieSession;

public interface Listener {
    void process(KieSession kieSession, GamePlay gamePlay) throws Exception;

}


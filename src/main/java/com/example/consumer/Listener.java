package com.example.consumer;

import com.example.model.GamePlay;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.EntryPoint;

public interface Listener {
    void process(KieSession kieSession, GamePlay gamePlay, EntryPoint entryPoint) throws Exception;

}


package com.example.config;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

//    private static final String RULES_CUSTOMER_RULES_DRL = "rules/customer-discount.drl";
    private static final String GAMEPLAY_RULES_DRL = "rules/gamePlay.drl";
    private static final KieServices kieServices = KieServices.Factory.get();

    @Bean
    public KieSession kieSession() {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(GAMEPLAY_RULES_DRL));
        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        KieBaseConfiguration config = kieServices.newKieBaseConfiguration();
        config.setOption(EventProcessingOption.STREAM);
        KieBase streamKieBase = kieContainer.newKieBase(config);
        KieSession kieSession = streamKieBase.newKieSession();

        return kieSession;
    }
}
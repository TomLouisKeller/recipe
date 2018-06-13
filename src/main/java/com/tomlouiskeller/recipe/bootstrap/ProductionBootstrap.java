package com.tomlouiskeller.recipe.bootstrap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Profile({"production"})
@Component
public class ProductionBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private BasicBootstrap basicBootstrap;

    public ProductionBootstrap(BasicBootstrap basicBootstrap) {
        this.basicBootstrap = basicBootstrap;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("Bootstrapping Production Data");
        basicBootstrap.initCategories();
        basicBootstrap.initUnitOfMeasurements();
    }
}

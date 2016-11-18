package com.millross.spring.boot.vertx.configuration;

import com.millross.spring.boot.vertx.verticle.DemoVerticle;
import io.vertx.core.Vertx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class VertxConfiguration {

    @Bean
    public Vertx vertx() {
        return Vertx.vertx();
    }

    @Bean
    public DemoVerticle serviceVerticle(@Autowired Vertx vertx) {
        final DemoVerticle demoVerticle = new DemoVerticle();
        vertx.deployVerticle(demoVerticle); // We should strictly block while we do this and wrap in a future but being lazy
        return demoVerticle;
    }

}

package com.millross.spring.boot.vertx;

import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 */
@Service
public class SimpleVertxEBService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleVertxEBService.class);

    private Vertx vertx;

    @Autowired
    public SimpleVertxEBService(final Vertx vertx) {
        this.vertx = vertx;
    }

    public int invoke() throws Exception { // Ugly hack to throw exception and probably needs better consideration
        final CompletableFuture<Integer> future = new CompletableFuture<>();
        LOG.info("About to send to the eventbus");
        vertx.eventBus().<Integer>send("vertx.demo", "Latest", result -> {
            if (result.succeeded()) {
                future.complete(result.result().body());
            } else {
                future.completeExceptionally(result.cause());
            }
        });
        LOG.info("About to block the current thread for up to 2 seconds");
        final Integer result =  future.get(2, TimeUnit.SECONDS);
        LOG.info("About to return {}", result);
        return result;
    }

}

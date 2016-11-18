package com.millross.spring.boot.vertx.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class DemoVerticle extends AbstractVerticle {

    private static final Logger LOG = LoggerFactory.getLogger(DemoVerticle.class);

    int invocationCounter = 0;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        super.start(startFuture);
        LOG.info("Starting DemoVerticle");
        vertx.eventBus().<String>consumer("vertx.demo").handler(this::handleMessage);
    }

    private void handleMessage(final Message<String> message) {
        LOG.info("Message: {} received", message.body());
        invocationCounter++;
        LOG.info("Replying with value {}", invocationCounter);
        message.reply(new Integer(invocationCounter));
    }
}

package com.myhub.traffic.dark.horse.starter;

import com.myhub.traffic.dark.horse.dispatch.RequestEventConsumer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

public class RequestEventConsumerStarter implements ApplicationListener<ApplicationReadyEvent> {
    public void start() {
        new Thread(() ->{
            RequestEventConsumer requestEventConsumer = new RequestEventConsumer();
            requestEventConsumer.consume();
        }).start();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        start();
    }
}

package com.myhub.traffic.dark.horse.starter;

import com.myhub.traffic.dark.horse.dispatch.RequestEventConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class RequestEventConsumerStarter implements ApplicationListener<ContextRefreshedEvent> {
    public void start() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                RequestEventConsumer requestEventConsumer = new RequestEventConsumer();
                requestEventConsumer.consume();
            }
        });
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        start();
        log.info("RequestEventConsumerStarter started.");
    }
}

package com.myhub.traffic.dark.horse;

import com.myhub.traffic.dark.horse.starter.RequestEventConsumerStarter;
import com.myhub.traffic.dark.horse.statistics.RequestStatistics;

import java.util.Map;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Request request = new Request();

        // 启动统计汇总打印
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Map<String, Integer> statistics =
                    RequestStatistics.statistics();

            System.out.println(statistics);
        }, 0, 1, TimeUnit.SECONDS);

        // 启动监听消费
        RequestEventConsumerStarter requestEventConsumerStarter = new RequestEventConsumerStarter();
        requestEventConsumerStarter.start();

        Thread thread100 = new Thread(() -> {
            for (;;) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request.newRequest(String.valueOf(100));
            }
        });

        Thread thread200 = new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request.newRequest(String.valueOf(200));
            }
        });

        Thread thread300 = new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                request.newRequest(String.valueOf(300));
            }
        });


        thread100.start();
        thread200.start();
        thread300.start();

        thread100.join();
        thread200.join();
        thread300.join();
    }
}

package com.myhub.traffic.dark.horse.dispatch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Dispatcher {
    public static BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>(2000000);

    public static void dispatch(String resource) {
        try {
            QUEUE.put(resource);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

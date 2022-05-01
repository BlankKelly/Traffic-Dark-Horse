package com.myhub.traffic.dark.horse.dispatch;

import com.myhub.traffic.dark.horse.core.ResourceSlidingWindowCache;
import com.myhub.traffic.dark.horse.core.SlidingWindow;

public class RequestEventConsumer {
    public void consume() {
        while (true) {
            try {
                String resource = Dispatcher.QUEUE.take();
                SlidingWindow slidingWindow = ResourceSlidingWindowCache.getWindow(resource);
                slidingWindow.addCount(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

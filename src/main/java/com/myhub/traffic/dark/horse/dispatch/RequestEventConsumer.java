package com.myhub.traffic.dark.horse.dispatch;

import com.myhub.traffic.dark.horse.core.ResourceSlidingWindowCache;
import com.myhub.traffic.dark.horse.core.SlidingWindow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestEventConsumer {
    public void consume() {
        while (true) {
            try {
                String resource = Dispatcher.QUEUE.take();
                log.debug("resource: {}", resource);
                SlidingWindow slidingWindow = ResourceSlidingWindowCache.getWindow(resource);
                slidingWindow.addCount(1);
                log.debug("slidingWindow count: {}", slidingWindow.count());
            } catch (InterruptedException e) {
                log.error("consume error: ", e);
                e.printStackTrace();
            }
        }
    }
}

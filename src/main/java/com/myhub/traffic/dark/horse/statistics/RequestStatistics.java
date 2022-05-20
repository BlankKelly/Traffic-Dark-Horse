package com.myhub.traffic.dark.horse.statistics;

import com.myhub.traffic.dark.horse.core.ResourceSlidingWindowCache;
import com.myhub.traffic.dark.horse.core.SlidingWindow;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Slf4j
public class RequestStatistics {
    public static Map<String, Integer> statistics() {
        Map<String, Integer> countStatistics = new HashMap<>();
        ConcurrentMap<@NonNull String, @NonNull Object> snapshot = ResourceSlidingWindowCache.getCACHE().asMap();
        log.debug("snapshot: {}", snapshot);

        Set<Map.Entry<@NonNull String, @NonNull Object>> entries = snapshot.entrySet();
        Iterator<Map.Entry<@NonNull String, @NonNull Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<@NonNull String, @NonNull Object> next = iterator.next();
            String key = next.getKey();
            SlidingWindow slidingWindow = (SlidingWindow) next.getValue();

            countStatistics.put(key, slidingWindow.count());
        }

        return countStatistics.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getValue() - entry1.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));
    }
}

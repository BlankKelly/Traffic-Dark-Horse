package com.myhub.traffic.dark.horse.core;

import com.github.benmanes.caffeine.cache.Cache;
import java.util.function.Function;

public class ResourceSlidingWindowCache {
    private static final Cache<String, Object> CACHE = CaffeineBuilder.buildAllKeyCache();

    public static SlidingWindow getWindow(String key) {
        return (SlidingWindow) CACHE.get(key, (Function<String, SlidingWindow>) s -> {
            return new SlidingWindow(1, 1000);
        });
    }

    public static Cache<String, Object> getCACHE() {
        return CACHE;
    }
}

package com.myhub.traffic.dark.horse.core;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CaffeineBuilder {
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    /**
     * 构建所有来的要缓存的key cache
     */
    public static Cache<String, Object> buildAllKeyCache() {
        //老版本jdk1.8.0_20之前，caffeine默认的forkJoinPool在及其密集的淘汰过期时，会有forkJoinPool报错。建议用新版jdk
        return Caffeine.newBuilder()
                .initialCapacity(200)//初始大小
                .maximumSize(1000)//最大数量。这个数值我设置的很大，按30万每秒，每分钟是1800万，实际可以调小
                .executor(executorService)
                .softValues()
                .build();
    }
}

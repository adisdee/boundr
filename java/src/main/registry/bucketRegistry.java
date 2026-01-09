package main.registry;

import java.util.concurrent.ConcurrentHashMap;

import main.core.tokenBucket;
import main.time.systemTimeProvider;

public class bucketRegistry {
    // a thread safe map that return correct bucket as per the key

    private final int bucketCapcity;
    private final long refillInterval;
    private final systemTimeProvider timeProvider;
    private static final long bucketTTL = 5 * 60 * 1000;

    private final ConcurrentHashMap<String, tokenBucket> buckets = new ConcurrentHashMap<>();

    // configs

    public bucketRegistry(int bucketCapcity, long refillInterval, systemTimeProvider timeProvider) {
        this.bucketCapcity = bucketCapcity;
        this.refillInterval = refillInterval;
        this.timeProvider = timeProvider;
    }

    // get and creation of bucket

    public tokenBucket getBucket(String key) {
        return buckets.computeIfAbsent(key,
                k -> new tokenBucket(bucketCapcity, refillInterval, timeProvider));
    }

    public void showBuckets() {
        buckets.forEach((key, bucket) -> System.out.println(key + "->" + bucket));
    }

    // unused buckets cleanup

    public void cleanupUnusedBuckets() {
        long now = timeProvider.nowMillis();

        buckets.entrySet().removeIf(entry -> {
            tokenBucket bucket = entry.getValue();
            return now - bucket.getLastAccessTime() > bucketTTL;
        });
    }

}

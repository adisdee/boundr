package main.registry;

import java.util.concurrent.ConcurrentHashMap;

import main.core.TokenBucket;
import main.time.TimeProvider;

/*
 * Registry for managing buckets per key
 */

public class BucketRegistry {

    private final int bucketCapacity;
    private final long refillInterval;
    private final TimeProvider timeProvider;
    private static final long BUCKET_TTL_MS = 5 * 60 * 1000;

    private final ConcurrentHashMap<String, TokenBucket> buckets = new ConcurrentHashMap<>();

    public BucketRegistry(int bucketCapacity, long refillInterval, TimeProvider timeProvider) {
        this.bucketCapacity = bucketCapacity;
        this.refillInterval = refillInterval;
        this.timeProvider = timeProvider;
    }

    /*
     * Returns existing bucket for the key or creates new one
     */

    public TokenBucket getBucket(String key) {
        return buckets.computeIfAbsent(key,
                k -> new TokenBucket(bucketCapacity, refillInterval, timeProvider));
    }

    /*
     * Remove unused buckets that are not accessed within TTL
     */

    public void cleanupUnusedBuckets() {
        long now = timeProvider.now();

        buckets.entrySet().removeIf(entry -> {
            TokenBucket bucket = entry.getValue();
            return now - bucket.getLastAccessTime() > BUCKET_TTL_MS;
        });
    }
}

package main.core;

import main.time.TimeProvider;

/*
 * TokenBucket implements token bucket rate limiting.
 * Each request or call consume a token and token are refilled after a fixed time interval
 */

public class TokenBucket {
    private final int bucketCapcity;
    private int tokens;
    private final long refillInterval;
    private long lastAccessTime;
    private long lastRefillTime;
    private final TimeProvider timeProvider;

    public TokenBucket(int bucketCapacity, long refillInterval, TimeProvider timeProvider) {
        this.bucketCapcity = bucketCapacity;
        this.tokens = bucketCapacity;
        this.lastRefillTime = timeProvider.now();
        this.timeProvider = timeProvider;
        this.refillInterval = refillInterval;
        this.lastAccessTime = lastRefillTime;
    };

    /*
     * Consume one token from the bucket.
     * 
     * @return true if token was consumed, false if limit exceeded
     */

    public synchronized boolean consumeToken() {
        lastAccessTime = timeProvider.now();

        refillBucketIfNeeded();

        if (tokens <= 0)
            return false;
        tokens--;
        return true;
    };

    /*
     * Refills the bucket if refill interval has elapsed
     */

    private void refillBucketIfNeeded() {
        long now = timeProvider.now();
        long elapsed = now - lastRefillTime;

        if (elapsed >= refillInterval) {
            tokens = bucketCapcity;
            lastRefillTime = now;
        }
    };

    /*
     * @return last access time in ms of token bucket
     */

    public long getLastAccessTime() {
        return lastAccessTime;
    }
}

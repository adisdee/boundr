package main.core;

import main.time.systemTimeProvider;

public class tokenBucket {
    private final int bucketCapcity;
    private int tokens;
    private final long refillInterval;
    private long lastAccessTime;
    private long lastRefillTime;
    private final systemTimeProvider timeProvider;

    public tokenBucket(int bucketCapcity, long refillInterval, systemTimeProvider timeProvider) {
        this.bucketCapcity = bucketCapcity;
        this.tokens = bucketCapcity;
        this.lastRefillTime = timeProvider.nowMillis();
        this.timeProvider = timeProvider;
        this.refillInterval = refillInterval;
    };

    public synchronized boolean consumeToken() {
        lastAccessTime = timeProvider.nowMillis();
        // checks the last refill time of bucket and compare between refillinterval
        // if last refill time is greater than refillinterval

        refillBucketIfNeeded();

        if (tokens <= 0)
            return false;
        tokens--;
        return true;
    };

    public void refillBucketIfNeeded() {
        // comparison between current time and last refill time

        long now = timeProvider.nowMillis();
        long elapsed = now - lastRefillTime;

        if (elapsed >= refillInterval) {
            tokens = bucketCapcity;
            lastRefillTime = now;
        }
    };

    public long getLastAccessTime() {
        return lastAccessTime;
    }

    @Override
    public String toString() {
        return "{ tokens" + tokens + "bucket" + bucketCapcity + " }";
    }

    public int getTokens() {
        return tokens;
    }
}

package main.core;

import main.time.systemTimeProvider;

public class tokenBucket {
    private final int bucketCapcity;
    private int tokens;
    private long lastRefillTime;
    private final systemTimeProvider timeProvider;

    public tokenBucket(int bucketCapcity, long refillInterval, systemTimeProvider timeProvider) {
        this.bucketCapcity = bucketCapcity;
        this.tokens = bucketCapcity;
        this.lastRefillTime = timeProvider.nowMillis();
        this.timeProvider = timeProvider;
    }

    public int getTokens() {
        return tokens;
    }

    public String toString() {
        return "token" + tokens + "," + "lastRefillTime" + lastRefillTime + "capcity" + bucketCapcity;
    }
}

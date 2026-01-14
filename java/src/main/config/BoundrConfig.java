package main.config;

public class BoundrConfig {
    private final int bucketCapcity;
    private final long refillInterval;

    public BoundrConfig(int bucketCapcity, long refillInterval) {
        this.bucketCapcity = bucketCapcity;
        this.refillInterval = refillInterval;
    }

    public int getCapcity() {
        return bucketCapcity;
    }

    public long getRefillInterval() {
        return refillInterval;
    }
}

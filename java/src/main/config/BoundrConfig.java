package main.config;

/* Configuration for boundr rate limiter */

public class BoundrConfig {
    private final int bucketCapacity;
    private final long refillInterval;

    public BoundrConfig(int bucketCapacity, long refillInterval) {

        if (bucketCapacity <= 0) {
            throw new IllegalArgumentException("Bucket capacity must be greater than 0");
        }

        if (refillInterval <= 0) {
            throw new IllegalArgumentException("Refill Interval must be greater than 0");
        }

        this.bucketCapacity = bucketCapacity;
        this.refillInterval = refillInterval;
    }

    /* @return bucket capcity */

    public int getCapacity() {
        return bucketCapacity;
    }

    /* @return bucket refill interval */
    
    public long getRefillInterval() {
        return refillInterval;
    }
}

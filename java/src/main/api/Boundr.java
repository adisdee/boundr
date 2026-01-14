package main.api;

import main.config.BoundrConfig;
import main.registry.BucketRegistry;
import main.time.SystemTimeProvider;
import main.time.TimeProvider;

/* Public API for rate limiting */

public class Boundr {
    private final BucketRegistry registry;

    public Boundr(BoundrConfig config) {
        this(config, new SystemTimeProvider());
    };

    public Boundr(BoundrConfig config, TimeProvider timeProvider) {
        this.registry = new BucketRegistry(config.getCapacity(), config.getRefillInterval(), timeProvider);
    };

    /* @return true if request is allowed, false otherwise */

    public boolean allow(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key must not be null");
        }
        return registry.getBucket(key).consumeToken();
    };

    /* Cleanup of unused buckets */

    public void cleanupBuckets() {
        registry.cleanupUnusedBuckets();
    };
}

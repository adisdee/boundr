package main.api;

import main.config.boundrConfig;
import main.registry.bucketRegistry;
import main.time.systemTimeProvider;

public class boundr {
    private final bucketRegistry registry;

    public boundr(boundrConfig config) {
        this.registry = new bucketRegistry(config.getCapcity(), config.getRefillInterval(), new systemTimeProvider());
    }

    // needs to pass a key that will go in registry then goes to token bucket it
    // verifies and return boolean

    public boolean allow(String key) {
        return registry.getBucket(key).consumeToken();
    }

    public int getTokens(String key) {
        return registry.getBucket(key).getTokens();
    }

    // clear buckets that are not used for last 5 minutes

    public void cleanupBuckets() {
        registry.cleanupUnusedBuckets();
    }
}

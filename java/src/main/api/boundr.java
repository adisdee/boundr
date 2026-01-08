package main.api;

import main.registry.bucketRegistry;
import main.time.systemTimeProvider;

public class boundr {
    private final bucketRegistry registry;

    public boundr(int bucketCapcity, long refillInterval) {
        this.registry = new bucketRegistry(bucketCapcity, refillInterval, new systemTimeProvider());
    }

    // needs to pass a key that will go in registry then goes to token bucket it
    // verifies and return boolean

    public boolean allow(String key) {
        return registry.getBucket(key).consumeToken();
    }

    public int getTokens(String key) {
        return registry.getBucket(key).getTokens();
    }
}

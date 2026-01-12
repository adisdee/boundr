package test.registry;

import main.registry.bucketRegistry;
import main.time.systemTimeProvider;

public class bucketRegistryTest {
    public static void main(String[] args) {
        // tests for registry

        systemTimeProvider time = new systemTimeProvider();
        bucketRegistry br = new bucketRegistry(20, 6000, time);

        // bucket creation

        br.getBucket("user123");

        // shows buckets that are stored
        
        br.showBuckets();
    }
}

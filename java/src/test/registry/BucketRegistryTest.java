package test.registry;

import main.core.TokenBucket;
import main.registry.BucketRegistry;
import main.time.SystemTimeProvider;

/*
 * Test that perform bucket usage and cleanup
 */

public class BucketRegistryTest {
    public static void main(String[] args) throws InterruptedException {
        SystemTimeProvider time = new SystemTimeProvider();
        BucketRegistry registry = new BucketRegistry(10, 5000, time);

        /* Must return the same bucket */

        TokenBucket b1 = registry.getBucket("user123");
        TokenBucket b2 = registry.getBucket("user123");

        assert b1 == b2 : "Same key must return the same bucket.";

        /* Must return different buckets to different keys */

        TokenBucket b3 = registry.getBucket("user12");

        assert b1 != b3 : "Different keys must return different buckets.";

        /* Waiting for to expire TTL */

        System.out.println("Waiting for bucket TTL to expire...");

        /* TTL must be configured 2 Mins */

        Thread.sleep(3 * 60 * 1000);

        /* Cleanup of unused buckets from memory */

        registry.cleanupUnusedBuckets();

        /* Buckets needs to recreated for the same user after bucket cleanup */

        TokenBucket b4 = registry.getBucket("user123");

        assert b1 != b4 : "Bucket should be recreated after cleanup";

        System.out.println("REGISTRY TEST PASSED");
    }

}
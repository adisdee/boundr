/* Test that perform bucket usage and cleanup */

import { BucketRegistry } from "../../src/registry/BucketRegistry";
import { SystemTimeProvider } from "../../src/time/SystemTimeProvider";

async function run() {
    const time = new SystemTimeProvider();
    const registry = new BucketRegistry(3, 3500, time);

    const b1 = registry.getBucket("user123");
    const b2 = registry.getBucket("user123");

    console.assert(b1 == b2, "Same key must return same bucket");

    const b3 = registry.getBucket("user12");
    console.assert(b1 !== b3, "For different key must return different bucket");

    console.log("Waiting for bucket TTL...");

    await new Promise(r => setTimeout(r, 10000));

    registry.cleanupUnusedBuckets();

    const b4 = registry.getBucket("user123");
    console.assert(b4 !== b1, "Bucket must be recreated after cleanup");

    console.log("BUCKET REGISTRY TEST PASSED");
}
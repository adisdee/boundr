import { BoundrConfig } from "../config/BoundrConfig";
import { BucketRegistry } from "../registry/BucketRegistry";
import { SystemTimeProvider } from "../time/SystemTimeProvider";
import { TimeProvider } from "../time/TimeProvider";

/* Public API for rate limiting */

export class Boundr {
  private readonly registry: BucketRegistry;

  constructor(config: BoundrConfig, timeProvider?: TimeProvider) {
    const time = timeProvider ?? new SystemTimeProvider();

    this.registry = new BucketRegistry(
      config.getCapacity(),
      config.getRefillInterval(),
      time
    );
  }

  /* @return true if request is allowed, false otherwise */

  public allow(key: string): boolean {
    if (!key || key.trim().length === 0) {
      throw new Error("Key must not be empty");
    }
    return this.registry.getBucket(key).consumeToken();
  }

  /* Cleanup of unused buckets */

  public cleanupBuckets(): void {
    this.registry.cleanupUnusedBuckets();
  }
}

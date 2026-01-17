import { TokenBucket } from "../core/TokenBucket";
import { TimeProvider } from "../time/TimeProvider";

/* Registry for managing buckets per key */

export class BucketRegistry {
  private readonly bucketCapacity: number;
  private readonly refillInterval: number;
  private readonly timeProvider: TimeProvider;
  private readonly BUCKET_TTL_MS = 5 * 60 * 1000;

  private buckets: Map<String, TokenBucket> = new Map();

  constructor(
    bucketCapacity: number,
    refillInterval: number,
    timeProvider: TimeProvider
  ) {
    this.bucketCapacity = bucketCapacity;
    this.refillInterval = refillInterval;
    this.timeProvider = timeProvider;
  }

  /* @return existing bucket for the key or create new bucket */

  public getBucket(key: String): TokenBucket {
    const existBucket = this.getBucket(key);

    if (existBucket) {
      return existBucket;
    }

    const newBucket = new TokenBucket(
      this.bucketCapacity,
      this.refillInterval,
      this.timeProvider
    );

    this.buckets.set(key, newBucket);
    return newBucket;
  }

  /* remove unused buckets that are not accessed within TTL */

  public cleanupUnusedBuckets(): void {
    const currentTime = this.timeProvider.now();

    for (const [key, bucket] of this.buckets.entries()) {
      if (currentTime - bucket.getLastAccessTime() > this.BUCKET_TTL_MS) {
        this.buckets.delete(key);
      }
    }
  }
}

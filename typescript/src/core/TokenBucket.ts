import { TimeProvider } from "../time/TimeProvider";

/* TokenBucket implements token bucket rate limiting
 * Each request consume a token and tokens are refilled at fixed time interval
 */

export class TokenBucket {
  private readonly bucketCapacity: number;
  private tokens: number;
  private readonly refillInterval: number;
  private lastAccessTime: number;
  private lastRefillTime: number;
  private timeProvider: TimeProvider;

  constructor(
    bucketCapacity: number,
    refillInterval: number,
    timeProvider: TimeProvider
  ) {
    this.bucketCapacity = bucketCapacity;
    this.tokens = this.bucketCapacity;
    this.refillInterval = refillInterval;
    this.lastRefillTime = timeProvider.now();
    this.lastAccessTime = this.lastRefillTime;
    this.timeProvider = timeProvider;
  }

  /* @return true if token was consumed, false if limit exceeded */

  public consumToken(): boolean {
    this.lastAccessTime = this.timeProvider.now();

    this.refillBucketIfNeeded();

    if (this.tokens <= 0) {
      return false;
    }

    this.tokens--;
    return true;
  }

  /* refill the bucket if refill interval has elapsed */

  private refillBucketIfNeeded(): void {
    const currentTime = this.timeProvider.now();
    const elapsed = currentTime - this.lastRefillTime;

    if (elapsed >= this.refillInterval) {
      this.tokens = this.bucketCapacity;
      this.lastRefillTime = currentTime;
    }
  }

  /* @return last access time in ms of token bucket */

  public getLastAccessTime(): number {
    return this.lastAccessTime;
  }
}

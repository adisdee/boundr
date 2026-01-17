/* Configuration for boundr rate limiter */

export class BoundrConfig {
  private readonly bucketCapacity: number;
  private readonly refillInterval: number;

  constructor(bucketCapacity: number, refillInterval: number) {
    if (bucketCapacity <= 0) {
      throw new Error("Bucket capacity must be greater than 0");
    }

    if (refillInterval <= 0) {
      throw new Error("Refill interval must be greater than 0");
    }

    this.bucketCapacity = bucketCapacity;
    this.refillInterval = refillInterval;
  }

  /* @return bucket capacity */

  public getCapacity(): number {
    return this.bucketCapacity;
  }

  /* @return bucket refill interval */

  public getRefillInterval(): number {
    return this.refillInterval;
  }
}

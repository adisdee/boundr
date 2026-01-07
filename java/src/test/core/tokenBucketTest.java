package test.core;

import main.core.tokenBucket;
import main.time.systemTimeProvider;

public class tokenBucketTest {
    public static void main(String[] args) throws InterruptedException {
        systemTimeProvider time = new systemTimeProvider();
        tokenBucket bucket = new tokenBucket(10, 3000, time);

        for (int i = 1; i <= 10; i++) {
            System.out.println("Started consuming tokens...");
            bucket.consumeToken();
            System.out.println(bucket);
        }

        System.out.println("Waiting for refilling");
        Thread.sleep(3000);

        System.out.println("After refill again consume single token... " + bucket.consumeToken());
        System.out.println(bucket);
    }
}

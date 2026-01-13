package test.core;

import main.core.TokenBucket;
import main.time.SystemTimeProvider;
import main.time.TimeProvider;

/*
 * Test that performs token consumption and refilling     
 */

public class TokenBucketTest {
    public static void main(String[] args) throws InterruptedException {
        TimeProvider time = new SystemTimeProvider();
        TokenBucket bucket = new TokenBucket(3, 2000, time);

        /* Consume all tokens */

        assert bucket.consumeToken();
        assert bucket.consumeToken();
        assert bucket.consumeToken();

        /* Request needs to be rejected when bucket is empty */

        assert !bucket.consumeToken() : "Bucket must reject when empty";

        System.out.println("All tokens exhausted");

        /* Refilling bucket */

        System.out.println("Waiting for refill..");
        Thread.sleep(5000);

        /* Request needs to be allowed when bucket is refilled */

        assert bucket.consumeToken() : "Bucket should refiil after interval";

        System.out.println("TOKEN BUCKET TEST PASSED");

    }
}

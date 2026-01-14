package test.boundr;

import main.api.Boundr;
import main.config.BoundrConfig;

/* Test that performs boundr api testing */

public class BoundrTest {
    public static void main(String[] args) throws InterruptedException {
        BoundrConfig config = new BoundrConfig(2, 15000);
        Boundr boundr = new Boundr(config);

        /* Sending request */
        
        assert boundr.allow("user123");
        assert boundr.allow("user123");

        assert boundr.allow("user123") : "Request should be blocked";

        System.out.println("User blocked");

        /* Refilling tokens */

        System.out.println("Waiting to refill tokens");
        Thread.sleep(25000);

        /* Again sending request */

        assert boundr.allow("user123") : "Request should be allowed";

        System.out.println("BOUNDR TEST PASSED");
    }
}
package test.boundr;

import main.api.boundr;

public class boundrTest {
    public static void main(String[] args) throws InterruptedException {
        boundr b = new boundr(20, 2000);

        for (int i = 1; i < 15; i++) {
            System.out.println("Using token from the bucket..." + b.allow("user123"));
            System.out.println("Token left: " + b.getTokens("user123"));
        }

        System.out.println("Refilling Bucket...");
        Thread.sleep(2000);

        for (int i = 1; i < 10; i++) {
            System.out.println("After Refilling Bucket Again hitting request..." + b.allow("user123"));
            System.out.println("Token left: " + b.getTokens("user123"));
        }

        System.out.println(b.getTokens("user123"));
    }
}

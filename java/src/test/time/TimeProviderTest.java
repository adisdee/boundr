package test.time;

import main.time.SystemTimeProvider;
import main.time.TimeProvider;

/*
 * Test that system time provider returns moving forward time 
 */

public class TimeProviderTest {
    public static void main(String[] args) {
        TimeProvider time = new SystemTimeProvider();

        long t1 = time.now();
        long t2 = time.now();

        assert t2 >= t1 : "Time Need to move forward";
    }
}

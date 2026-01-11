package main.time;

/*
 * Implementation of TimeProvider using the system clock.
 */

public class SystemTimeProvider implements TimeProvider {
    public long now() {
        return System.currentTimeMillis();
    }
}

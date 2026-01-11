package main.time;

public class systemTimeProvider implements timeProvider {
    public long nowMillis() {
        // return current system in ms
        return System.currentTimeMillis();
    }
}

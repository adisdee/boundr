package main.time;

/*
 * Provides current time in milliseconds
 */

public interface TimeProvider {
    /*
     * @return current time in milliseconds
     */
    long now();
}

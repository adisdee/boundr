package test.time;

import main.time.systemTimeProvider;

public class timeProviderTest {
    public static void main(String[] args) {
        // return system current time in milliseconds
        
        systemTimeProvider time = new systemTimeProvider();
        System.out.println(time.nowMillis());
    }
}

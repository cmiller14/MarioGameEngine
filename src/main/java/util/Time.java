package util;

public class Time {
    public final static float TIME_STARTED = System.nanoTime();

    public static float getTime() {
        return (float)((System.nanoTime()- TIME_STARTED) * 1E-9);
    }
}

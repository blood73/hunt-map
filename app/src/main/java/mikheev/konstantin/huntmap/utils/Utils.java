package mikheev.konstantin.huntmap.utils;

import java.util.Random;

public class Utils {

    private static int DAYS_PERIOD = 20;
    private static int SECONDS_PERIOD = DAYS_PERIOD * 60 * 60 * 24;

    public static Long getCurrentTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static Long getGeneratedTimestamp() {
        Long currentTimestamp = getCurrentTimestamp();
        long LOWER_RANGE = currentTimestamp - SECONDS_PERIOD;
        long UPPER_RANGE = currentTimestamp + SECONDS_PERIOD;
        Random random = new Random();

        return LOWER_RANGE +  (long) (random.nextDouble() * (UPPER_RANGE - LOWER_RANGE));
    }
}
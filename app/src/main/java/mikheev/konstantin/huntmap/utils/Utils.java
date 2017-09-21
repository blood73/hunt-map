package mikheev.konstantin.huntmap.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

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

    public static String getDateFromTimestamp(long timestamp) {
        String result = "";

        try {
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("d MMM ''yy", new Locale("ru"));
            Date currenTimeZone = (Date) calendar.getTime();
            result = sdf.format(currenTimeZone);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
package utils;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * @author kang
 * @date 2022/7/5
 */
public class LogUtils {

    private LogUtils() {
        
    }

    private static final String LOG_PATTERN = "[{0}]-[{1}]:{2}";

    public static void log(String message) {
        System.out.println(
            MessageFormat.format(LOG_PATTERN, Thread.currentThread().getName(), LocalDateTime.now(), message));
    }

}

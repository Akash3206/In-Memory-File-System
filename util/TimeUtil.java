package util;

import java.time.LocalDateTime;

public class TimeUtil {

    private static LocalDateTime currDateTime = LocalDateTime.now();

    public static LocalDateTime now() {
        return currDateTime;
    }
}
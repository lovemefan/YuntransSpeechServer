/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 16:31
 */
package com.yuntrans.common.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
    private static final DateFormat READABLE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static final DateFormat READABLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private static final DateTimeFormatter COMMON_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getReadableTime() {
        return READABLE_TIME_FORMAT.format(Calendar.getInstance().getTime());
    }

    public static String getReadableDate() {
        return READABLE_DATE_FORMAT.format(Calendar.getInstance().getTime());
    }

    public static String getCustomizeTime(String format) {
        return (new SimpleDateFormat(format)).format(Calendar.getInstance().getTime());
    }

    public static Date now() {
        return Calendar.getInstance().getTime();
    }

    public static String toReadableTime(Date time) {
        return READABLE_TIME_FORMAT.format(time);
    }

    public static String getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(COMMON_DATETIME_FORMATTER);
    }
}

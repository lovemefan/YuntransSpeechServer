/**
 * @author Lovemefan
 * @email lovemefan@outlook.com
 * @date 2022/11/21 17:42
 */
package com.yuntrans.common.logGather.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    static TimeZone timeZone = TimeZone.getTimeZone("Asia/Shanghai");

    static {
        simpleDateFormat.setTimeZone(timeZone);
    }

    public static Long getTime(Long time) {
        Long now = System.currentTimeMillis();
        if (time == null || time.intValue() == 0)
            return now;
        if (startLogTime() > time || time > endLogTime())
            return now;
        return time;
    }

    private static Long startLogTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private static Long endLogTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.get(5) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static synchronized String getCurrentTime() {
        return simpleDateFormat.format(new Date());
    }
}

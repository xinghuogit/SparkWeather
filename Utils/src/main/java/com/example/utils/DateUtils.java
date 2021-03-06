package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期：2019/3/26 17:39
 * 创建：李加蒙
 * 描述：
 */
public class DateUtils {

    /**
     * @param day 0是当天  1是昨天
     * @return 获取凌晨时间
     */
    public static long getTodayStartTime(int day) {
        long nowTime = System.currentTimeMillis();
        long todayStartTime = (nowTime - (nowTime + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24)) - ((1000 * 3600 * 24) * day);
        return todayStartTime;
    }

    /**
     * @param time
     * @param str  "yyyy-MM-dd HH:mm:ss"   yyyy年MM月dd日  yyyyMMddHHmmss
     * @return
     */
    public static String getDateTime(long time, String str) {
        time = isMs(time);
        String dateStr = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat(str, Locale.getDefault());// 获取当前时间，进一步转化为字符串
            Date date = new Date(time);
            dateStr = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateStr;
    }

    public static long isMs(long time) {
        return StringUtils.isMs(time + "") ? time : time * 1000;
    }
}

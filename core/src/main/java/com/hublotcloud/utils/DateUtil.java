package com.hublotcloud.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 */
public class DateUtil {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前日期，如果没有指定日期格式，默认：yyyy-MM-dd HH:mm:ss
     */
    public static final String getCurrentTime(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat("".equals(pattern) ? YYYYMMDD_HHMMSS : pattern);
        return sdf.format(new Date());
    }

    // java8 获取当前时间的：年
    public static final int getCurrentYear() {
        LocalDate current = LocalDate.now();
        return current.getYear();
    }

    // java8 获取当前时间的：月
    public static final int getCurrentMonth() {
        LocalDate current = LocalDate.now();
        return current.getMonthValue();
    }

    // java8 获取本月第一天
    public static final LocalDate getFirstDayOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        return LocalDate.of(today.getYear(), today.getMonth(), 1);
    }

    // java8 获取本月最后一天
    public static final LocalDate getLastDayOfCurrentMonth() {
        LocalDate today = LocalDate.now();
        return today.with(TemporalAdjusters.lastDayOfMonth());
    }

    // java.util.Date 转换为：java.time.LocalDate
    public static final LocalDate date2LocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDate(); // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime
    }
    public static final Date localDate2Date(LocalDate localDate) {
        // LocalDate localDate = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();
        return Date.from(instant);
    }

    // 字符串转日期类型
    public static final Date stringToDate(String dateString, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateString);
    }

    // 日期类型转换成字符串
    public static final String dateToString(Date date, String pattern) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(null == pattern ? YYYYMMDD_HHMMSS : pattern);
        return sdf.format(date);
    }

    // 计算年龄
    public static final int calculateAge(String dateString, String pattern) throws Exception {
        // 当前年份
        Calendar c = Calendar.getInstance();
        int yearNow = c.get(Calendar.YEAR);

        // 参数年份
        c.setTime(stringToDate(dateString, YYYYMMDD));
        int yearParam = c.get(Calendar.YEAR);

        return yearNow - yearParam;
    }

    // 计算当前日期和参数日期的差，返回秒（返回正数则参数是之前的日期；返回负数则参数是当前日期之后的日期）
    public static final long calStringDateMinus(String datetime) throws Exception {
        Date startDate = stringToDate(datetime, YYYYMMDD_HHMMSS);
        long between = (new Date().getTime() - startDate.getTime()) / 1000; // 将毫秒转化成秒
        return between;
    }

    // 计算两个日期差，返回秒
    public static final long calTwoStringDateMinus(String startTime, String endTime, String pattern) throws Exception {
        Date startDate = stringToDate(startTime, pattern);
        Date endDate = stringToDate(endTime, pattern);
        long between = (endDate.getTime() - startDate.getTime()) / 1000; // 将毫秒转化成秒
        return between;
    }

    /**
     * 计算两个字符串日期差，返回天
     * @param startTime
     * @param endTime
     * @param pattern
     * @return
     * @throws Exception
     */
    public static final long calTwoStringDateDay(String startTime, String endTime, String pattern) throws Exception {
        Date startDate = stringToDate(startTime, pattern);
        Date endDate = stringToDate(endTime, pattern);
        return (endDate.getTime() - startDate.getTime()) / 1000 / 60 / 60 / 24;
    }

    // 计算某个时间的之前或之后的几小时（currentDay 为true的话，就是当前时间，第一个指定具体时间的参数无效）
    public static final String getBeforeOrAfterHour(String date, int hour, boolean currentDay) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDD_HHMMSS);
        Calendar c = Calendar.getInstance();
        if (!currentDay) {
            c.setTime(sdf.parse(date));
        }
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) + hour);
        return sdf.format(c.getTime());
    }

    // 计算某个时间的之前或之后的几分钟（currentDay 为true的话，就是当前时间，第一个指定具体时间的参数无效）
    public static final String getBeforeOrAfterMinute(String date, String pattern, int minute, boolean currentDay) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar c = Calendar.getInstance();
        if (!currentDay) {
            c.setTime(sdf.parse(date));
        }
        c.add(Calendar.MINUTE, minute);
        return sdf.format(c.getTime());
    }

    // 计算某个时间在不在一个时间段的范围之内（包括前后的临界点）
    public static boolean belongBetween(String start, String end, String target, String pattern) throws Exception {
        Date startTime = stringToDate(start, pattern);
        Date endTime = stringToDate(end, pattern);
        Date targetTime = stringToDate(target, pattern);
        if (targetTime.after(startTime) && targetTime.before(endTime)) {
            return true;
        } else if (0 == targetTime.compareTo(startTime) || 0 == targetTime.compareTo(endTime)) {
            return true;
        }
        return false;
    }

    // 将 2019:03:17 10:48:06 转换成标准格式：2019-03-17 10:48:06
    public static String transferDateFormat(String originFormat) {
        String datePart = originFormat.substring(0, 10);
        String timePart = originFormat.substring(10);
        return datePart.replaceAll(":", "-") + timePart;
    }

    // 测试
    public static void main(String[] args) throws Exception {
//        String string = "2019:03:17 10:48:06";
//        System.out.println(transferDateFormat(string));

//        System.out.println(0 < stringToDate("20280203").compareTo(new Date())); // > 1; = 0; < -1
//        System.out.println(calculateAge("19780228", YYYYMMDD));
//        System.out.println(dateToString(new Date(), YYYY_MM));
//        System.out.println(calStringDateMinus("2018-08-07 13:50:00"));
//        System.out.println(getNextMonth("2018-9"));
//        System.out.println(calTwoStringDateMinus("2018-08-07 13:50:00", "2018-08-07 14:00:00"));
//        System.out.println(calTwoStringDateMinus("2018-08-07 14:15:00", dateToString(new Date(), YYYYMMDDHHMMSS)) / 60);
//        System.out.println(getBeforeOrAfterHour("2018-08-10 08:00:00", -1, true));
//        System.out.println(getBeforeOrAfterMinute("2018-08-10 07:00", YYYY_MM_DD_HH_MM, -60, false));
//        System.out.println(calTwoStringDateMinus("2018-08-20 23:12:00", "2018-08-20 23:15:00", YYYYMMDDHHMMSS) / 60);
//        System.out.println(calTwoStringDateDay("2018-08-23 18:13:00", "2018-08-22 13:12:00", YYYYMMDDHHMMSS));
//        System.out.println(belongBetween("2018-08-20 06:00", "2018-08-20 12:30", "2018-08-20 05:01", YYYY_MM_DD_HH_MM));


//        String s = "2018-08-10 07:00";
//        System.out.println(s.substring(0, 10));
//        System.out.println(s.substring(11));

//        System.out.println(getBeforeOrAfterHour("2019-1-4 23:23:23", 3 * 24, true));

//        System.out.println(getCurrentTime(YYYYMMDDHHMMSS));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
//        System.out.println(LocalDate.parse("2019-01-01", formatter));

//        System.out.println(getCurrentYear());
//        System.out.println(getCurrentMonth());

//        System.out.println(date2LocalDate(new Date()).getYear());

        System.out.println(getFirstDayOfCurrentMonth());
        System.out.println(getLastDayOfCurrentMonth());
    }

}

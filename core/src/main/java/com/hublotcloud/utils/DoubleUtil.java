package com.hublotcloud.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtil {

    public static final String TWO_DECIMAL = "#.00";
    public static final String FOUR_DECIMAL = "#.0000";
    public static final String SIX_DECIMAL = "#.000000";

    public static final String MONEY_COMMA_FORMAT = "###,###.00";

    public static final String formatBigDecimal(BigDecimal money, String pattern) {
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        return decimalFormat.format(money);
    }

    /**
     * 将字符串格式化输出
     *
     * @param s
     * @param pattern
     * @return
     */
    public static final String formatString(String s, String pattern) {
        double d = Double.parseDouble(s);
        if (0 == d) {
            return s;
        }
        DecimalFormat df = new DecimalFormat(pattern);
        return df.format(d);
    }

    /**
     * Double 转 BigDecimal (防止精度丢失)
     *
     * @param d
     * @return
     */
    public static final BigDecimal double2BigDecimal(double d) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
//        BigDecimal bigDecimal = BigDecimal.valueOf(d);
        return bigDecimal;
    }

    // 测试
    public static void main(String[] args) {
//        System.out.println(formatString("120.04586499999999", SIX_DECIMAL));
        System.out.println(String.format("%.2f", Double.parseDouble("1200000000.04586499999999")));

//        System.out.println(formatString("0.000", SIX_DECIMAL));
//        System.out.println(String.format("%.6f", Double.parseDouble("0.0")));

//        BigDecimal a = BigDecimal.valueOf(0);
//        BigDecimal b = BigDecimal.valueOf(0.00);
//        System.out.println(0 != a.compareTo(b));
//        System.out.println(a.compareTo(b));         // 大于：1；等于：0；小于：-1
//
//        System.out.println(formatBigDecimal(BigDecimal.valueOf(1000000), MONEY_COMMA_FORMAT));

//        System.out.println(double2BigDecimal(1.05));

        // 金额计算
//        BigDecimal x = BigDecimal.valueOf(1000);
//        BigDecimal a = BigDecimal.valueOf(500);
//        BigDecimal b = BigDecimal.valueOf(450);
//        BigDecimal c = BigDecimal.valueOf(550.25);
//        System.out.println(b.subtract(a)); // 减法
//        System.out.println(c.subtract(a)); // 减法
//        System.out.println("-------------------------");
//        System.out.println(x.add(b.subtract(a)));
//        System.out.println(x.add(c.subtract(a)));

    }

}

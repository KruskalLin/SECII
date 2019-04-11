//package ui.util;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class DateUtil {
//    public static String defaultDatePattern = "yyyy-MM-dd";
//    public static String defaultDatePatternNotFlag = "yyyyMMdd";
//
//    public static String getDatePattern() {
//        return defaultDatePattern;
//    }
//
//
//    public static String getToday() {
//        Date today = new Date();
//        return format(today);
//    }
//    public static String getNotFlagToday() {
//        Date today = new Date();
//        return formatNotFlagDate(today);
//    }
//
//    public static String format(Date date) {
//        return format(date, getDatePattern());
//    }
//
//    public static String formatNotFlagDate(Date date) {
//        return format(date, defaultDatePatternNotFlag);
//    }
//
//    public static String format(Date date, String pattern) {
//        String returnValue = "";
//
//        if (date != null) {
//            SimpleDateFormat df = new SimpleDateFormat(pattern);
//            returnValue = df.format(date);
//        }
//
//        return (returnValue);
//    }
//
//    public static Date parse(String strDate) throws ParseException {
//        return parse(strDate, getDatePattern());
//    }
//
//
//    public static Date parse(String strDate, String pattern) throws ParseException {
//        SimpleDateFormat df = new SimpleDateFormat(pattern);
//        return df.parse(strDate);
//    }
//
//
//
//}
//

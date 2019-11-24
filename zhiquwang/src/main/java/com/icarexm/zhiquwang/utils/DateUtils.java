package com.icarexm.zhiquwang.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class DateUtils {


    private final static long minute = 60 * 1000;
    private final static long hour = 60 * minute;
    private final static long day = 24 * hour;
    private final static long month = 31 * day;
    private final static long year = 12 * month;

    public static String getTodayDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日",
                Locale.getDefault());
        return format.format(new Date());
    }

    public static String getMonthDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月",
                Locale.getDefault());
        return format.format(new Date());
    }

    public static String getTodayDateHour() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm",
                Locale.getDefault());
        return format.format(new Date());
    }

    public static String getTodayHour() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm",
                Locale.getDefault());
        return format.format(new Date());
    }

    public static String getTodaySecond() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss",
                Locale.getDefault());
        return format.format(new Date());
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp1(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime()/1000;
        return    ts;
    }


    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss",
                Locale.getDefault());

        Date date = new Date(System.currentTimeMillis());
        return format.format(date);
    }


    public static String dateToStamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }


    public static String dateToStamp2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd HH:mm",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }


    public static String date2TimeStamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;
    }


    public static String timeStamp2Time2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    public static String timeStamp2Date(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    public static String timeStamp2Date2(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    public static String timeStamp2Date3(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    public static String timeStamp2Date4(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM月dd日");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;

    }


    @SuppressLint("SimpleDateFormat")
    public static Date parseDate(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse(strDate);
        } catch (ParseException e) {

        }
        return returnDate;

    }

    public static Date parseDateHm(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse(strDate);
        } catch (ParseException e) {

        }
        return returnDate;

    }

    public static int getMonth(String dateStr) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        int month = 0;
        try {
            Date date = dateFormat.parse(dateStr);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(date);

            int year = calendar.get(Calendar.YEAR);

            month = calendar.get(Calendar.MONTH) + 1;

            int day = calendar.get(Calendar.DAY_OF_MONTH);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return month;
    }


    public static Date addDate(Date date, int dayNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, dayNum);
        return calendar.getTime();
    }


    public static Date addMonth(Date date, int monthNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthNum);
        return calendar.getTime();
    }


    public static Date addYear(Date date, int yearNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, yearNum);
        return calendar.getTime();
    }


    @SuppressLint("SimpleDateFormat")
    public static String formatDate(String str, int days) {

        String dateStr = "";
        try {
            DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
            Date date = df.parse(str);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
            Calendar c = Calendar.getInstance();
            Date d = dateFormat.parse(dateFormat.format(date));
            c.setTime(d);
            c.add(Calendar.DAY_OF_MONTH, days);
            switch (c.get(Calendar.DAY_OF_WEEK) - 1) {
                case 0:
                    dateStr = "星期日";
                    break;
                case 1:
                    dateStr = "星期一";
                    break;
                case 2:
                    dateStr = "星期二";
                    break;
                case 3:
                    dateStr = "星期三";
                    break;
                case 4:
                    dateStr = "星期四";
                    break;
                case 5:
                    dateStr = "星期五";
                    break;
                case 6:
                    dateStr = "星期六";
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateStr;
    }


    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }


    public static int getTwoDateDiff(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    public static String getWeek(String pTime) {


        String Week = "星期";


        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week += "天";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "六";
        }



        return Week;
    }


    public static  boolean isDate(String str_input, String rDateFormat){
        if (!isNull(str_input)) {
            SimpleDateFormat formatter = new SimpleDateFormat(rDateFormat);
            formatter.setLenient(false);
            try {
                formatter.format(formatter.parse(str_input));
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }
    public static boolean isNull(String str){
        if(str==null)
            return true;
        else
            return false;
    }

}

package com.icarexm.zhiquwang.custview.Wheel;

import android.view.View;


import com.icarexm.zhiquwang.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WheelTime {
    private View view;
    private WheelView wv_year;
    private WheelView wv_month;
    private WheelView wv_day;
    private WheelView wv_hours;
    private WheelView wv_mins;
    public int screenheight;
    private boolean hasSelectTime;
    private static int START_YEAR = 1990, END_YEAR = 2100;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getSTART_YEAR() {
        return START_YEAR;
    }

    public static void setSTART_YEAR(int sTART_YEAR) {
        START_YEAR = sTART_YEAR;
    }

    public static int getEND_YEAR() {
        return END_YEAR;
    }

    public static void setEND_YEAR(int eND_YEAR) {
        END_YEAR = eND_YEAR;
    }

    public WheelTime(View view) {
        super();
        this.view = view;
        hasSelectTime = true;
        setView(view);
    }

    public WheelTime(View view, boolean hasSelectTime) {
        super();
        this.view = view;
        this.hasSelectTime = hasSelectTime;
        setView(view);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        if (!hasSelectTime) {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append(".")
                    .append((wv_month.getCurrentItem() + 1)).append(".")
                    .append((wv_day.getCurrentItem() + 1));

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = DateUtils.parseDate(sb.toString());

            return format.format(date);

        }else {
            sb.append((wv_year.getCurrentItem() + START_YEAR)).append(".")
                    .append((wv_month.getCurrentItem() + 1)).append(".")
                    .append((wv_day.getCurrentItem() + 1)).append(" ")
                    .append(wv_hours.getCurrentItem()).append(":")
                    .append(wv_mins.getCurrentItem());

            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            Date date = DateUtils.parseDateHm(sb.toString());

            return format.format(date);
        }


    }
}

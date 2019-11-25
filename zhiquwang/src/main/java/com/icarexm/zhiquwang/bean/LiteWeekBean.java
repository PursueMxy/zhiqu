package com.icarexm.zhiquwang.bean;

import org.litepal.crud.LitePalSupport;

public class LiteWeekBean extends LitePalSupport {
    private String week_name;
    private int week_code;
    private int slt_code;

    public int getWeek_code() {
        return week_code;
    }

    public void setWeek_code(int week_code) {
        this.week_code = week_code;
    }

    public String getWeek_name() {
        return week_name;
    }

    public void setWeek_name(String week_name) {
        this.week_name = week_name;
    }

    public int getSlt_code() {
        return slt_code;
    }

    public void setSlt_code(int slt_code) {
        this.slt_code= slt_code;
    }


}

package com.icarexm.zhiquwang.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class LiteSubsidyBean extends LitePalSupport {
    @Column(unique = true, defaultValue = "unknown")
    private int subsidy_id;
    private String subsidy_name;
    private int price;

    public int getSubsidy_id() {
        return subsidy_id;
    }

    public void setSubsidy_id(int subsidy_id) {
        this.subsidy_id = subsidy_id;
    }

    public String getSubsidy_name() {
        return subsidy_name;
    }

    public void setSubsidy_name(String subsidy_name) {
        this.subsidy_name = subsidy_name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

   public LiteSubsidyBean(){

    }
    public LiteSubsidyBean(int subsidy_id, String subsidy_name, int price) {
        this.subsidy_id = subsidy_id;
        this.subsidy_name = subsidy_name;
        this.price = price;
    }
}

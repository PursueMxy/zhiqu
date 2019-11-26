package com.icarexm.zhiquwang.bean;

import java.util.List;

public class CityNameBean {

    /**
     * code : 1
     * msg : success
     * data : [{"area_id":350200,"area_name":"厦门市"},{"area_id":350500,"area_name":"泉州市"},{"area_id":350600,"area_name":"漳州市"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * area_id : 350200
         * area_name : 厦门市
         */

        private int area_id;
        private String area_name;

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }
    }
}

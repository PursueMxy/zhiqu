package com.icarexm.zhiquwang.bean;

import java.util.List;

public class AreaBean {

    /**
     * code : 1
     * msg : success
     * data : [{"area_id":350502,"area_name":"鲤城区"},{"area_id":350503,"area_name":"丰泽区"},{"area_id":350504,"area_name":"洛江区"},{"area_id":350505,"area_name":"泉港区"},{"area_id":350521,"area_name":"惠安县"},{"area_id":350524,"area_name":"安溪县"},{"area_id":350525,"area_name":"永春县"},{"area_id":350526,"area_name":"德化县"},{"area_id":350527,"area_name":"金门县"},{"area_id":350581,"area_name":"石狮市"},{"area_id":350582,"area_name":"晋江市"},{"area_id":350583,"area_name":"南安市"}]
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
         * area_id : 350502
         * area_name : 鲤城区
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

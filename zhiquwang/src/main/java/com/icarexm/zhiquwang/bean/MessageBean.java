package com.icarexm.zhiquwang.bean;

import java.util.List;

public class MessageBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":3,"per_page":"20","current_page":1,"last_page":1,"data":[{"id":3,"type":3,"price":"15.00","create_time":"2019-11-19 10:22:23","is_read":2,"message":"入职满30天，获得15.00元佣金"},{"id":2,"type":2,"price":"10.00","create_time":"2019-11-19 10:22:12","is_read":2,"message":"邀请好友王晓晓成功入职，获得10.00元佣金"},{"id":1,"type":1,"price":"10.00","create_time":"2019-11-19 10:22:02","is_read":2,"message":"邀请好友黑铁5成功注册，获得10.00元佣金"}]}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"id":3,"type":3,"price":"15.00","create_time":"2019-11-19 10:22:23","is_read":2,"message":"入职满30天，获得15.00元佣金"},{"id":2,"type":2,"price":"10.00","create_time":"2019-11-19 10:22:12","is_read":2,"message":"邀请好友王晓晓成功入职，获得10.00元佣金"},{"id":1,"type":1,"price":"10.00","create_time":"2019-11-19 10:22:02","is_read":2,"message":"邀请好友黑铁5成功注册，获得10.00元佣金"}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 3
             * type : 3
             * price : 15.00
             * create_time : 2019-11-19 10:22:23
             * is_read : 2
             * message : 入职满30天，获得15.00元佣金
             */

            private int id;
            private int type;
            private String price;
            private String create_time;
            private int is_read;
            private String message;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}

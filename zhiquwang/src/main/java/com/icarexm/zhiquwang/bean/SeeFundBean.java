package com.icarexm.zhiquwang.bean;

import java.util.List;

public class SeeFundBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total_price":"10.00","list":{"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-19 17:50:08"},{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-16 20:12:50"}]}}
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
         * total_price : 10.00
         * list : {"total":2,"per_page":10,"current_page":1,"last_page":1,"data":[{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-19 17:50:08"},{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-16 20:12:50"}]}
         */

        private String total_price;
        private ListBean list;

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * total : 2
             * per_page : 10
             * current_page : 1
             * last_page : 1
             * data : [{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-19 17:50:08"},{"price":"10.00","content":"提现","audit":1,"create_time":"2019-11-16 20:12:50"}]
             */

            private int total;
            private int per_page;
            private int current_page;
            private int last_page;
            private List<DataBean> data;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
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
                 * price : 10.00
                 * content : 提现
                 * audit : 1
                 * create_time : 2019-11-19 17:50:08
                 */

                private String price;
                private String content;
                private int audit;
                private String create_time;

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getAudit() {
                    return audit;
                }

                public void setAudit(int audit) {
                    this.audit = audit;
                }

                public String getCreate_time() {
                    return create_time;
                }

                public void setCreate_time(String create_time) {
                    this.create_time = create_time;
                }
            }
        }
    }
}

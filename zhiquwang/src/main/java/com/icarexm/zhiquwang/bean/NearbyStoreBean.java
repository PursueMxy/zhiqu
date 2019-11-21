package com.icarexm.zhiquwang.bean;

import java.util.List;

public class NearbyStoreBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":1,"per_page":"30","current_page":1,"last_page":1,"data":[{"alliance_id":2,"shop_name":"取不出名字","address":"福建省厦门市湖里区江头街道园山南路66厦门信息学校","longitude":"118.133308","latitude":"24.502971","mobile":"13333333333","distance":"3.48Km","office_hours":"08:00-18:00"}]}
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
         * total : 1
         * per_page : 30
         * current_page : 1
         * last_page : 1
         * data : [{"alliance_id":2,"shop_name":"取不出名字","address":"福建省厦门市湖里区江头街道园山南路66厦门信息学校","longitude":"118.133308","latitude":"24.502971","mobile":"13333333333","distance":"3.48Km","office_hours":"08:00-18:00"}]
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
             * alliance_id : 2
             * shop_name : 取不出名字
             * address : 福建省厦门市湖里区江头街道园山南路66厦门信息学校
             * longitude : 118.133308
             * latitude : 24.502971
             * mobile : 13333333333
             * distance : 3.48Km
             * office_hours : 08:00-18:00
             */

            private int alliance_id;
            private String shop_name;
            private String address;
            private String longitude;
            private String latitude;
            private String mobile;
            private String distance;
            private String office_hours;

            public int getAlliance_id() {
                return alliance_id;
            }

            public void setAlliance_id(int alliance_id) {
                this.alliance_id = alliance_id;
            }

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getOffice_hours() {
                return office_hours;
            }

            public void setOffice_hours(String office_hours) {
                this.office_hours = office_hours;
            }
        }
    }
}

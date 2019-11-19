package com.icarexm.zhiquwang.bean;

public class WalletBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"balance":"0.00","withdrawal":"10"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * balance : 0.00
         * withdrawal : 10
         */

        private String balance;
        private String withdrawal;

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getWithdrawal() {
            return withdrawal;
        }

        public void setWithdrawal(String withdrawal) {
            this.withdrawal = withdrawal;
        }
    }
}

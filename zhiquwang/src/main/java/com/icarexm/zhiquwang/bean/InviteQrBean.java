package com.icarexm.zhiquwang.bean;

public class InviteQrBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"qr_code":"data:image/png;base64,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"}
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
         * qr_code : data:image/png;base64,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
         */

        private String qr_code;

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }
    }
}

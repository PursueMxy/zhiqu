package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.BasePayBean;

public interface BasePayContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, BasePayBean.DataBean data);
        void UpdateUI(int code,String msg);
    }

    interface Presenter {
    }
}

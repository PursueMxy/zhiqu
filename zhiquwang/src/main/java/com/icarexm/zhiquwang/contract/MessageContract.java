package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.MessageBean;

public interface MessageContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code ,String msg, MessageBean.DataBeanX data);
    }

    interface Presenter {
    }
}

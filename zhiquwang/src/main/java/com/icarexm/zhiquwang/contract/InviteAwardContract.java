package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.SeeFundBean;

public interface InviteAwardContract {
    interface Model {
    }

    interface View {
        void Update(int code, String msg, SeeFundBean.DataBeanX data);
    }

    interface Presenter {
    }
}

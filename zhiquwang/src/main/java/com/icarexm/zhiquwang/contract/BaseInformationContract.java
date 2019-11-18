package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.BaseInforBean;

public interface BaseInformationContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, BaseInforBean.DataBean data);
    }

    interface Presenter {
    }
}

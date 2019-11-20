package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.CertificationBean;

public interface CertificationContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg);
        void UpdateUI(int code, String msg, CertificationBean.DataBean dataBean);
    }

    interface Presenter {
    }
}

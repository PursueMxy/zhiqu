package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.HomeDataBean;

public interface FamousRecruitmentContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg, HomeDataBean.DataBeanX data);
    }

    interface Presenter {
    }
}

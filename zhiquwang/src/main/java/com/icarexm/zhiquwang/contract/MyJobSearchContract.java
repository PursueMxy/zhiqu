package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.JobSearchBean;

public interface MyJobSearchContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, JobSearchBean.DataBeanX data);
    }

    interface Presenter {
    }
}

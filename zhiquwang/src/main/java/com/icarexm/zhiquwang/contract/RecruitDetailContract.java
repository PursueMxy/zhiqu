package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.bean.JobDetailBean;

public interface RecruitDetailContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, JobDetailBean.DataBean dataBean);
        void UpdateUI(int code,String msg, HomeDataBean.DataBeanX data);
    }

    interface Presenter {
    }
}

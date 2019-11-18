package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.JobDetailBean;

public interface RecruitDetailContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, JobDetailBean.DataBean dataBean);
    }

    interface Presenter {
    }
}

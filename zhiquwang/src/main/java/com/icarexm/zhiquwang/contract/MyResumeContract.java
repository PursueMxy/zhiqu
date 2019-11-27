package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.ResumeBean;

public interface MyResumeContract {
    interface Model {
    }

    interface View {
        void  UpdateUI(int code,String msg, ResumeBean.DataBean data);
        void UpdateUI(int code,String msg);
    }

    interface Presenter {
    }
}

package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.RepairInfoBean;

public interface AttendanceCardContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, RepairInfoBean.DataBean data);
        void UpdateUI(int code,String msg);
    }

    interface Presenter {
    }
}

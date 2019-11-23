package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.RepairInfoBean;

public interface PunchCardRecordContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, RepairInfoBean.DataBean data);
    }

    interface Presenter {
    }
}

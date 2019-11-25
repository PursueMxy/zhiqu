package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.ClockRemindedInfoBean;

public interface SetReminderContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg);
        void UpdateUI(int code,String msg, ClockRemindedInfoBean.DataBean data);
    }

    interface Presenter {
    }
}

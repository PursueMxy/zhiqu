package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.StatisticsBean;

public interface OvertimeStatisticsContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String message, StatisticsBean.DataBean data);
    }

    interface Presenter {
    }
}

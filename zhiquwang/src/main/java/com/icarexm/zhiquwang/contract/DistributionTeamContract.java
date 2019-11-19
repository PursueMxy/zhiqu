package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.TeamBean;

public interface DistributionTeamContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg, TeamBean.DataBean data);
    }

    interface Presenter {
    }
}

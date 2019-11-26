package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.OvertimeApproverBean;

public interface OvertimeApprovalContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg);
        void UpdateUI(int code, String msg, OvertimeApproverBean.DataBean data);

    }

    interface Presenter {
    }
}

package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.MyBankBean;

import java.util.List;

public interface CashWithdrawalContract {
    interface Model {
    }

    interface View {
        void BankList(int code, String msg, List<MyBankBean.DataBean> data);
    }

    interface Presenter {
    }
}

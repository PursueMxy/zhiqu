package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.MyBankBean;

import java.util.List;

public interface CashWithdrawalContract {
    interface Model {
    }

    interface View {
        void BankList(int code, String msg, List<MyBankBean.DataBean> data);
        void UpdateUI(int code,String msg);
    }

    interface Presenter {
    }
}

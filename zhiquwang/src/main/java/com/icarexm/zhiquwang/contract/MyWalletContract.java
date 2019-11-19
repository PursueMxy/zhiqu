package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.WalletBean;

public interface MyWalletContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code, String msg, WalletBean.DataBean data);
    }

    interface Presenter {
    }
}

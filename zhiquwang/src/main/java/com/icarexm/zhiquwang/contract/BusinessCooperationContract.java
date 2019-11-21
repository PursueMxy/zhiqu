package com.icarexm.zhiquwang.contract;

public interface BusinessCooperationContract {
    interface Model {
    }

    interface View {
        void UpdateUI(int code,String msg);
        void UpdateUI(int code,String msg,String mobile);
    }

    interface Presenter {
    }
}

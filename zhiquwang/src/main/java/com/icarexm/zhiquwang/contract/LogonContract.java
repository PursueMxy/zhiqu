package com.icarexm.zhiquwang.contract;

public interface LogonContract {
    interface Model {
    }

    interface View {
      void  UpdateUI(int code,String msg);
        void  UpdateUI(int code,String msg,String data);
    }

    interface Presenter {
    }
}

package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.AllianceInfoBean;

public interface MyToJoinContract {
    interface Model {
    }

    interface View {
      void  UpdateUI(int code , String msg, int type, AllianceInfoBean.DataBean data);
    }

    interface Presenter {
    }
}

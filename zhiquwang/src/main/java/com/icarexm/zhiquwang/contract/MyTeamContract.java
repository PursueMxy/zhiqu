package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.MyTeanBean;

public interface MyTeamContract {
    interface Model {
    }

    interface View {
        void  UpdateUI(int code,String msg, MyTeanBean.DataBeanX data);
    }

    interface Presenter {
    }
}

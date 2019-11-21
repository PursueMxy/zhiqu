package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.NearbyStoreBean;

public interface NearbyStoreContract {
    interface Model {
    }

    interface View {
       void UpdateUI(int code, String msg, NearbyStoreBean.DataBeanX data);
    }

    interface Presenter {
    }
}

package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.QueryWagesBean;

public interface QueryWagesContract {
    interface Model {
    }

    interface View {
        void  UpdateWages(QueryWagesBean queryWagesBean,String content);
    }

    interface Presenter {
    }
}

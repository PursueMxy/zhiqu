package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.ChatListBean;

import java.util.List;

public interface MessageListContract {
    interface Model {
    }

    interface View {
        void  UpdateMessageList(int code ,String msg,List<ChatListBean.DataBean> dataBeanList);
    }

    interface Presenter {
    }
}

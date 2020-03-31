package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.bean.ChatLogListBean;
import com.icarexm.zhiquwang.bean.CreatChatBean;

public interface CustomerChatContract {
    interface Model {
    }

    interface View {
        void  updateCreateChat(int code ,String msg, CreatChatBean.DataBean data);

        void UpdateChatLog(int code, String msg, ChatLogListBean.DataBeanX data);
    }

    interface Presenter {
    }
}

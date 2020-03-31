package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MessageListContract;
import com.icarexm.zhiquwang.presenter.MessageListPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MessageListModel implements MessageListContract.Model {
    /**
     * 获取聊天列表
     */
    public void PostphoneGetChatList(MessageListPresenter presenter,String token){
        OkGo.<String>post(RequstUrl.URL.phoneGetChatList)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    presenter.phoneGetChatListBackcall(response.body());
                    }
                });
    }
}

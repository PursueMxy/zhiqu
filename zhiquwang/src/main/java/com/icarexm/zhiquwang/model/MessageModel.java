package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MessageContract;
import com.icarexm.zhiquwang.presenter.MessagePresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MessageModel implements MessageContract.Model {

    /**
     * 获取消息列表
     */
    public void PostMessage(MessagePresenter messagePresenter, String token, String limit, String page){
        OkGo.<String>post(RequstUrl.URL.message)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    messagePresenter.SetMessage(response.body());
                    }
                });
    }
}

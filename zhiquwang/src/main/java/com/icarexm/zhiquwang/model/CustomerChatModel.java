package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.CustomerChatContract;
import com.icarexm.zhiquwang.presenter.ContactUsPresenter;
import com.icarexm.zhiquwang.presenter.CustomerChatPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CustomerChatModel implements CustomerChatContract.Model {

    /**
     * 进入聊天
     */
    public void PostPhoneCreateChat(CustomerChatPresenter presenter, String token, String job_id){
        OkGo.<String>post(RequstUrl.URL.phoneCreateChat)
                .params("token",token)
                .params("job_id",job_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     presenter.phoneCreateChatCallback(response.body());
                    }
                });
    }


    /**
     * 获取聊天记录
     */
    public void PostphoneGetChatRecord(CustomerChatPresenter presenter, String token,String chat_id,int limit,int page){
        OkGo.<String>post(RequstUrl.URL.phoneGetChatRecord)
                .params("token",token)
                .params("chat_id",chat_id)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        presenter.phoneGetChatRecordCallback(response.body());
                    }
                });
    }
}

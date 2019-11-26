package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.MessageBean;
import com.icarexm.zhiquwang.contract.MessageContract;
import com.icarexm.zhiquwang.model.MessageModel;

public class MessagePresenter implements MessageContract.Presenter {
    public MessageContract.View mView;

    public MessageModel messageModel;

    public MessagePresenter(MessageContract.View view){
        this.mView=view;
        messageModel=new MessageModel();
    }

    public void getMessage(String token,String limit,String page){
       messageModel.PostMessage(this,token,limit,page);
    }

    //消息返回
    public void SetMessage(String content){
        MessageBean messageBean = new GsonBuilder().create().fromJson(content, MessageBean.class);
        mView.UpdateUI(messageBean.getCode(),messageBean.getMsg(),messageBean.getData());
    }
}

package com.icarexm.zhiquwang.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.ChatListBean;
import com.icarexm.zhiquwang.contract.MessageListContract;
import com.icarexm.zhiquwang.model.MessageListModel;

import java.util.List;

public class MessageListPresenter implements MessageListContract.Presenter {
    public MessageListContract.View mView;
    public MessageListModel messageListModel;

    public MessageListPresenter(MessageListContract.View view){
        this.mView=view;
        messageListModel=new MessageListModel();
    }

    //获取聊天列表
    public void  phoneGetChatList(String token){
     messageListModel.PostphoneGetChatList(this,token);
    }

    //聊天列表解析
    public void phoneGetChatListBackcall(String content){
        ChatListBean chatListBean = new GsonBuilder().create().fromJson(content, ChatListBean.class);
        if (chatListBean.getCode()==1){
            mView.UpdateMessageList(chatListBean.getCode(),chatListBean.getMsg(),chatListBean.getData());
        }else {
            mView.UpdateMessageList(chatListBean.getCode(),chatListBean.getMsg(),null);
        }
    }
}

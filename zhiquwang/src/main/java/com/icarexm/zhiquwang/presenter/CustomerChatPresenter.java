package com.icarexm.zhiquwang.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.ChatLogListBean;
import com.icarexm.zhiquwang.bean.CreatChatBean;
import com.icarexm.zhiquwang.contract.CustomerChatContract;
import com.icarexm.zhiquwang.model.CustomerChatModel;

public class CustomerChatPresenter implements CustomerChatContract.Presenter {

     public CustomerChatContract.View mView;

     public CustomerChatModel customerChatModel;
     public CustomerChatPresenter(CustomerChatContract.View view){
         this.mView=view;
         this.customerChatModel=new CustomerChatModel();
     }

     //加入聊天
    public void  phoneCreateChat(String token,String job_id){
        customerChatModel.PostPhoneCreateChat(this,token,job_id);
    }

    //返回聊天
    public void phoneCreateChatCallback(String content){
        CreatChatBean creatChatBean = new GsonBuilder().create().fromJson(content, CreatChatBean.class);
        if (creatChatBean.getCode()==1){
            mView.updateCreateChat(creatChatBean.getCode(),creatChatBean.getMsg(),creatChatBean.getData());
        }else {
            mView.updateCreateChat(creatChatBean.getCode(),creatChatBean.getMsg(),null);
        }
    }

    //获取聊天日志
    public void  phoneGetChatRecord(String token,String chat_id,int limit,int page){
          customerChatModel.PostphoneGetChatRecord(this,token,chat_id,limit,page);
    }

    //返回聊天日志
    public void phoneGetChatRecordCallback(String content){
        ChatLogListBean chatLogListBean = new GsonBuilder().create().fromJson(content, ChatLogListBean.class);
        if (chatLogListBean.getCode()==1){
            ChatLogListBean.DataBeanX data = chatLogListBean.getData();
            mView.UpdateChatLog(chatLogListBean.getCode(),chatLogListBean.getMsg(),chatLogListBean.getData());
        }else {
            mView.UpdateChatLog(chatLogListBean.getCode(),chatLogListBean.getMsg(),null);
        }
    }

}

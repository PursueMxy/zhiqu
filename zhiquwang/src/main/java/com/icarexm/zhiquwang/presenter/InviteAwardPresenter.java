package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.InviteAwardContract;
import com.icarexm.zhiquwang.contract.InviteAwardContract;
import com.icarexm.zhiquwang.model.InviteAwardModel;

public class InviteAwardPresenter implements InviteAwardContract.Presenter {
    //获取提现列表
    public InviteAwardContract.View mView;

    public InviteAwardModel InviteAwardModel;


    public InviteAwardPresenter(InviteAwardContract.View view){
        this.mView=view;
        InviteAwardModel=new InviteAwardModel();

    }

    public void GetSeeFund(String token,String type){
        InviteAwardModel.PostSeeFund(this,token,type);
    }

    //数据返回
    public void SetSeeFund(String content,String type){
        SeeFundBean seeFundBean = new GsonBuilder().create().fromJson(content, SeeFundBean.class);
        mView.Update(seeFundBean.getCode(), seeFundBean.getMsg(), seeFundBean.getData());
    }
}

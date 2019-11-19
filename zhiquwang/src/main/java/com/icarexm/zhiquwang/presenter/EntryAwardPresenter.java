package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.EntryAwardContract;
import com.icarexm.zhiquwang.model.EntryAwardModel;

public class EntryAwardPresenter implements EntryAwardContract.Presenter {
    //获取提现列表
    public EntryAwardContract.View mView;

    public EntryAwardModel EntryAwardModel;


    public EntryAwardPresenter(EntryAwardContract.View view){
        this.mView=view;
        EntryAwardModel=new EntryAwardModel();

    }

    public void GetSeeFund(String token,String type){
        EntryAwardModel.PostSeeFund(this,token,type);
    }

    //数据返回
    public void SetSeeFund(String content,String type) {
        SeeFundBean seeFundBean = new GsonBuilder().create().fromJson(content, SeeFundBean.class);
            mView.Update(seeFundBean.getCode(), seeFundBean.getMsg(), seeFundBean.getData());
    }
}

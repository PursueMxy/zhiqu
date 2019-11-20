package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.CommissionContract;
import com.icarexm.zhiquwang.model.CommissionModel;

public class CommissionPresenter implements CommissionContract.Presenter {
    //获取提现列表
    public CommissionContract.View mView;

    public CommissionModel CommissionModel;


    public CommissionPresenter(CommissionContract.View view){
        this.mView=view;
        CommissionModel=new CommissionModel();

    }

    public void GetSeeFund(String token,String type){
        CommissionModel.PostSeeFund(this,token,type);
    }

    //数据返回
    public void SetSeeFund(String content,String type) {
        SeeFundBean seeFundBean = new GsonBuilder().create().fromJson(content, SeeFundBean.class);
        mView.Update(seeFundBean.getCode(), seeFundBean.getMsg(), seeFundBean.getData());
    }
}

package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.WithdrawalDtlContract;
import com.icarexm.zhiquwang.model.WithdrawalDtlModel;

public class WithdrawalDtlPresenter implements WithdrawalDtlContract.Presenter {
    //获取提现列表
    public WithdrawalDtlContract.View mView;

    public WithdrawalDtlModel withdrawalDtlModel;


    public WithdrawalDtlPresenter(WithdrawalDtlContract.View view){
        this.mView=view;
        withdrawalDtlModel=new WithdrawalDtlModel();

    }

    public void GetSeeFund(String token,String type){
        withdrawalDtlModel.PostSeeFund(this,token,type);
    }

    //数据返回
    public void SetSeeFund(String content,String type){
        SeeFundBean seeFundBean = new GsonBuilder().create().fromJson(content, SeeFundBean.class);
         mView.Update(seeFundBean.getCode(), seeFundBean.getMsg(), seeFundBean.getData());
    }

}

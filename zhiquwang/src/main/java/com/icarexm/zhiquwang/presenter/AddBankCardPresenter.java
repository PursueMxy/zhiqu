package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.AddBankCardContract;
import com.icarexm.zhiquwang.model.AddBankCardModel;

public class AddBankCardPresenter implements AddBankCardContract.Presenter {

    public AddBankCardContract.View mView;

    public AddBankCardModel addBankCardModel;

    public AddBankCardPresenter(AddBankCardContract.View view){
        this.mView=view;
        addBankCardModel=new AddBankCardModel();
    }

    public void  GetAddCard(String token,String bank_user,String bank_name,String bank_num){
        addBankCardModel.PosttAddCard(this,token,bank_user,bank_name,bank_num);
    }

    public void  SetAddBank(String content){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(content, PublicResultBean.class);
       mView.UpdateUI(resultBean.getCode(),resultBean.getMsg());
    }
}

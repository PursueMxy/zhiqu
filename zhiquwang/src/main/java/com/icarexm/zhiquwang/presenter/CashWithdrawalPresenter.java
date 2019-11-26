package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.MyBankBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.CashWithdrawalContract;
import com.icarexm.zhiquwang.model.CashWithdrawalModel;

public class CashWithdrawalPresenter implements CashWithdrawalContract.Presenter {

    public CashWithdrawalContract.View mView;
    public CashWithdrawalModel cashWithdrawalModel;

    public CashWithdrawalPresenter(CashWithdrawalContract.View view){
        this.mView=view;
        cashWithdrawalModel=new CashWithdrawalModel();
    }

    public void GetWithdrawal(String token){
       cashWithdrawalModel.PostCashWithdrawa(this,token);
    }

    public void SetWithdrawal(String content){
        MyBankBean myBankBean = new GsonBuilder().create().fromJson(content, MyBankBean.class);
        mView.BankList(myBankBean.getCode(),myBankBean.getMsg(),myBankBean.getData());
    }

    //提现金额
    public void  GetdoWithdrawal(String token,String price,String bank_id){
        cashWithdrawalModel.PostdoWithdrawal(this,token,price,bank_id);
    }

    public void SetdoWithdrawal(String content){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(content, PublicResultBean.class);
        mView.UpdateUI(resultBean.getCode(),resultBean.getMsg());
    }

}

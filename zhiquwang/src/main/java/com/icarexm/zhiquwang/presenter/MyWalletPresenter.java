package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.bean.WalletBean;
import com.icarexm.zhiquwang.contract.MyWalletContract;
import com.icarexm.zhiquwang.model.MyWalletModel;

import java.net.PortUnreachableException;

public class MyWalletPresenter implements MyWalletContract.Presenter {

    public MyWalletContract.View mView;
    public MyWalletModel myWalletModel;

    public MyWalletPresenter(MyWalletContract.View view){
        this.mView=view;
        myWalletModel=new MyWalletModel();
    }

    public void GetMyWallet(String token){
        myWalletModel.PostWallet(this,token);
    }

    public void SetMyWallet(String content){
        WalletBean walletBean = new GsonBuilder().create().fromJson(content, WalletBean.class);
        mView.UpdateUI(walletBean.getCode(),walletBean.getMsg(),walletBean.getData());
    }
}

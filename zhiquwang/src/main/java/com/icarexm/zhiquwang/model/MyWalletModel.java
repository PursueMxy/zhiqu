package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyWalletContract;
import com.icarexm.zhiquwang.presenter.MyWalletPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyWalletModel implements MyWalletContract.Model {

    public void PostWallet(MyWalletPresenter myWalletPresenter,String token){
        OkGo.<String>post(RequstUrl.URL.MyBalance)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    myWalletPresenter.SetMyWallet(response.body());
                    }
                });
    }
}

package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.LoginContract;
import com.icarexm.zhiquwang.presenter.LoginPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LoginModel implements LoginContract.Model {

    public void PostLogin(LoginPresenter loginPresenter,String mobile,String password){
        OkGo.<String>post(RequstUrl.URL.Login)
                .params("mobile",mobile)
                .params("password",password)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                       loginPresenter.SetLogin(response.body());
                    }
                });
    }
}

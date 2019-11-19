package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.ChangePasswordContract;
import com.icarexm.zhiquwang.presenter.ChangePasswordPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ChangePasswordModel implements ChangePasswordContract.Model {

    public void PostChangePassword(ChangePasswordPresenter changePasswordPresenter,String token,String password,String newpassword,String repassword){
        OkGo.<String>post(RequstUrl.URL.EditPass)
                .params("token",token)
                .params("password",password)
                .params("newpassword",newpassword)
                .params("repassword",repassword)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     changePasswordPresenter.SetChangePassworde(response.body());
                    }
                });
    }
}

package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.ResetPasswordContract;
import com.icarexm.zhiquwang.presenter.ResetPasswordPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class ResetPasswordModel implements ResetPasswordContract.Model {

    public void PostSendMsg(ResetPasswordPresenter resetPasswordPresenter,String mobile, String type){
        OkGo.<String>post(RequstUrl.URL.sendMsg)
                .params("mobile",mobile)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    /**
     * 修改密码
     */
    public void PsotFindPass(ResetPasswordPresenter resetPasswordPresenter,String mobile,String code,
                             String password,String repassword){
        OkGo.<String>post(RequstUrl.URL.findPass)
                .params("mobile",mobile)
                .params("code",code)
                .params("password",password)
                .params("repassword",repassword)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        resetPasswordPresenter.SetFindPass(response.body());
                    }
                });
    }
}

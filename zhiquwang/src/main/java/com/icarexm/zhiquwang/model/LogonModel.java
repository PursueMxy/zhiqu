package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.LogonContract;
import com.icarexm.zhiquwang.presenter.LogonPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class LogonModel implements LogonContract.Model {
    public void PostSendMsg(LogonPresenter LogonPresenter, String mobile, String type) {
        OkGo.<String>post(RequstUrl.URL.sendMsg)
                .params("mobile", mobile)
                .params("type", type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }

    /**
     * 注册
     */
    public void PsotRegister(LogonPresenter LogonPresenter, String mobile, String code,
                             String password, String repassword) {
        OkGo.<String>post(RequstUrl.URL.Register)
                .params("mobile", mobile)
                .params("code", code)
                .params("password", password)
                .params("repassword", repassword)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        LogonPresenter.SetRegister(response.body());
                    }
                });
    }

    /**
     * 绑定手机号
     */
    public void postBindMobile(LogonPresenter LogonPresenter,String mobile,String code,String password,String repassword,String openid){
        OkGo.<String>post(RequstUrl.URL.BindMobile)
                .params("mobile",mobile)
                .params("code",code)
                .params("password",password)
                .params("repassword",repassword)
                .params("openid",openid)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                    }
                });
    }
}

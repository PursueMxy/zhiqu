package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.presenter.BaseInformationPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BaseInformationModel implements BaseInformationContract.Model {

    public void PostBaseInfo(BaseInformationPresenter baseInformationPresenter,String token){
        OkGo.<String>post(RequstUrl.URL.BasicsInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     baseInformationPresenter.SetBaseInfo(response.body());
                    }
                });
    }

    //修改个人信息
    public void PostUpdateUser(BaseInformationPresenter baseInformationPresenter,String token,String username,String avatar){
        OkGo.<String>post(RequstUrl.URL.SetUsername)
                .params("token",token)
                .params("username",username)
                .params("avatar",avatar)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    baseInformationPresenter.SetUpdateUser(response.body());
                    }
                });
    }
}

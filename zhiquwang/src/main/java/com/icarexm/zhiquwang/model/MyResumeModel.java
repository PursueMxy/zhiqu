package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyResumeContract;
import com.icarexm.zhiquwang.presenter.MyResumePresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyResumeModel implements MyResumeContract.Model {

    /**
     * 获取个人简历
     */
    public void PostMineInfo(MyResumePresenter myResumePresenter,String token){
        OkGo.<String>post(RequstUrl.URL.MineInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     myResumePresenter.SetMineinfo(response.body());
                    }
                });
    }
}

package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyToJoinContract;
import com.icarexm.zhiquwang.presenter.MyToJoinPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyToJoinModel implements MyToJoinContract.Model {
    public void PostMyTojoin(MyToJoinPresenter myToJoinPresenter,String token, String name, String mobile, String area){
        OkGo.<String>post(RequstUrl.URL.doAlliance)
                .params("token",token)
                .params("name",name)
                .params("mobile",mobile)
                .params("area",area)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     myToJoinPresenter.SetMyToJoin(response.body());
                    }
                });
    }
}

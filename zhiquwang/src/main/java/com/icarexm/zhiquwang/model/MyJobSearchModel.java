package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyJobSearchContract;
import com.icarexm.zhiquwang.presenter.MyJobSearchPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyJobSearchModel implements MyJobSearchContract.Model {

    public void  PostMyJob(MyJobSearchPresenter myJobSearchPresenter,String token,String limit,String page){
        OkGo.<String>post(RequstUrl.URL.MyJob)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                       myJobSearchPresenter.SetMyJob(response.body());
                    }
                });
    }
}

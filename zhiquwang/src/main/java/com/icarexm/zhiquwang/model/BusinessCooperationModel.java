package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.BusinessCooperationContract;
import com.icarexm.zhiquwang.presenter.BusinessCooperationPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class BusinessCooperationModel implements BusinessCooperationContract.Model {

    /**
     * 获取商务合作信息
     */
    public void  PostCooperation(BusinessCooperationPresenter businessCooperation,String token){
        OkGo.<String>post(RequstUrl.URL.Cooperation)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     businessCooperation.SetCooperation(response.body());
                    }
                });
    }

    /**
     * 上传合作需求
     */
    public void PostDoCooperation(BusinessCooperationPresenter businessCooperation,String token,String name,String mobile,String need){
        OkGo.<String>post(RequstUrl.URL.doCooperation)
                .params("token",token)
                .params("name",name)
                .params("mobile",mobile)
                .params("need",need)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     businessCooperation.SetDoCooperation(response.body());
                    }
                });
    }
}

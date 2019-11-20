package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.CertificationContract;
import com.icarexm.zhiquwang.presenter.CertificationPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CertificationModel implements CertificationContract.Model {

    /**
     * 实名认证今日
     */
    public void  PostCertification(CertificationPresenter certificationPresenter,String token){
        OkGo.<String>post(RequstUrl.URL.AutoNym)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      certificationPresenter.SetCertifiaction(response.body());
                    }
                });
    }

     public void PostdoAutoNym(CertificationPresenter certificationPresenter,String token,String real_name,String card_front,String card_reverse,String card_num){
        OkGo.<String>post(RequstUrl.URL.DoAutoNym)
                .params("token",token)
                .params("real_name",real_name)
                .params("card_front",card_front)
                .params("card_reverse",card_reverse)
                .params("card_num",card_num)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                       certificationPresenter.SetdoAutoNym(response.body());
                    }
                });
     }


}

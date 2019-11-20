package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.CertificationBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.CertificationContract;
import com.icarexm.zhiquwang.model.CertificationModel;

public class CertificationPresenter implements CertificationContract.Presenter {

    public CertificationContract.View mView;

    public CertificationModel certificationModel;

    public CertificationPresenter(CertificationContract.View view){
        this.mView=view;
        certificationModel=new CertificationModel();
    }

    public void GetCertification(String token){
        certificationModel.PostCertification(this,token);
    }

    public void SetCertifiaction(String content){
        CertificationBean certificationBean = new GsonBuilder().create().fromJson(content, CertificationBean.class);
        mView.UpdateUI(certificationBean.getCode(),certificationBean.getMsg(),certificationBean.getData());
    }

    //实名资料上传
    public void  GetdoAutoNym(String token,String real_name,String card_front,String card_reverse,String card_num){
          certificationModel.PostdoAutoNym(this,token,real_name,card_front,card_reverse,card_num);
    }

    //上传返回
    public void SetdoAutoNym(String conten){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(conten, PublicResultBean.class);

    }
}

package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.model.BaseInformationModel;

public class BaseInformationPresenter implements BaseInformationContract.Presenter {

    public BaseInformationContract.View mView;
    public BaseInformationModel baseInformationModel;

    public BaseInformationPresenter(BaseInformationContract.View view){
        this.mView=view;
        baseInformationModel=new BaseInformationModel();
    }

    public void  GetBaseInfor(String token){
        baseInformationModel.PostBaseInfo(this,token);
    }

    public  void SetBaseInfo(String content){
        BaseInforBean baseInforBean = new GsonBuilder().create().fromJson(content, BaseInforBean.class);
        BaseInforBean.DataBean data = baseInforBean.getData();
        mView.UpdateUI(baseInforBean.getCode(),baseInforBean.getMsg(), baseInforBean.getData());
    }
}

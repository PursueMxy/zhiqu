package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.BasePayBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.contract.BasePayContract;
import com.icarexm.zhiquwang.model.BaseInformationModel;
import com.icarexm.zhiquwang.model.BasePayModel;

public class BasePayPresenter implements BasePayContract.Presenter {

    public BasePayContract.View mView;

    public BasePayModel basePayModel;

    public BasePayPresenter( BasePayContract.View view){
        this.mView=view;
        basePayModel=new BasePayModel();
    }

    public void GetOvertimeInfo(String token,String type){
        basePayModel.PostOvertimeInfo(this,token,type);
    }

    public void SetOvertimeInfo(String content){
        BasePayBean basePayBean = new GsonBuilder().create().fromJson(content, BasePayBean.class);
        mView.UpdateUI(basePayBean.getCode(),basePayBean.getMsg(),basePayBean.getData());
    }

    //设置基本工资
    public void  GetsetOvertime(String token,String type,String pay,String subsidy){
        basePayModel.PostsetOvertime(this,token,type,pay,subsidy);
    }

    //设置返回
    public void SetOvertime(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        mView.UpdateUI(publicCodeBean.getCode(),publicCodeBean.getMsg());
    }
}

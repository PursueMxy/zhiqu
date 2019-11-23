package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.BusinessCooperationContract;
import com.icarexm.zhiquwang.bean.CooperationBean;
import com.icarexm.zhiquwang.model.BusinessCooperationModel;

public class BusinessCooperationPresenter implements BusinessCooperationContract.Presenter {

    public BusinessCooperationContract.View mView;
    public BusinessCooperationModel businessCooperationModel;

    public BusinessCooperationPresenter(BusinessCooperationContract.View view){
        this.mView=view;
        businessCooperationModel=new BusinessCooperationModel();
    }

    //获取进入页面信息
    public void GetCooperation(String token){
     businessCooperationModel.PostCooperation(this,token);
    }

    //数据解析
    public void SetCooperation(String content){
        CooperationBean cooperationBean = new GsonBuilder().create().fromJson(content, CooperationBean.class);
        mView.UpdateUI(cooperationBean.getCode(),cooperationBean.getMsg(),cooperationBean.getData().getMobile());
    }

    //添加合作需求
    public void GetDoCooperation(String token,String name,String mobile,String need){
       businessCooperationModel.PostDoCooperation(this,token,name,mobile,need);
    }

    //添加需求数据返回
    public void  SetDoCooperation(String content){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(content, PublicResultBean.class);
        mView.UpdateUI(resultBean.getCode(),resultBean.getMsg());
    }
}

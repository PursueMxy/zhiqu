package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.AllianceInfoBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.contract.MyToJoinContract;
import com.icarexm.zhiquwang.model.MyToJoinModel;

public class MyToJoinPresenter implements MyToJoinContract.Presenter {
    public MyToJoinContract.View mView;
    public MyToJoinModel myToJoinModel;

    public MyToJoinPresenter(MyToJoinContract.View view){
        this.mView=view;
        myToJoinModel=new MyToJoinModel();
    }

    //进入页面
    public void GetallianceInfo(String token){
    myToJoinModel.PostAllianceInfo(this,token);
    }

    //进入页面返回
    public void  SetallianceInfo(String content){
        AllianceInfoBean allianceInfoBean = new GsonBuilder().create().fromJson(content, AllianceInfoBean.class);
        mView.UpdateUI(allianceInfoBean.getCode(),allianceInfoBean.getMsg(),1,allianceInfoBean.getData());
    }

     public void GetMyTojoin(String token,String name,String mobile,String area){
      myToJoinModel.PostMyTojoin(this,token,name,mobile,area);
    }

    /**
     * 加入返回
     */
    public void SetMyToJoin(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        mView.UpdateUI(publicCodeBean.getCode(),publicCodeBean.getMsg(),2,null);
    }
}

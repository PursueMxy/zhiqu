package com.icarexm.zhiquwang.presenter;

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
}

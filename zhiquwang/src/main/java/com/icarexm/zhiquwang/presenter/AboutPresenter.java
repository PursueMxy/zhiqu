package com.icarexm.zhiquwang.presenter;

import com.icarexm.zhiquwang.contract.AboutContract;
import com.icarexm.zhiquwang.model.AboutModel;

public class AboutPresenter implements AboutContract.Presenter {

    public AboutContract.View mView;
    public AboutModel aboutModel;

    public AboutPresenter(AboutContract.View view){
        this.mView=view;
        aboutModel=new AboutModel();
    }

}

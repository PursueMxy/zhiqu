package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.ResumeBean;
import com.icarexm.zhiquwang.contract.MyResumeContract;
import com.icarexm.zhiquwang.model.MyResumeModel;

public class MyResumePresenter implements MyResumeContract.Presenter {

    public MyResumeContract.View mView;

    public MyResumeModel myResumeModel;

    public MyResumePresenter(MyResumeContract.View view){
        this.mView=view;
        myResumeModel=new MyResumeModel();
    }

    //获取个人信息
    public void  GetMineInfo(String token){
      myResumeModel.PostMineInfo(this,token);
    }

    public void SetMineinfo(String content){
        ResumeBean resumeBean = new GsonBuilder().create().fromJson(content, ResumeBean.class);
         mView.UpdateUI(resumeBean.getCode(),resumeBean.getMsg(),resumeBean.getData());
    }
}

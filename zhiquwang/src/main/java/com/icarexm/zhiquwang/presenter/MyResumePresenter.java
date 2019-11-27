package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.bean.ResumeBean;
import com.icarexm.zhiquwang.contract.MyResumeContract;
import com.icarexm.zhiquwang.model.MyResumeModel;
import com.lzy.okgo.OkGo;

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

    //保存个人信息
    public void GetAddResume(String token,String avatar,String real_name,String sex,String birth,String city,
                            String education,String personal_introduce,String experience){
            myResumeModel.PostaddResume(this,token,avatar,real_name,sex,birth,city,education,
                    personal_introduce,experience);
    }

    //保存个人信息返回
    public void SetAddResume(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        mView.UpdateUI(publicCodeBean.getCode(),publicCodeBean.getMsg());

    }

}

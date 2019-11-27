package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.LogonContract;
import com.icarexm.zhiquwang.contract.LogonContract;
import com.icarexm.zhiquwang.model.LogonModel;

public class LogonPresenter implements LogonContract.Presenter {
    public LogonContract.View mView;
    public LogonModel LogonModel;

    public LogonPresenter(LogonContract.View view)
    {
        this.mView=view;
        LogonModel=new LogonModel();
    }

    //获去验证码
    public void GetSendMsg(String mobile,String type){
        LogonModel.PostSendMsg(this,mobile,type);
    }

    //注册
    public void GetRegister(String mobile,String code,String password,String repassword){
        LogonModel.PsotRegister(this,mobile,code,password,repassword);
    }

    //绑定手机号
    public void GetBindMobile(String mobile,String code,String password,String repassword,String openid){
       LogonModel.postBindMobile(this,mobile,code,password,repassword,openid);
    }

    //注册数据返回
    public void SetRegister(String content){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(content, PublicResultBean.class);
        mView.UpdateUI(resultBean.getCode(),resultBean.getMsg());
    }
}

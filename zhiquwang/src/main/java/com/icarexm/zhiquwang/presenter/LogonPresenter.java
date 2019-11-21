package com.icarexm.zhiquwang.presenter;

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

    //找回密码
    public void GetFindPass(String mobile,String code,String password,String repassword){
        LogonModel.PsotFindPass(this,mobile,code,password,repassword);
    }

    //数据返回
    public void SetFindPass(String content){

    }
}

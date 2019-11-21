package com.icarexm.zhiquwang.presenter;

import com.icarexm.zhiquwang.contract.ResetPasswordContract;
import com.icarexm.zhiquwang.model.ResetPasswordModel;

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {

    public ResetPasswordContract.View mView;
    public ResetPasswordModel resetPasswordModel;

    public ResetPasswordPresenter(ResetPasswordContract.View view)
    {
        this.mView=view;
        resetPasswordModel=new ResetPasswordModel();
    }

    //获去验证码
    public void GetSendMsg(String mobile,String type){
        resetPasswordModel.PostSendMsg(this,mobile,type);
    }

    //找回密码
    public void GetFindPass(String mobile,String code,String password,String repassword){
        resetPasswordModel.PsotFindPass(this,mobile,code,password,repassword);
    }

    //数据返回
    public void SetFindPass(String content){

    }

}

package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.LoginBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.bean.WechatBean;
import com.icarexm.zhiquwang.contract.LoginContract;
import com.icarexm.zhiquwang.model.LoginModel;

public class LoginPresenter implements LoginContract.Presenter {

    public LoginContract.View mView;
    public LoginModel loginModel;

    public LoginPresenter(LoginContract.View view){
        this.mView=view;
        loginModel=new LoginModel();
    }

    //登录
    public void GetLogin(String mobile,String password){
        loginModel.PostLogin(this,mobile,password);
    }

    //登录数据解析
    public void SetLogin(String content){
       LoginBean result = new GsonBuilder().create().fromJson(content, LoginBean.class);
        mView.Update(result.getCode(),result.getMsg(),result.getData(),result.getOther());
    }

    //微信登录
    public void GetWechatLogin(String code){
     loginModel.PostWechatLogin(this,code);
    }

    //微信登陆返回
    public void SetWechatLogin(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        if (publicCodeBean.getCode()==1){
            LoginBean result = new GsonBuilder().create().fromJson(content, LoginBean.class);
            mView.Update(result.getCode(),result.getMsg(),result.getData(),result.getOther());
        }else if (publicCodeBean.getCode()==10004){
            WechatBean wechatBean = new GsonBuilder().create().fromJson(content, WechatBean.class);
            mView.WechatLoginUpdateUI(wechatBean.getCode(),wechatBean.getMsg(), wechatBean.getData().getOpenid());
        }else {
            mView.WechatLoginUpdateUI(publicCodeBean.getCode(), publicCodeBean.getMsg(), "");
        }
    }
}

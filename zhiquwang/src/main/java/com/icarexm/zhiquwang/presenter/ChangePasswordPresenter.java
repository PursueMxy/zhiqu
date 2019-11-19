package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.ChangePasswordContract;
import com.icarexm.zhiquwang.model.ChangePasswordModel;

public class ChangePasswordPresenter implements ChangePasswordContract.Presenter {

    public ChangePasswordContract.View mView;
    public ChangePasswordModel changePasswordModel;

    public ChangePasswordPresenter(ChangePasswordContract.View view){
        this.mView=view;
        changePasswordModel=new ChangePasswordModel();
    }

    //修改密码
    public void GetChangePassword(String token,String password,String newpassword,String repassword){
      changePasswordModel.PostChangePassword(this,token,password,newpassword,repassword);
    }

    //修改返回
    public void SetChangePassworde(String content){
        PublicResultBean resultBean = new GsonBuilder().create().fromJson(content, PublicResultBean.class);
        mView.UpdateUI(resultBean.getCode(),resultBean.getMsg());
    }
}

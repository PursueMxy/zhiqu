package com.icarexm.zhiquwang.contract;

import com.icarexm.zhiquwang.presenter.LoginPresenter;

public interface LoginContract {
    interface Model {
        void PostLogin(LoginPresenter loginPresenter, String mobile, String password);
    }

    interface View {
        void Update(int code,String msg,String data);
        void WechatLoginUpdateUI(int code,String msg);
    }

    interface Presenter {
        void GetLogin(String mobile,String password);
        void SetLogin(String content);
    }
}

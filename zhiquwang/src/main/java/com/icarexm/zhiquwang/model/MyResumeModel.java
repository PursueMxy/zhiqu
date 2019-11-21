package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.MyResumeContract;
import com.icarexm.zhiquwang.presenter.MyResumePresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class MyResumeModel implements MyResumeContract.Model {

    /**
     * 获取个人简历
     */
    public void PostMineInfo(MyResumePresenter myResumePresenter,String token){
        OkGo.<String>post(RequstUrl.URL.MineInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     myResumePresenter.SetMineinfo(response.body());
                    }
                });
    }

    /**
     * 保存个人简历
     */
    public void PostaddResume(MyResumePresenter myResumePresenter,String token,String avatar,String real_name,String sex,String birth,String city,
                              String education,String personal_introduce,String experience){
          OkGo.<String>post(RequstUrl.URL.AddResume)
                  .params("token",token)
                  .params("avatar",avatar)
                  .params("real_name",real_name)
                  .params("sex",sex)
                  .params("birth",birth)
                  .params("city",city)
                  .params("education",education)
                  .params("personal_introduce",personal_introduce)
                  .params("experience",experience)
                  .execute(new StringCallback() {
                      @Override
                      public void onSuccess(Response<String> response) {
                       myResumePresenter.SetAddResume(response.body());
                      }
                  });


    }

}

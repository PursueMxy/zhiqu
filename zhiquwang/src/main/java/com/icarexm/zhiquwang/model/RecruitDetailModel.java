package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.RecruitDetailContract;
import com.icarexm.zhiquwang.presenter.RecruitDetailPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class RecruitDetailModel implements RecruitDetailContract.Model {

    /**
     * 获取招聘详情
     */
    public void PostJobDetail(RecruitDetailPresenter recruitDetailPresenter,String token,String job_id){
        OkGo.<String>post(RequstUrl.URL.JobDetail)
                .params("token",token)
                .params("job_id",job_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        recruitDetailPresenter.SetJobDetail(response.body());

                    }
                });
    }

}

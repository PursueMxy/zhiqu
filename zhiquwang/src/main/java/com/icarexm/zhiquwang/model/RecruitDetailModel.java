package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.RecruitDetailContract;
import com.icarexm.zhiquwang.presenter.FamousRecruitmentPresenter;
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

    /**
     * 获取推荐列表
     */
    public void PostHomeData(RecruitDetailPresenter recruitDetailPresenter, String token, String limit, String page, String zone_id, String area_id, String salary_id, String age_id,
                             String vocation_id, String environment_id, String job_id){
        OkGo.<String>post(RequstUrl.URL.HomeData)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .params("zone_id",zone_id)
                .params("area_id",age_id)
                .params("salary_id",salary_id)
                .params("age_id",age_id)
                .params("vocation_id",vocation_id)
                .params("environment_id",environment_id)
                .params("job_id",job_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        recruitDetailPresenter.SetHomeData(response.body());
                    }
                });
    }

}

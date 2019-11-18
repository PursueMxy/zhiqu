package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.FamousRecruitmentContract;
import com.icarexm.zhiquwang.presenter.FamousRecruitmentPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class FamousRecruitmentModel implements FamousRecruitmentContract.Model {

    public void PostHomeData(FamousRecruitmentPresenter famousRecruitmentPresenter,String token,String limit,String page,String zone_id,String area_id,String salary_id,String age_id,
                             String vocation_id,String environment_id,String job_id){
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
                     famousRecruitmentPresenter.SetHomeData(response.body());
                    }
                });
    }
}

package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.contract.FamousRecruitmentContract;
import com.icarexm.zhiquwang.model.FamousRecruitmentModel;

public class FamousRecruitmentPresenter implements FamousRecruitmentContract.Presenter {
    public FamousRecruitmentContract.View mView;
    public FamousRecruitmentModel famousRecruitmentModel;

    public FamousRecruitmentPresenter(FamousRecruitmentContract.View view){
        this.mView=view;
        famousRecruitmentModel=new FamousRecruitmentModel();
    }

    public void GetHomeData(String token,String limit,String page,String zone_id,String area_id,String salary_id,String age_id,
                            String vocation_id,String environment_id,String job_id){
        famousRecruitmentModel.PostHomeData(this,token,limit,page,zone_id,area_id,salary_id,age_id,vocation_id,
                environment_id,job_id);

    }

    public void SetHomeData(String content){
        HomeDataBean homeDataBean = new GsonBuilder().create().fromJson(content, HomeDataBean.class);
         HomeDataBean.DataBeanX data = homeDataBean.getData();
         mView.UpdateUI(homeDataBean.getCode(),homeDataBean.getMsg(),data);
    }
}

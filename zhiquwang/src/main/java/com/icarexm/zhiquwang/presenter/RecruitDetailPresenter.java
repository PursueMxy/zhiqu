package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.bean.JobDetailBean;
import com.icarexm.zhiquwang.contract.RecruitDetailContract;
import com.icarexm.zhiquwang.model.RecruitDetailModel;

public class RecruitDetailPresenter implements RecruitDetailContract.Presenter {
    public RecruitDetailContract.View mView;
    public RecruitDetailModel recruitDetailModel;

    public RecruitDetailPresenter(RecruitDetailContract.View view){
        this.mView=view;
        recruitDetailModel=new RecruitDetailModel();
    }

    //获取招聘详情
    public void GetJobDetail(String token,String job_id){
        recruitDetailModel.PostJobDetail(this,token,job_id);
    }

    //解析招聘详情
    public void  SetJobDetail(String content){
        JobDetailBean jobDetailBean = new GsonBuilder().create().fromJson(content, JobDetailBean.class);
        mView.UpdateUI(jobDetailBean.getCode(),jobDetailBean.getMsg(),jobDetailBean.getData());

    }

    //获取推荐列表
    public void GetHomeData(String token,String limit,String page,String zone_id,String area_id,String salary_id,String age_id,
                            String vocation_id,String environment_id,String job_id){
        recruitDetailModel.PostHomeData(this,token,limit,page,zone_id,area_id,salary_id,age_id,vocation_id,
                environment_id,job_id);

    }

    public void SetHomeData(String content){
        HomeDataBean homeDataBean = new GsonBuilder().create().fromJson(content, HomeDataBean.class);
        HomeDataBean.DataBeanX data = homeDataBean.getData();
        mView.UpdateUI(homeDataBean.getCode(),homeDataBean.getMsg(),data);
    }


}

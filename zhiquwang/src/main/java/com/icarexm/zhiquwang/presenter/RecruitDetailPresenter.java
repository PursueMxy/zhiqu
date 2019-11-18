package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
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
}

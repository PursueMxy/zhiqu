package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.JobSearchBean;
import com.icarexm.zhiquwang.contract.MyJobSearchContract;
import com.icarexm.zhiquwang.model.MyJobSearchModel;

public class MyJobSearchPresenter implements MyJobSearchContract.Presenter {

    public MyJobSearchContract.View mView;

    public MyJobSearchModel myJobSearchModel;

    public MyJobSearchPresenter(MyJobSearchContract.View view){
        this.mView=view;
        myJobSearchModel=new MyJobSearchModel();
    }

    public void GetMyJob(String token,String limit,String page){
       myJobSearchModel.PostMyJob(this,token,limit,page);
    }

    public void SetMyJob(String contnet){
        JobSearchBean jobSearchBean = new GsonBuilder().create().fromJson(contnet, JobSearchBean.class);
        mView.UpdateUI(jobSearchBean.getCode(),jobSearchBean.getMsg(),jobSearchBean.getData());
    }
}

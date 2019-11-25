package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.StatisticsBean;
import com.icarexm.zhiquwang.contract.OvertimeStatisticsContract;
import com.icarexm.zhiquwang.model.OvertimeStatisticsModel;

public class OvertimeStatisticsPresenter implements OvertimeStatisticsContract.Presenter {
    public OvertimeStatisticsContract.View mView;

    public OvertimeStatisticsModel overtimeStatisticsModel;

    public OvertimeStatisticsPresenter(OvertimeStatisticsContract.View view){
        this.mView=view;
        overtimeStatisticsModel=new OvertimeStatisticsModel();
    }

    //获取加班统计详情
    public void  GetRecords(String token,String type){
      overtimeStatisticsModel.PostRecords(this,token,type);
    }

    //加班数据返回
    public void SetRecords(String content){
        StatisticsBean statisticsBean = new GsonBuilder().create().fromJson(content, StatisticsBean.class);
        StatisticsBean.DataBean data = statisticsBean.getData();

    }
}

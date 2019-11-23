package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.RepairInfoBean;
import com.icarexm.zhiquwang.contract.PunchCardRecordContract;
import com.icarexm.zhiquwang.model.PunchCardRecordModel;

public class PunchCardRecordPresenter implements PunchCardRecordContract.Presenter {

    public PunchCardRecordContract.View mView;

    public PunchCardRecordModel punchCardRecordModel;

    public PunchCardRecordPresenter(PunchCardRecordContract.View view){
        this.mView=view;
        this.punchCardRecordModel=new PunchCardRecordModel();
    }

    //获取打卡记录
    public void  GetRepairInfo(String token){
      punchCardRecordModel.PsotRepairInfo(this,token);
    }

    //解析打卡记录
    public void SetRepairInfo(String content){
        RepairInfoBean repairInfoBean = new GsonBuilder().create().fromJson(content, RepairInfoBean.class);
        mView.UpdateUI(repairInfoBean.getCode(),repairInfoBean.getMsg(),repairInfoBean.getData());
    }

}

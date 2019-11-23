package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.bean.RepairInfoBean;
import com.icarexm.zhiquwang.contract.AttendanceCardContract;
import com.icarexm.zhiquwang.contract.AttendanceCardContract;
import com.icarexm.zhiquwang.model.AttendanceCardModel;

public class AttendanceCardPresenter implements AttendanceCardContract.Presenter {
    public AttendanceCardContract.View mView;

    public AttendanceCardModel AttendanceCardModel;

    public AttendanceCardPresenter(AttendanceCardContract.View view){
        this.mView=view;
        this.AttendanceCardModel=new AttendanceCardModel();
    }

    //获取打卡记录
    public void  GetRepairInfo(String token){
        AttendanceCardModel.PsotRepairInfo(this,token);
    }

    //解析打卡记录
    public void SetRepairInfo(String content){
        RepairInfoBean repairInfoBean = new GsonBuilder().create().fromJson(content, RepairInfoBean.class);
        mView.UpdateUI(repairInfoBean.getCode(),repairInfoBean.getMsg(),repairInfoBean.getData());
    }

    //申请补卡
    public void GetReissue(String token,String day_shift,String start_time,String end_time,String witch_day){
        AttendanceCardModel.PostReissue(this,token,day_shift,start_time,end_time,witch_day);
    }

    //申请打卡记录返回
    public void  SetReissue(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        mView.UpdateUI(publicCodeBean.getCode(),publicCodeBean.getMsg());
    }
}

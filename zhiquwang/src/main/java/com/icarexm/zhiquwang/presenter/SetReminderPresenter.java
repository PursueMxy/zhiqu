package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.ClockRemindedInfoBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.contract.SetReminderContract;
import com.icarexm.zhiquwang.model.SetReminderModel;

public class SetReminderPresenter implements SetReminderContract.Presenter {

    public SetReminderContract.View mView;

    public SetReminderModel setReminderModel;

    public SetReminderPresenter(SetReminderContract.View view){
        this.mView=view;
        setReminderModel=new SetReminderModel();
    }

    /**获取提现消息
     *
     */
    public void GetclockRemindedInfo(String token){
          setReminderModel.GetclockRemindedInfo(this,token);
    }

    /**
     * 提醒返回
     */
    public void SetclockRemindedInfo(String content){
        try {
            ClockRemindedInfoBean clockRemindedInfoBean = new GsonBuilder().create().fromJson(content, ClockRemindedInfoBean.class);
        mView.UpdateUI(clockRemindedInfoBean.getCode(),clockRemindedInfoBean.getMsg(),clockRemindedInfoBean.getData());
        }catch (Exception e){
            mView.UpdateUI(0,"数据为空",null);
        }
    }


    /** 设置提醒 */
    public void GetClockReminded(String token,String start_time,String end_time,String 	repetition){
        setReminderModel.PsotClockReminded(this,token,start_time,end_time,repetition);
    }

    /**
     * 提醒返回
     */
    public void SetClockReminded(String content){
        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(content, PublicCodeBean.class);
        mView.UpdateUI(publicCodeBean.getCode(),publicCodeBean.getMsg());
    }
}

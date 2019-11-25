package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.SetReminderContract;
import com.icarexm.zhiquwang.presenter.SetReminderPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class SetReminderModel implements SetReminderContract.Model {
    /**
     * 获取提醒信息
     */
    public void  GetclockRemindedInfo(SetReminderPresenter setReminderPresenter,String token){
        OkGo.<String>post(RequstUrl.URL.ClockRemindedInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        setReminderPresenter.SetclockRemindedInfo(response.body());
                    }
                });
    }

    /**
     * 提醒设置
     */
    public void PsotClockReminded(SetReminderPresenter setReminderPresenter,String token, String start_time, String end_time, String repetition){
        OkGo.<String>post(RequstUrl.URL.ClockReminded)
                .params("token",token)
                .params("start_time",start_time)
                .params("end_time",end_time)
                .params("repetition",repetition)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    setReminderPresenter.SetClockReminded(response.body());
                    }
                });
    }
}

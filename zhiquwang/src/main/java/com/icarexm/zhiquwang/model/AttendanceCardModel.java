package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.AttendanceCardContract;
import com.icarexm.zhiquwang.presenter.AttendanceCardPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class AttendanceCardModel implements AttendanceCardContract.Model {

    /**
     * 获取打卡记录
     */
    public void PsotRepairInfo(AttendanceCardPresenter AttendanceCardPresenter, String token){
        OkGo.<String>post(RequstUrl.URL.RepairInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AttendanceCardPresenter.SetRepairInfo(response.body());
                    }
                });
    }

    /**
     * 发送补卡请求
     */
    public void PostReissue(AttendanceCardPresenter ACardPresenter, String token,String day_shift,String start_time,String end_time,String witch_day){
        OkGo.<String>post(RequstUrl.URL.Reissue)
                .params("token",token)
                .params("day_shift",day_shift)
                .params("start_time",start_time)
                .params("end_time",end_time)
                .params("witch_day",witch_day)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     ACardPresenter.SetReissue(response.body());
                    }
                });
    }
}

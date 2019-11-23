package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.PunchCardRecordContract;
import com.icarexm.zhiquwang.presenter.PunchCardRecordPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class PunchCardRecordModel implements PunchCardRecordContract.Model {

    /**
     * 获取打卡记录
     */
    public void PsotRepairInfo(PunchCardRecordPresenter punchCardRecordPresenter, String token){
        OkGo.<String>post(RequstUrl.URL.RepairInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        punchCardRecordPresenter.SetRepairInfo(response.body());
                    }
                });
    }

}

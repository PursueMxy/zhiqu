package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.OvertimeStatisticsContract;
import com.icarexm.zhiquwang.presenter.OvertimeStatisticsPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class OvertimeStatisticsModel implements OvertimeStatisticsContract.Model {
    /**
     * 获取加班详情
     */
    public void  PostRecords(OvertimeStatisticsPresenter overtimeStatisticsPresenter,String token,String type){
        OkGo.<String>post(RequstUrl.URL.Records)
                .params("token",token)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    overtimeStatisticsPresenter.SetRecords(response.body());
                    }
                });
    }
}

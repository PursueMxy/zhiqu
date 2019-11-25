package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.OvertimeApprovalContract;
import com.icarexm.zhiquwang.presenter.OvertimeApprovalPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class OvertimeApprovalModel implements OvertimeApprovalContract.Model {
    /**
     * 加班页面
     */
    public void PostOertimeRecords(OvertimeApprovalPresenter approvalPresenter,String token,String type){
        OkGo.<String>post(RequstUrl.URL.overtimeRecords)
                .params("token",token)
                .params("type",type)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        approvalPresenter.SetOertimeRecords(response.body());
                    }
                });
    }

    /**
     * 提交加班信息
     */
    public void PostdoRecords(OvertimeApprovalPresenter approvalPresenter,String token,String type,String classes_id,
                              String festival_id,String hours,String day){
                        OkGo.<String>post(RequstUrl.URL.doRecords)
                                .params("token",token)
                                .params("type",type)
                                .params("classes_id",classes_id)
                                .params("festival_id",festival_id)
                                .params("hours",hours)
                                .params("day",day)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {

                                    }
                                });
    }

}

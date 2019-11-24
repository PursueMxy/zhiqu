package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.OvertimeApproverBean;
import com.icarexm.zhiquwang.contract.OvertimeApprovalContract;
import com.icarexm.zhiquwang.model.OvertimeApprovalModel;

public class OvertimeApprovalPresenter implements OvertimeApprovalContract.Presenter {

    public OvertimeApprovalContract.View mView;
    public OvertimeApprovalModel overtimeApprovalModel;

    public OvertimeApprovalPresenter(OvertimeApprovalContract.View view){
        this.mView=view;
        overtimeApprovalModel=new OvertimeApprovalModel();
    }

    public void GetOertimeRecords(String token,String type){
            overtimeApprovalModel.PostOertimeRecords(this,token,type);
    }

    public void SetOertimeRecords(String content){
        OvertimeApproverBean overtimeApproverBean = new GsonBuilder().create().fromJson(content, OvertimeApproverBean.class);
        mView.UpdateUI(overtimeApproverBean.getCode(),overtimeApproverBean.getMsg(),overtimeApproverBean.getData());
    }

    public void GetdoRecords(String token,String type,String classes_id,String festival_id,String hours){
       overtimeApprovalModel.PostdoRecords(this,token,type,classes_id,festival_id,hours);
    }
}

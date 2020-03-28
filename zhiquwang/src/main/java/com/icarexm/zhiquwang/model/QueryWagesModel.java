package com.icarexm.zhiquwang.model;

import android.util.Log;

import com.icarexm.zhiquwang.contract.QueryWagesContract;
import com.icarexm.zhiquwang.presenter.QueryWagesPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class QueryWagesModel implements QueryWagesContract.Model {

    /**
     * 查询工资
     */
    public void PostHonorariumSearch(QueryWagesPresenter presenter,String token, String name, String card, String num, String month){
        OkGo.<String>post(RequstUrl.URL.honorariumSearch)
                .params("token",token)
                .params("name",name)
                .params("card",card)
                .params("num",num)
                .params("month",month)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        presenter.HonorariumBackcall(response.body());
                    }
                });
    }

}

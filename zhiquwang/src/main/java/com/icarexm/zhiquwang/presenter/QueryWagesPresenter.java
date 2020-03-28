package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.QueryWagesBean;
import com.icarexm.zhiquwang.contract.QueryWagesContract;
import com.icarexm.zhiquwang.model.QueryWagesModel;

public class QueryWagesPresenter implements QueryWagesContract.Presenter {
    public QueryWagesContract.View mView;
    public QueryWagesModel queryWagesModel;

    public QueryWagesPresenter(QueryWagesContract.View view){
        this.mView=view;
        this.queryWagesModel=new QueryWagesModel();
    }

   //查询工资
    public void honorariumSearch(String token, String name, String card, String num, String month){
        queryWagesModel.PostHonorariumSearch(this,token,name,card,num,month);
    }

    //工资列表返回
    public void HonorariumBackcall(String content){
        QueryWagesBean queryWagesBean = new GsonBuilder().create().fromJson(content, QueryWagesBean.class);
        mView.UpdateWages(queryWagesBean,content);
    }

}

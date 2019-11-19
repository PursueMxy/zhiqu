package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class CommissionAdapter extends HelperRecyclerViewAdapter<SeeFundBean.DataBeanX.ListBean.DataBean> {
    public Context context;

    public CommissionAdapter(Context context) {
        super(context, R.layout.list_commission);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,SeeFundBean.DataBeanX.ListBean.DataBean item) {
        TextView tv_content= viewHolder.getView(R.id.list_commission_tv_content);
        TextView tv_time= viewHolder.getView(R.id.list_commission_tv_time);
        TextView tv_money= viewHolder.getView(R.id.list_commission_tv_money);
        tv_content.setText(item.getContent());
        tv_time.setText(item.getCreate_time());
        tv_money.setText(item.getPrice()+"元");
    }
}

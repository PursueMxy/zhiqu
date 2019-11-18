package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class HomeFmAdapter extends HelperRecyclerViewAdapter<HomeDataBean.DataBeanX.DataBean> {
    public Context context;

    public HomeFmAdapter(Context context) {
        super(context, R.layout.list_home_fm);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,HomeDataBean.DataBeanX.DataBean item) {
        List<HomeDataBean.DataBeanX.DataBean.LabelArrBean> label_arr = item.getLabel_arr();
        LabelsView labels= viewHolder.getView(R.id.labels);
        //LabelsView可以设置任何类型的数据，而不仅仅是String。
        ArrayList<String> label = new ArrayList<>();
        for (int a=0;a<label_arr.size();a++){
            label.add(label_arr.get(a).getLabel_name());
        }
        labels.setLabels(label);
        TextView tv_position= viewHolder.getView(R.id.list_home_fm_tv_position);
        tv_position.setText(item.getJob_name());
        TextView tv_salary = viewHolder.getView(R.id.list_home_fm_tv_salary);
        tv_salary.setText(item.getSalary()+"/月("+item.getSalary_hour()+"/小时)");
        TextView tv_age= viewHolder.getView(R.id.list_home_fm_tv_age);
        tv_age.setText(item.getAge());
        TextView tv_address = viewHolder.getView(R.id.list_home_fm_tv_address);
        tv_address.setText(item.getAddress());
        TextView tv_label_price= viewHolder.getView(R.id.list_home_fm_tv_label_price);
        tv_label_price.setText(item.getLabel_price());
    }
}

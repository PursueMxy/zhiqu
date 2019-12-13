package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.bean.JobSearchBean;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MyJobSearchAdapter extends HelperRecyclerViewAdapter<JobSearchBean.DataBeanX.DataBean> {
    public Context context;

    public MyJobSearchAdapter(Context context) {
        super(context, R.layout.list_home_fm);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,JobSearchBean.DataBeanX.DataBean item) {
        LabelsView labels= viewHolder.getView(R.id.labels);
        List<JobSearchBean.DataBeanX.DataBean.LabelArrBean> label_arr = item.getLabel_arr();
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
        viewHolder.getView(R.id.list_home_fm_tv_label_price).setVisibility(View.GONE);
    }
}

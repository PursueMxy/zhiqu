package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;

public class MyJobSearchAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public MyJobSearchAdapter(Context context) {
        super(context, R.layout.list_home_fm);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,String item) {
        LabelsView labels = viewHolder.getView(R.id.labels);
        viewHolder.getView(R.id.list_home_fm_rl).setVisibility(View.GONE);
        ArrayList<String> label = new ArrayList<>();
        label.add("Android");
        label.add("IOS");
        label.add("前端");
        label.add("后台");
        label.add("微信开发");
        labels.setLabels(label); //直接设置一个字符串数组就可以了。
    }
}

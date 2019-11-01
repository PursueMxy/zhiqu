package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;

public class MyTeamAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public MyTeamAdapter(Context context) {
        super(context, R.layout.list_my_team);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,String item) {
    }
}

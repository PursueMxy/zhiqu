package com.icarexm.zhiquwang.adapter;

import android.content.Context;

import com.icarexm.zhiquwang.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class CommissionAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public CommissionAdapter(Context context) {
        super(context, R.layout.list_commission);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,String item) {
    }
}

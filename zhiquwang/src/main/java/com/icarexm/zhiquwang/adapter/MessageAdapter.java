package com.icarexm.zhiquwang.adapter;

import android.content.Context;

import com.icarexm.zhiquwang.R;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MessageAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public MessageAdapter(Context context) {
        super(context, R.layout.list_meaasge);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,String item) {
    }
}

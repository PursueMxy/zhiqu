package com.icarexm.zhiquwang.adapter;

import android.content.Context;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;

public class NearbyStoreAdapter extends HelperRecyclerViewAdapter<String> {
    public Context context;

    public NearbyStoreAdapter(Context context) {
        super(context, R.layout.list_nearbystore);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,String item) {
    }
}

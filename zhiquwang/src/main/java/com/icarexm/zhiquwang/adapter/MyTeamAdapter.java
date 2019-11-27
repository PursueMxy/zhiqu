package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.MyTeanBean;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;

public class MyTeamAdapter extends HelperRecyclerViewAdapter<MyTeanBean.DataBeanX.DataBean> {
    public Context context;

    public MyTeamAdapter(Context context) {
        super(context, R.layout.list_my_team);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,MyTeanBean.DataBeanX.DataBean item) {
        TextView tv_name = viewHolder.getView(R.id.list_my_team_tv_name);
        CircleImageView img_avatar = viewHolder.getView(R.id.list_my_team_img_avatar);
        Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).into(img_avatar);
        tv_name.setText(item.getUser_name());

    }
}

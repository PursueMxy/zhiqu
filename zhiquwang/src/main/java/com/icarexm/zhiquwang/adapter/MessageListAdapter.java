package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ChatListBean;
import com.icarexm.zhiquwang.bean.MessageBean;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MessageListAdapter extends HelperRecyclerViewAdapter<ChatListBean.DataBean> {
    public Context context;

    public MessageListAdapter(Context context) {
        super(context, R.layout.list_message_item);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,ChatListBean.DataBean item) {
       ImageView  img_avatar = viewHolder.getView(R.id.list_message_item_img);
       TextView tv_service_name= viewHolder.getView(R.id.list_message_item_tv_service_name);
       TextView tv_job_name = viewHolder.getView(R.id.list_message_item_tv_job_name);
       TextView tv_content = viewHolder.getView(R.id.list_message_item_tv_content);
       TextView tv_date = viewHolder.getView(R.id.list_message_item_tv_date);
       TextView tv_unread= viewHolder.getView(R.id.list_message_tv_unread);
       TextView tv_enterprise_name= viewHolder.getView(R.id.list_message_item_tv_enterprise_name);
        Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).circleCrop().into(img_avatar);
        tv_service_name.setText(item.getNickname());
        tv_enterprise_name.setText(item.getEnterprise_name());
        tv_job_name.setText("·"+item.getJob_name());
        try {
            if (item.getChat_record().contains("/uploads/")){
                tv_content.setText("[图片]");
            }else {
                tv_content.setText(item.getChat_record());
            }
        }catch (Exception e){}
        tv_date.setText(DateUtils.timeStamp2Time2(item.getTime()+""));
        if (item.getUnread_num()>0){
            tv_unread.setVisibility(View.VISIBLE);
            tv_unread.setText(item.getUnread_num()+"");
        }else {
            tv_unread.setVisibility(View.GONE);
        }

    }
}

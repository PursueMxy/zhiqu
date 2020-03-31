package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ChatMessageBean;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class CustomerChatAdapter extends HelperRecyclerViewAdapter<ChatMessageBean.NameValuePairsBean> {
    public Context context;

    public CustomerChatAdapter(Context context) {
        super(context, R.layout.list_customer_chat_item);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,ChatMessageBean.NameValuePairsBean item) {
       RelativeLayout rl_left = viewHolder.getView(R.id.list_customer_chat_rl_left);
       RelativeLayout rl_right = viewHolder.getView(R.id.list_customer_chat_rl_right);
       ImageView img_left = viewHolder.getView(R.id.list_customer_chat_img_left);
       ImageView img_right = viewHolder.getView(R.id.list_customer_chat_img_right);
       TextView left_tv_content= viewHolder.getView(R.id.list_customer_chat_left_tv_content);
       TextView right_tv_content = viewHolder.getView(R.id.list_customer_chat_right_tv_content);
      ImageView left_img_one = viewHolder.getView(R.id.list_customer_chat_left_img_one);
        ImageView right_img_one = viewHolder.getView(R.id.list_customer_chat_right_img_one);
        if (item.getSide()==2){
           rl_left.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).circleCrop().into(img_left);
            if (item.getType()==1) {
                left_img_one.setVisibility(View.GONE);
                left_tv_content.setVisibility(View.VISIBLE);
                left_tv_content.setText(item.getContent());
            }else {
                left_img_one.setVisibility(View.VISIBLE);
                left_tv_content.setVisibility(View.GONE);
                Glide.with(mContext).load(RequstUrl.URL.HOST+item.getContent()).into(left_img_one);
            }
       }else {
           rl_right.setVisibility(View.VISIBLE);
            Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).circleCrop().into(img_right);
            if (item.getType()==1) {
                right_img_one.setVisibility(View.GONE);
                right_tv_content.setVisibility(View.VISIBLE);
                right_tv_content.setText(item.getContent());
            }else {
                right_img_one.setVisibility(View.VISIBLE);
                right_tv_content.setVisibility(View.GONE);
                Glide.with(mContext).load(RequstUrl.URL.HOST+item.getContent()).into(right_img_one);
            }
       }
    }
}

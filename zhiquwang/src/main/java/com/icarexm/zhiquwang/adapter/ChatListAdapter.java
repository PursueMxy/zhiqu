package com.icarexm.zhiquwang.adapter;

import android.content.Context;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ChatMessageBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class ChatListAdapter extends HelperRecyclerViewAdapter<ChatMessageBean.NameValuePairsBean> {
    public ChatListAdapter(Context context) {
        super(context, R.layout.list_customer_chat_right,R.layout.list_customer_chat_item);
    }

    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, int position, ChatMessageBean.NameValuePairsBean item) {
       if (item.getSide()==1){

       }else {

       }
    }

    @Override
    public int checkLayout(ChatMessageBean.NameValuePairsBean item, int position) {
        return super.checkLayout(item, position);
    }
}

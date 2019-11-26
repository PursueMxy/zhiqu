package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.MessageBean;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

public class MessageAdapter extends HelperRecyclerViewAdapter<MessageBean.DataBeanX.DataBean> {
    public Context context;

    public MessageAdapter(Context context) {
        super(context, R.layout.list_meaasge);
        this.context=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,MessageBean.DataBeanX.DataBean item) {
       TextView tv__content = viewHolder.getView(R.id.list_message_content);
       TextView tv_time = viewHolder.getView(R.id.list_message_time);
       tv__content.setText(item.getMessage());
       tv_time.setText(item.getCreate_time());
    }
}

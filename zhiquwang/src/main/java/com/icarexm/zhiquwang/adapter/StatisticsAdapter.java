package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.StatisticsBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StatisticsAdapter extends BaseExpandableListAdapter {

    private final Context mContext;
    private List<StatisticsBean.DataBean.TotalInfoBean> data=new ArrayList<>();

    public StatisticsAdapter(Context context) {
        this.mContext = context;

    }

    /**
     * 自定义设置数据方法；
     * 通过notifyDataSetChanged()刷新数据，可保持当前位置
     *
     * @param data 需要刷新的数据
     */
    public void setData(List<StatisticsBean.DataBean.TotalInfoBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getGroupCount() {
        if (data != null && data.size() > 0) {
            return data.size();
        } else {
            return 0;
        }
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (data.get(groupPosition).getInfo() != null && data.get(groupPosition).getInfo()  .size() > 0) {
            return data.get(groupPosition).getInfo().size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return data.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return data.get(groupPosition).getInfo().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_statistics, null);
            groupViewHolder = new GroupViewHolder(convertView);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.tv_name.setText( data.get(groupPosition).getClasses_name());
        groupViewHolder.tv_hours.setText("共"+data.get(groupPosition).getHours()+"小时");
        groupViewHolder.tv_dayShift_price.setText("¥ "+data.get(groupPosition).getPrice());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.list_statistics_item, null);
            childViewHolder = new ChildViewHolder(convertView);
            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        StatisticsBean.DataBean.TotalInfoBean totalInfoBean = data.get(groupPosition);
        childViewHolder.item_tv_name.setText(totalInfoBean.getClasses_name());
        childViewHolder.tv_holidays.setText("共"+totalInfoBean.getHours()+"小时");
        childViewHolder.tv_holidays_price.setText("¥ "+totalInfoBean.getPrice()+"");
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    static class GroupViewHolder {
          @BindView(R.id.list_statistics_tv_name)
          TextView tv_name;
          @BindView(R.id.list_statistics_tv_hours)
                  TextView tv_hours;
          @BindView(R.id.list_statistics_tv_dayShift_price)
                  TextView tv_dayShift_price;
        GroupViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }

    static class ChildViewHolder {
        @BindView(R.id.list_statistics_item_tv_name)
                TextView item_tv_name;
        @BindView(R.id.list_statistics_item_tv_holidays)
                TextView tv_holidays;
        @BindView(R.id.list_statistics_item_holidays_price)
                TextView tv_holidays_price;
        ChildViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}

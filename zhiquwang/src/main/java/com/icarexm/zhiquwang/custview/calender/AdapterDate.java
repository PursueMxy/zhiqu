package com.icarexm.zhiquwang.custview.calender;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icarexm.zhiquwang.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/16.
 */

public class AdapterDate extends BaseAdapter {

    private Context context;
    private List<Integer> days = new ArrayList<>();
    //日历数据
    private List<Boolean> status = new ArrayList<>();


    //签到状态，实际应用中初始化签到状态可通过该字段传递
    private OnSignedSuccess onSignedSuccess;
    private final int maxDay;
    private int today=1;
    //签到成功的回调方法，相应的可自行添加签到失败时的回调方法

    public AdapterDate(Context context) {
        this.context = context;
        today = Integer.parseInt(DateUtil.getToday());
        //获取当月天数
        maxDay = DateUtil.getCurrentMonthLastDay();
        for (int i = 0; i < DateUtil.getFirstDayOfMonth() - 1; i++) {
            //DateUtil.getFirstDayOfMonth()获取当月第一天是星期几，星期日是第一天，依次类推
            days.add(0);
            //0代表需要隐藏的item
            status.add(false);
            //false代表为签到状态
        }
        for (int i = 0; i < maxDay; i++) {
            days.add(i+1);
            //初始化日历数据
            status.add(false);
            //初始化日历签到状态
        }
    }

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int i) {
        return days.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null){
            view = LayoutInflater.from(context).inflate(R.layout.item_day,null);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv = view.findViewById(R.id.tvWeek);
        viewHolder.rlItem = view.findViewById(R.id.rlItem);
        viewHolder.img_to_card_week = view.findViewById(R.id.item_to_card_week);
        viewHolder.tv.setText(days.get(i)+"");
        viewHolder.img_to_card_week.setVisibility(View.GONE);
        if(days.get(i)==0){
            viewHolder.rlItem.setVisibility(View.GONE);
        }
        if(status.get(i)){
            viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
            viewHolder.tv.setBackgroundResource(R.drawable.bg_green_22);
        }else{
            if (days.get(i)==today){
                viewHolder.tv.setTextColor(Color.parseColor("#FFFFFF"));
                viewHolder.tv.setBackgroundResource(R.drawable.bg_green_22);
            }else {
                viewHolder.tv.setTextColor(Color.parseColor("#666666"));
                viewHolder.tv.setBackgroundColor(context.getResources().getColor(R.color.white));
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status.get(i)){
                    Toast.makeText(context,"重复选择", Toast.LENGTH_SHORT).show();
                }else{
//                    Toast.makeText(context,"Sign in success!", Toast.LENGTH_SHORT).show();
                    for (int a = 0; a < status.size(); a++) {
                        status.set(a, false);
                        Boolean aBoolean = status.get(i);
                    }

                    status.set(i,true);
                    notifyDataSetChanged();
                    if(onSignedSuccess!=null){
                        onSignedSuccess.OnSignedSuccess();
                    }
                }
            }
        });
        return view;
    }

    class ViewHolder{
        RelativeLayout rlItem;
        TextView tv;
        ImageView img_to_card_week;
    }

    public void setOnSignedSuccess(OnSignedSuccess onSignedSuccess){
        this.onSignedSuccess = onSignedSuccess;
    }
}

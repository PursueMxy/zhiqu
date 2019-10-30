package com.icarexm.zhiquwang.view.actvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.IndicatorAdapter;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;
import com.icarexm.zhiquwang.adapter.ViewPagerAdapter;
import com.icarexm.zhiquwang.custview.LabelsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecruitDetailActivity extends AppCompatActivity {

    private Context mContext;
    @BindView(R.id.recruit_dtl_viewPage)
    ViewPager2 recruit_dtl_viewPage;
    @BindView(R.id.recruit_dtl_rcv_indicator)
    RecyclerView recyclerView;
    @BindView(R.id.recruit_dtl_labels)
    LabelsView labelsView;
    private int CurrentItem=0;
    private List<String> list;
    private int DELAYMILLIS=10000;
    private ViewPagerAdapter viewPagerAdapter;
    private IndicatorAdapter indicatorAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_detail);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        list = new ArrayList<>();
        list.add("页面一");
        list.add("页面二");
        list.add("页面三");
        list.add("页面四");
        viewPagerAdapter = new ViewPagerAdapter(this, list, recruit_dtl_viewPage);
        recruit_dtl_viewPage.setAdapter(viewPagerAdapter);
        handler.postDelayed(runnable,DELAYMILLIS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        indicatorAdapter = new IndicatorAdapter(mContext, list, CurrentItem);
        recyclerView.setAdapter(indicatorAdapter);
        indicatorAdapter.refreshData(CurrentItem);
        recruit_dtl_viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            // 当前页面被滑动时调用
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            // 当新的页面被选中时调用
            @Override
            public void onPageSelected(int position) {
                CurrentItem=position;
                indicatorAdapter.refreshData(CurrentItem);
                indicatorAdapter.notifyDataSetChanged();
                super.onPageSelected(position);
            }
            // 当滑动状态改变时调用
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        ArrayList<String> label = new ArrayList<>();
        label.add("Android");
        label.add("IOS");
        label.add("前端");
        label.add("后台");
        label.add("微信开发");
        labelsView.setLabels(label); //直接设置一个字符串数组就可以了。
    }


    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            if (CurrentItem<list.size()-1){
                CurrentItem++;
            }else {
                CurrentItem=0;
            }
            recruit_dtl_viewPage.setCurrentItem(CurrentItem);
            handler.postDelayed(runnable,DELAYMILLIS);
        }
    };


}

package com.icarexm.zhiquwang.view.actvity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.chaychan.library.BottomBarLayout;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.MyViewPager;
import com.icarexm.zhiquwang.view.fragment.ClockInFragment;
import com.icarexm.zhiquwang.view.fragment.HomeFragment;
import com.icarexm.zhiquwang.view.fragment.MinFragment;
import com.icarexm.zhiquwang.view.fragment.RecordOvertimeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.home_myviewpager)
    MyViewPager mVpContent;
    @BindView(R.id.home_bottombarly)
    BottomBarLayout home_bottombarly;
    private int currentItems=0;
    private List<Fragment> mFragmentList=new ArrayList<>();
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        AddFragment();
        InitUI();
    }


    private void AddFragment() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        mFragmentList.add(homeFragment);
        RecordOvertimeFragment recordOvertimeFragment=new RecordOvertimeFragment();
        Bundle bundle1 = new Bundle();
        recordOvertimeFragment.setArguments(bundle1);
        mFragmentList.add(recordOvertimeFragment);
        ClockInFragment clockInFragment = new ClockInFragment();
        Bundle bundle2 = new Bundle();
        clockInFragment.setArguments(bundle2);
        mFragmentList.add(clockInFragment);
        MinFragment minFragment = new MinFragment();
        Bundle bundle3 = new Bundle();
        minFragment.setArguments(bundle3);
        mFragmentList.add(minFragment);
    }

    private void InitUI() {
        home_bottombarly.setSmoothScroll(true);

        mVpContent.setAdapter(new MyAdapter(getSupportFragmentManager()));
        home_bottombarly.setViewPager(mVpContent);
        home_bottombarly.setCurrentItem(currentItems);
    }


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}

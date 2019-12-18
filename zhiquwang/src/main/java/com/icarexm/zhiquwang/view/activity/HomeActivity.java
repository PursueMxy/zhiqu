package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.chaychan.library.BottomBarItem;
import com.chaychan.library.BottomBarLayout;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.MyViewPager;
import com.icarexm.zhiquwang.view.fragment.ClockInFragment;
import com.icarexm.zhiquwang.view.fragment.HomeFragment;
import com.icarexm.zhiquwang.view.fragment.MinFragment;
import com.icarexm.zhiquwang.view.fragment.RecordOvertimeFragment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_myviewpager)
    MyViewPager mVpContent;
    @BindView(R.id.home_bottombarly)
    BottomBarLayout home_bottombarly;
    private int currentItems=0;
    private List<Fragment> mFragmentList=new ArrayList<>();
    private Context mContext;
    private CustomProgressDialog progressDialog = null;//加载页
    private HomeFragment homeFragment;
    private RecordOvertimeFragment recordOvertimeFragment;
    private ClockInFragment clockInFragment;
    private MinFragment minFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        //权限申请
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        AddFragment();
        InitUI();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        try {
            currentItems = Integer.parseInt(intent.getStringExtra("currentItems"));
        }catch (Exception e){
            currentItems=0;
        }
        if (currentItems==0) {
            homeFragment.UpdateUI();
        }else if (currentItems==1){
            recordOvertimeFragment.UpdateUI();
        }else if (currentItems==2){
            clockInFragment.UpdateUI();
        }else if (currentItems==3){
            minFragment.UpdateUI();
        }
        home_bottombarly.setCurrentItem(currentItems);
    }

    private void AddFragment() {
        homeFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        homeFragment.setArguments(bundle);
        mFragmentList.add(homeFragment);
        recordOvertimeFragment = new RecordOvertimeFragment();
        Bundle bundle1 = new Bundle();
        recordOvertimeFragment.setArguments(bundle1);
        mFragmentList.add(recordOvertimeFragment);
        clockInFragment = new ClockInFragment();
        Bundle bundle2 = new Bundle();
        clockInFragment.setArguments(bundle2);
        mFragmentList.add(clockInFragment);
        minFragment = new MinFragment();
        Bundle bundle3 = new Bundle();
        minFragment.setArguments(bundle3);
        mFragmentList.add(minFragment);
    }

    private void InitUI() {
        home_bottombarly.setSmoothScroll(true);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        mVpContent.setAdapter(myAdapter);
        home_bottombarly.setViewPager(mVpContent);
        home_bottombarly.setCurrentItem(currentItems);
        home_bottombarly.setOnItemSelectedListener(new BottomBarLayout.OnItemSelectedListener() {
            @Override
            public void onItemSelected(BottomBarItem bottomBarItem, int i, int currentPosition) {
               if (currentPosition==0) {
                   homeFragment.UpdateUI();
               }else if (currentPosition==1){
                   recordOvertimeFragment.UpdateUI();
               }else if (currentPosition==2){
                   clockInFragment.UpdateUI();
               }else if (currentPosition==3){
                   minFragment.UpdateUI();
               }
            }
        });


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

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(this);
            }
            progressDialog.show();
        }catch (Exception e){

        }
    }

    //关闭刷新
    public void LoadingDialogClose(){
        try {
            if (progressDialog != null){
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch (Exception e){

        }

    }

}

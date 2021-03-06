package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.icarexm.zhiquwang.MainActivity;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.GuidePageAdapter;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.view.fragment.GuideOneFragment;
import com.icarexm.zhiquwang.view.fragment.GuideThreeFragment;
import com.icarexm.zhiquwang.view.fragment.GuideTwoFragment;

import java.util.ArrayList;

public class GuidePageActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private LinearLayout mDotLayout;
    private ArrayList<Fragment> mFragments;
    private ImageView[] dots;
    private int currentIndex;
    private SharedPreferences share;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String bootpage = share.getString("bootpage", "");
        try {
            if (bootpage.equals("true")){
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }else {
                InitUI();
                initData();
                initDots();
                initListeners();
            }
        }catch (Exception e){
            InitUI();
            initData();
            initDots();
            initListeners();
        }
    }
    private void InitUI() {
        mViewPager = (ViewPager) findViewById(R.id.guide_view_pager);
        mDotLayout = (LinearLayout) findViewById(R.id.dots_layout);
    }

    private void initData() {
        mFragments = new ArrayList<Fragment>();
        mFragments.add(GuideOneFragment.newInstance());
        mFragments.add(GuideTwoFragment.newInstance());
        mFragments.add(GuideThreeFragment.newInstance());
        GuidePageAdapter mPageAdapter = new GuidePageAdapter(getSupportFragmentManager(), mFragments);
        mViewPager.setAdapter(mPageAdapter);
    }

    private void initListeners() {
        mViewPager.addOnPageChangeListener(this);
    }

    private void initDots() {
        dots = new ImageView[mFragments.size()];
        for (int i = 0; i < mFragments.size(); i++) {
            dots[i] = (ImageView) mDotLayout.getChildAt(i);
            if (i != 0) {
                dots[i].setImageResource(R.drawable.ic_circle_dot);
            }
        }
        currentIndex = 0;
        dots[currentIndex].setImageResource(R.drawable.ic_rect_dot);
    }

    private void setCurrentDot(int position) {

        if (position < 0 || position > mFragments.size() - 1
                || currentIndex == position) {
            return;
        }
        dots[position].setImageDrawable(null);
        dots[position].setImageResource(R.drawable.ic_rect_dot);
        dots[currentIndex].setImageDrawable(null);
        dots[currentIndex].setImageResource(R.drawable.ic_circle_dot);
        currentIndex = position;
    }


    // 当前页面被滑动时调用
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 当新的页面被选中时调用
    public void onPageSelected(int position) {

        // 设置底部小点选中状态
        setCurrentDot(position);
    }

    // 当滑动状态改变时调用
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.IndicatorAdapter;
import com.icarexm.zhiquwang.adapter.RecruitAdapter;
import com.icarexm.zhiquwang.adapter.ViewPagerAdapter;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.utils.MxyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecruitDetailActivity extends AppCompatActivity {

    private Context mContext;
    @BindView(R.id.recruit_dtl_viewPage)
    ViewPager2 recruit_dtl_viewPage;
    @BindView(R.id.recruit_dtl_rcv_indicator)
    RecyclerView recyclerView;
    @BindView(R.id.recruit_dtl_labels)
    LabelsView labelsView;
    @BindView(R.id.recruit_dtl_rcv_recruitList)
    RecyclerView rcv_recruitList;
    private int CurrentItem=0;
    private List<String> list;
    private int DELAYMILLIS=10000;
    private ViewPagerAdapter viewPagerAdapter;
    private IndicatorAdapter indicatorAdapter;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;


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
    @OnClick({R.id.recruit_dtl_tv_wechat,R.id.recruit_dtl_tv_nearby_store,R.id.recruit_dtl_tv_callPhone})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recruit_dtl_tv_wechat:
                WechatDialog();
                break;
            case R.id.recruit_dtl_tv_nearby_store:
                startActivity(new Intent(mContext,NearbyStoreActivity.class));
                break;
            case R.id.recruit_dtl_tv_callPhone:
                callPhoneDialog();
                break;
        }
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rcv_recruitList.setLayoutManager(linearLayoutManager);
        RecruitAdapter recruitAdapter = new RecruitAdapter(mContext, list);
        rcv_recruitList.setAdapter(recruitAdapter);
        rcv_recruitList.addItemDecoration( new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10)));
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

    //转发微信弹出窗
    public void  WechatDialog(){
        final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_wechat_share, null);
        inflate.findViewById(R.id.dialog_wechat_share_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        bottomDialog.setContentView(inflate);
        bottomDialog.show();
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
    //电话联系
    public void callPhoneDialog(){
        dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
        tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = tv_phone_number.getText().toString();
                Intent intentcall = new Intent();
                //设置拨打电话的动作
                intentcall.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcall.setData(Uri.parse("tel:" + mobile));
                //开启打电话的意图
                startActivity(intentcall);
                alertDialog.dismiss();
            }
        });
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialog = builder.setView(dialog_callphone)
                .create();
        alertDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}

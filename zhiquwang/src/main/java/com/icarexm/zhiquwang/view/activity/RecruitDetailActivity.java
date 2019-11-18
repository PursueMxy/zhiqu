package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.IndicatorAdapter;
import com.icarexm.zhiquwang.adapter.RecruitAdapter;
import com.icarexm.zhiquwang.adapter.ViewPagerAdapter;
import com.icarexm.zhiquwang.bean.JobDetailBean;
import com.icarexm.zhiquwang.contract.RecruitDetailContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomVideoView;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.presenter.RecruitDetailPresenter;
import com.icarexm.zhiquwang.utils.MxyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecruitDetailActivity extends BaseActivity implements RecruitDetailContract.View {

    private Context mContext;
    @BindView(R.id.recruit_dtl_viewPage)
    ViewPager2 recruit_dtl_viewPage;
    @BindView(R.id.recruit_dtl_rcv_indicator)
    RecyclerView recyclerView;
    @BindView(R.id.recruit_dtl_labels)
    LabelsView labelsView;
    @BindView(R.id.recruit_dtl_rcv_recruitList)
    RecyclerView rcv_recruitList;
    @BindView(R.id.recruit_dtl_tv_jobName)
    TextView tv_job_name;
    @BindView(R.id.recruit_dtl_tv_salary)
    TextView tv_salary;
    @BindView(R.id.recruit_dtl_tv_company)
    TextView tv_company;
    @BindView(R.id.recruit_dtl_tv_address)
    TextView tv_address;
    @BindView(R.id.recruit_dtl_tv_label_price)
    TextView tv_label_price;
    @BindView(R.id.recruit_dtl_tv_zhSalary)
    TextView tv_zhSalary;
    @BindView(R.id.recruit_dtl_tv_hourSalary)
    TextView tv_hourSalary;
    @BindView(R.id.recruit_dtl_tv_other_benefits)
    TextView tv_other_benefits;
    @BindView(R.id.recruit_dtl_ask_tv_age)
    TextView tv_age;
    @BindView(R.id.recruit_dtl_ask_tv_education)
    TextView tv_education;
    @BindView(R.id.recruit_dtl_ask_tv_workingLife)
    TextView tv_workingLife;
    @BindView(R.id.recruit_dtl_tv_other_ask)
    TextView tv_other_ask;
    @BindView(R.id.recruit_dtl_tv_workingContent)
    TextView tv_workingContent;
    @BindView(R.id.recruit_dtl_commuteDt)
    TextView tv_commuteDt;
    @BindView(R.id.recruit_dtl_tv_introduce)
    TextView tv_introduce;
    private int CurrentItem=0;
    private List<String> list=new ArrayList<>();
    private int DELAYMILLIS=10000;
    private ViewPagerAdapter viewPagerAdapter;
    private IndicatorAdapter indicatorAdapter;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;
    private RecruitDetailPresenter recruitDetailPresenter;
    private String token;
    private String job_id;
    private List<String> img_arr=new ArrayList<>();
    private int have_video=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_detail);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        job_id = intent.getStringExtra("job_id");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        recruitDetailPresenter = new RecruitDetailPresenter(this);
        recruitDetailPresenter.GetJobDetail(token,job_id);
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
        handler.postDelayed(runnable,DELAYMILLIS);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
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
                viewPagerAdapter.refreshData(CurrentItem);
                if (CurrentItem==0&&have_video==2){
                    viewPagerAdapter.notifyDataSetChanged();
                }
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
        RecruitAdapter recruitAdapter = new RecruitAdapter(mContext,list);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

   //返回数据跟新UI
    public void UpdateUI(int code, String msg, JobDetailBean.DataBean dataBean){
        if (code==1){
            img_arr = dataBean.getImg_arr();
            have_video = dataBean.getHave_video();
            viewPagerAdapter = new ViewPagerAdapter(this, img_arr, recruit_dtl_viewPage, have_video);
            recruit_dtl_viewPage.setAdapter(viewPagerAdapter);
            indicatorAdapter = new IndicatorAdapter(mContext, img_arr, CurrentItem, have_video);
            recyclerView.setAdapter(indicatorAdapter);
            indicatorAdapter.refreshData(CurrentItem);
            List<JobDetailBean.DataBean.LabelArrBean> label_arr = dataBean.getLabel_arr();
            ArrayList<String> label = new ArrayList<>();
            for (int a=0;a<label_arr.size();a++){
                label.add(label_arr.get(a).getLabel_name());
            }
            labelsView.setLabels(label);
            tv_job_name.setText(dataBean.getJob_name());
            tv_salary.setText(dataBean.getSalary()+"/月("+dataBean.getSalary_hour()+"/小时)");
            tv_company.setText(dataBean.getEnterprise_name());
            tv_address.setText(dataBean.getAddress());
            tv_label_price.setText(dataBean.getLabel_price());
            tv_zhSalary.setText("综合工资："+dataBean.getSalary()+"/月");
            tv_hourSalary.setText("小时工资："+dataBean.getSalary_hour()+"/日");
            tv_other_benefits.setText("其它福利 ："+dataBean.getSalary_other());
            tv_age.setText("性别年龄："+dataBean.getAge_explain());
            tv_education.setText("学      历："+dataBean.getEducation());
            tv_workingLife.setText("工作年限："+dataBean.getWork_year());
            tv_other_ask.setText(dataBean.getNeed_other());
            tv_workingContent.setText(dataBean.getWork_describe());
//            tv_commuteDt.setText(dataBean.get);
            tv_introduce.setText(dataBean.getIntroduce());
        }
    }
}

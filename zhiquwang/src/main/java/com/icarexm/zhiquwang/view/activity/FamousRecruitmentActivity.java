package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.FRAdapter;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.contract.FamousRecruitmentContract;
import com.icarexm.zhiquwang.presenter.FamousRecruitmentPresenter;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FamousRecruitmentActivity extends BaseActivity implements FamousRecruitmentContract.View {
     @BindView(R.id.famous_recruitment_recyclerView)
    XRecyclerView mRecyclerView;
     @BindView(R.id.famous_recruitment_tv_title)
    TextView tv_title;
    private Context mContext;
    private FRAdapter frAdapter;
    private RadioGroup radioGroup;
    private RadioButton radiobutton_area;
    private RadioButton radiobutton_salary;
    private RadioButton radiobutton_age;
    private RadioButton radiobutton_trade;
    private RadioButton radiobutton_work;
    private String zone_id;
    private FamousRecruitmentPresenter famousRecruitmentPresenter;
    private SharedPreferences share;
    private int limit=20;
    private int page=0;
    private String token;
    private String area_id;
    private String salary_id;
    private String age_id;
    private String vocation_id;
    private String environment_id;
    private String Job_id;
    private List<HomeDataBean.DataBeanX.DataBean> homeDataList=new ArrayList<>();
    private String zone_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_famous_recruitment);
        mContext = getApplicationContext();
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        Intent intent = getIntent();
        zone_id = intent.getStringExtra("zone_id");
        zone_name = intent.getStringExtra("zone_name");
        ButterKnife.bind(this);
        InitUI();
        tv_title.setText(zone_name);
        famousRecruitmentPresenter = new FamousRecruitmentPresenter(this);
        famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,Job_id);
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        frAdapter = new FRAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }
        });
        mRecyclerView.setAdapter(frAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10))
                        , 0
                        , 0);
            }
        });
        frAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                startActivity(new Intent(mContext, RecruitDetailActivity.class));
            }
        });
        frAdapter.notifyDataSetChanged();
        radioGroup = findViewById(R.id.famous_recruitment_radioGroup);
        radiobutton_area = findViewById(R.id.famous_recruitment_radiobutton_area);
        radiobutton_salary = findViewById(R.id.famous_recruitment_radiobutton_salary);
        radiobutton_age = findViewById(R.id.famous_recruitment_radiobutton_age);
        radiobutton_trade = findViewById(R.id.famous_recruitment_radiobutton_trade);
        radiobutton_work = findViewById(R.id.famous_recruitment_radiobutton_work);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.famous_recruitment_radiobutton_area:
                        radiobutton_area.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.famous_recruitment_radiobutton_salary:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.famous_recruitment_radiobutton_age:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.famous_recruitment_radiobutton_trade:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        radiobutton_work.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        break;
                    case R.id.famous_recruitment_radiobutton_work:
                        radiobutton_area.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_area.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_salary.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_salary.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_age.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_age.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_trade.setBackgroundResource(R.drawable.title_nochoosed_color);
                        radiobutton_trade.setTextColor(getResources().getColor(R.color.ff4e4d4d));
                        radiobutton_work.setBackgroundResource(R.drawable.title_choosed_color);
                        radiobutton_work.setTextColor(getResources().getColor(R.color.ff00b6ce));
                        break;
                }
            }
        });
    }

    @OnClick({R.id.famous_recruitment_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.famous_recruitment_img_back:
                finish();
                break;
        }
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

    public  void UpdateUI(int code,String msg, HomeDataBean.DataBeanX data){
      if (code==1){
          homeDataList = data.getData();
          frAdapter.setListAll(homeDataList);
          frAdapter.notifyDataSetChanged();
      }
    }
}

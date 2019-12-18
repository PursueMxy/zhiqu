package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.FRAdapter;
import com.icarexm.zhiquwang.bean.HomeBannerBean;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.contract.FamousRecruitmentContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.presenter.FamousRecruitmentPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.fragment.HomeFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
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
     @BindView(R.id.famous_recruitment_list_city)
     NoScrollListView list_city;
     @BindView(R.id.famous_recruitment_list_salary)
     NoScrollListView list_salary;
    @BindView(R.id.famous_recruitment_list_age)
    NoScrollListView list_age;
    @BindView(R.id.famous_recruitment_list_vocation)
    NoScrollListView list_vocation;
    @BindView(R.id.famous_recruitment_list_environment)
    NoScrollListView list_environment;
    @BindView(R.id.famous_recruitment_rl_city)
    RelativeLayout rl_city;
    @BindView(R.id.famous_recruitment_rl_salary)
    RelativeLayout rl_salary;
    @BindView(R.id.famous_recruitment_rl_age)
    RelativeLayout rl_age;
    @BindView(R.id.famous_recruitment_rl_vocation)
    RelativeLayout rl_vocation;
    @BindView(R.id.famous_recruitment_rl_environment)
    RelativeLayout rl_environment;

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
    private String job_id;
    private List<HomeDataBean.DataBeanX.DataBean> homeDataList=new ArrayList<>();
    private String zone_name;
    private String city_name;
    private List<HomeBannerBean.DataBean.OptionListBean.AreaListBean> area_list=new ArrayList<>();
    private CityAdapter cityAdapter;
    private SalaryAdapter salaryAdapter;
    private AgeAdapter ageAdapter;
    private VocationAdapter vocationAdapter;
    private EnvironmentAdapter environmentAdapter;
    private boolean IsArea=true;
    private boolean IsSalary=true;
    private boolean IsAge=true;
    private boolean IsVocation=true;
    private boolean IsEironment=true;
    private List<HomeBannerBean.DataBean.OptionListBean.SalaryBean> salary=new ArrayList<>();
    private List<HomeBannerBean.DataBean.OptionListBean.AgeBean> ageList=new ArrayList<>();
    private List<HomeBannerBean.DataBean.OptionListBean.EnvironmentBean> environment=new ArrayList<>();
    private List<HomeBannerBean.DataBean.OptionListBean.VocationBean> vocation=new ArrayList<>();
    private CustomProgressDialog progressDialog;


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
        city_name = intent.getStringExtra("city_name");
        ButterKnife.bind(this);
        famousRecruitmentPresenter = new FamousRecruitmentPresenter(this);
        InitUI();
        LoadingDialogShow();
        InitData();
        tv_title.setText(zone_name);
        famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
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
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);
            }
        });
        frAdapter.setListAll(homeDataList);
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
                Intent intent = new Intent(mContext, RecruitDetailActivity.class);
                intent.putExtra("job_id",homeDataList.get(position).getJob_id()+"");
                intent.putExtra("zone_name",zone_name);
                startActivity(intent);
            }
        });
        frAdapter.notifyDataSetChanged();
        radioGroup = findViewById(R.id.famous_recruitment_radioGroup);
        radiobutton_area = findViewById(R.id.famous_recruitment_radiobutton_area);
        radiobutton_salary = findViewById(R.id.famous_recruitment_radiobutton_salary);
        radiobutton_age = findViewById(R.id.famous_recruitment_radiobutton_age);
        radiobutton_trade = findViewById(R.id.famous_recruitment_radiobutton_trade);
        radiobutton_work = findViewById(R.id.famous_recruitment_radiobutton_work);
        SltAdapter();
        radiobuttonClick();
    }

    private void radiobuttonClick() {
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
                        rl_salary.setVisibility(View.GONE);
                        rl_age.setVisibility(View.GONE);
                        rl_vocation.setVisibility(View.GONE);
                        rl_environment.setVisibility(View.GONE);
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
                        rl_city.setVisibility(View.GONE);
                        rl_age.setVisibility(View.GONE);
                        rl_vocation.setVisibility(View.GONE);
                        rl_environment.setVisibility(View.GONE);
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
                        rl_city.setVisibility(View.GONE);
                        rl_salary.setVisibility(View.GONE);
                        rl_vocation.setVisibility(View.GONE);
                        rl_environment.setVisibility(View.GONE);
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
                        rl_city.setVisibility(View.GONE);
                        rl_salary.setVisibility(View.GONE);
                        rl_age.setVisibility(View.GONE);
                        rl_environment.setVisibility(View.GONE);
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
                        rl_city.setVisibility(View.GONE);
                        rl_salary.setVisibility(View.GONE);
                        rl_vocation.setVisibility(View.GONE);
                        rl_age.setVisibility(View.GONE);
                        break;
                }
            }
        });
        radiobutton_area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsArea) {
                    rl_city.setVisibility(View.VISIBLE);
                }else {
                    rl_city.setVisibility(View.GONE);
                }
                IsArea=!IsArea;
                IsSalary=true;
                IsAge=true;
                IsVocation=true;
                IsEironment=true;
            }
        });
        radiobutton_salary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsSalary) {
                    rl_salary.setVisibility(View.VISIBLE);
                }else {
                    rl_salary.setVisibility(View.GONE);
                }
                IsSalary=!IsSalary;
                IsArea=true;
                IsAge=true;
                IsVocation=true;
                IsEironment=true;
            }
        });
        radiobutton_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsAge) {
                    rl_age.setVisibility(View.VISIBLE);
                }else {
                    rl_age.setVisibility(View.GONE);
                }
                IsAge=!IsAge;
                IsArea=true;
                IsSalary=true;
                IsVocation=true;
                IsEironment=true;
            }
        });
        radiobutton_trade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsVocation) {
                    rl_vocation.setVisibility(View.VISIBLE);
                }else {
                    rl_vocation.setVisibility(View.GONE);
                }
                IsVocation=!IsVocation;
                IsArea=true;
                IsSalary=true;
                IsAge=true;
                IsEironment=true;
            }
        });
        radiobutton_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsEironment) {
                    rl_environment.setVisibility(View.VISIBLE);
                }else {
                    rl_environment.setVisibility(View.GONE);
                }
                IsEironment=!IsEironment;
                IsArea=true;
                IsSalary=true;
                IsAge=true;
                IsVocation=true;
            }
        });
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.Home)
                .params("city",city_name)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        HomeBannerBean homeBannerBean = new GsonBuilder().create().fromJson(response.body(), HomeBannerBean.class);
                        if (homeBannerBean.getCode()==1){
                            HomeBannerBean.DataBean data = homeBannerBean.getData();
                            if (data!=null){
                                HomeBannerBean.DataBean.OptionListBean option_list = data.getOption_list();
                                area_list = option_list.getArea_list();
                                area_list.add(0,new HomeBannerBean.DataBean.OptionListBean.AreaListBean(999999,"全部"));
                                cityAdapter.notifyDataSetChanged();
                                salary = option_list.getSalary();
                                salary.add(0,new HomeBannerBean.DataBean.OptionListBean.SalaryBean(999999,"全部"));
                                salaryAdapter.notifyDataSetChanged();
                                ageList = option_list.getAge();
                                ageList.add(0,new HomeBannerBean.DataBean.OptionListBean.AgeBean(999999,"全部"));
                                ageAdapter.notifyDataSetChanged();
                                vocation = option_list.getVocation();
                                vocation.add(0,new HomeBannerBean.DataBean.OptionListBean.VocationBean(999999,"全部"));
                                vocationAdapter.notifyDataSetChanged();
                                environment = option_list.getEnvironment();
                                environment.add(0,new HomeBannerBean.DataBean.OptionListBean.EnvironmentBean(999999,"全部"));
                                environmentAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                });
    }

    private void SltAdapter() {
        Drawable drawable=getResources().getDrawable(R.drawable.bg_green);
        cityAdapter = new CityAdapter();
        list_city.setAdapter(cityAdapter);
        list_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                area_id=area_list.get(i).getArea_id()+"";
                if (area_id.equals("999999")){
                    area_id="";
                }
                rl_city.setVisibility(View.GONE);
                IsArea=true;
                famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
            }
        });
        list_city.setSelector(drawable);
        salaryAdapter = new SalaryAdapter();
        list_salary.setAdapter(salaryAdapter);
        list_salary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                salary_id= salary.get(i).getSalary_id()+"";
                if (salary_id.equals("999999")){
                    salary_id="";
                }
                rl_salary.setVisibility(View.GONE);
                IsSalary=true;
                famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
            }
        });
        list_salary.setSelector(drawable);
        ageAdapter = new AgeAdapter();
        list_age.setAdapter(ageAdapter);
        list_age.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                age_id= ageList.get(i).getAge_id()+"";
                if (age_id.equals("999999")){
                    age_id="";
                }
                rl_age.setVisibility(View.GONE);
                IsAge=true;
                famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
            }
        });
        list_age.setSelector(drawable);
        vocationAdapter = new VocationAdapter();
        list_vocation.setAdapter(vocationAdapter);
        list_vocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vocation_id= vocation.get(i).getVocation_id()+"";
                if (vocation_id.equals("999999")){
                    vocation_id="";
                }
                rl_vocation.setVisibility(View.GONE);
                IsVocation=true;
                famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
            }
        });
        list_vocation.setSelector(drawable);
        environmentAdapter = new EnvironmentAdapter();
        list_environment.setAdapter(environmentAdapter);
        list_environment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                environment_id= environment.get(i).getEnvironment_id()+"";
                if (environment_id.equals("999999")){
                    environment_id="";
                }
                rl_environment.setVisibility(View.GONE);
                IsEironment=true;
                famousRecruitmentPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
            }
        });
        list_environment.setSelector(drawable);
    }

    @OnClick({R.id.famous_recruitment_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.famous_recruitment_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.famous_recruitment_img_back)) {
                    finish();
                }
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
       LoadingDialogClose();
      if (code==1){
          homeDataList = data.getData();
          frAdapter.setListAll(homeDataList);
          frAdapter.notifyDataSetChanged();
      }else if (code ==10001){
          ToastUtils.showToast(mContext,msg);
          startActivity(new Intent(mContext,LoginActivity.class));
          finish();
      }
    }

    public class CityAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return area_list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.home_list_item, null);
            TextView tv_city= inflate.findViewById(R.id.home_list_item_tv_content);
            tv_city.setText(area_list.get(i).getArea_name());
            return inflate;
        }
    }

    public class SalaryAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return salary.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.home_list_item, null);
            TextView tv_city= inflate.findViewById(R.id.home_list_item_tv_content);
            tv_city.setText(salary.get(i).getSalary_value());
            return inflate;
        }
    }

    public class AgeAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return ageList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.home_list_item, null);
            TextView tv_city= inflate.findViewById(R.id.home_list_item_tv_content);
            tv_city.setText(ageList.get(i).getAge_value());
            return inflate;
        }
    }

    public class VocationAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return vocation.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.home_list_item, null);
            TextView tv_city= inflate.findViewById(R.id.home_list_item_tv_content);
            tv_city.setText(vocation.get(i).getVocation_value());
            return inflate;
        }
    }

    public class EnvironmentAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return environment.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.home_list_item, null);
            TextView tv_city= inflate.findViewById(R.id.home_list_item_tv_content);
            tv_city.setText(environment.get(i).getEnvironment_value());
            return inflate;
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

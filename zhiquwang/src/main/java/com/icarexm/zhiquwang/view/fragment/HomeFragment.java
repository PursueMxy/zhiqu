package com.icarexm.zhiquwang.view.fragment;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.HomeFmAdapter;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;
import com.icarexm.zhiquwang.bean.HomeBannerBean;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.GlideImageLoader;
import com.icarexm.zhiquwang.custview.MyScrollView;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.FamousRecruitmentActivity;
import com.icarexm.zhiquwang.view.activity.HomeActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.MessageActivity;
import com.icarexm.zhiquwang.view.activity.RecruitActivity;
import com.icarexm.zhiquwang.view.activity.RecruitDetailActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yyydjk.library.BannerLayout;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private BannerLayout bannerLayout;
    //轮播图
    final List<String> urls = new ArrayList<>();
    private RadioGroup radioGroup;
    private RadioButton radiobutton_area;
    private RadioButton radiobutton_salary;
    private RadioButton radiobutton_age;
    private RadioButton radiobutton_trade;
    private RadioButton radiobutton_work;
    private RecyclerView recyclerView;
    private Context mContext;
    private TodayHeatAdapter todayHeatAdapter;
    private XRecyclerView mRecyclerView;
    private HomeFmAdapter homeFmAdapter;
    private GridAdapter gridAdapter;
    private GridView home_gridview;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    private double start_latitude;
    private double start_longitude;
    private String cityName;
    private List<HomeBannerBean.DataBean.OptionListBean.AreaListBean> area_list=new ArrayList<>();
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                    //获取纬度
                    start_latitude = aMapLocation.getLatitude();
                    //获取经度
                    start_longitude = aMapLocation.getLongitude();
                    aMapLocation.getAccuracy();//获取精度信息
                    aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                    aMapLocation.getCountry();//国家信息
                    aMapLocation.getProvince();//省信息
                    //城市信息
                    cityName = aMapLocation.getCity();
                    tv_cityname.setText(cityName);
                    InitData();
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("cityName",cityName);
                    editor.putString("latitude",start_latitude+"");
                    editor.putString("longitude",start_longitude+"");
                    editor.commit();//提交
                }
            }
        }
    };
    AMapLocationClientOption option = new AMapLocationClientOption();
    private AMapLocationClientOption mLocationOption;
    private TextView tv_cityname;
    private SharedPreferences share;
    private String token;
    private List<HomeBannerBean.DataBean.BannerListBean> banner_list=new ArrayList<>();
    private List<HomeBannerBean.DataBean.ZoneListBean> zone_list=new ArrayList<>();
    private NoScrollListView list_city;
    private CityAdapter cityAdapter;
    private RelativeLayout rl_city;
    private RelativeLayout rl_salary;
    private NoScrollListView list_salary;
    private SalaryAdapter salaryAdapter;
    private List<HomeBannerBean.DataBean.OptionListBean.SalaryBean> salary=new ArrayList<>();
    private RelativeLayout rl_age;
    private NoScrollListView list_age;
    private AgeAdapter ageAdapter;
    private List<HomeBannerBean.DataBean.OptionListBean.AgeBean> ageList=new ArrayList<>();
    private RelativeLayout rl_environment;
    private NoScrollListView list_environment;
    private RelativeLayout rl_vocation;
    private NoScrollListView list_vocation;
    private List<HomeBannerBean.DataBean.OptionListBean.VocationBean> vocation=new ArrayList<>();
    private List<HomeBannerBean.DataBean.OptionListBean.EnvironmentBean> environment=new ArrayList<>();
    private VocationAdapter vocationAdapter;
    private EnvironmentAdapter environmentAdapter;
    private int limit=20;
    private int page=1;
    private String zone_id;
    private String area_id;
    private String salary_id;
    private String age_id;
    private String vocation_id;
    private String environment_id;
    private String job_id;
    private List<HomeDataBean.DataBeanX.DataBean> homeDataList=new ArrayList<>();
    private boolean IsArea=true;
    private boolean IsSalary=true;
    private boolean IsAge=true;
    private boolean IsVocation=true;
    private boolean IsEiroment=true;
    private boolean IsUpdate=false;
    private CustomProgressDialog progressDialog;
    private Drawable drawable;
    private MyScrollView scrollview;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        mContext = getContext();
        Resources resources = mContext.getResources();
        drawable = resources.getDrawable(R.drawable.bg_green);
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        InitUI(inflate);
        startLocation();
        return inflate;
    }

    private void InitData() {
        InitHomeData();
        OkGo.<String>post(RequstUrl.URL.Home)
                .params("city",cityName)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            if (!IsUpdate) {
                                HomeActivity homeActivity = (HomeActivity) getActivity();
                                homeActivity.onCancel();
                            }
                            if (progressDialog != null){
                                progressDialog.dismiss();
                                progressDialog = null;
                            }
                        }catch (Exception e){

                        }
                        HomeBannerBean homeBannerBean = new GsonBuilder().create().fromJson(response.body(), HomeBannerBean.class);
                        if (homeBannerBean.getCode()==1){
                            HomeBannerBean.DataBean data = homeBannerBean.getData();
                            if (data!=null){
                                banner_list = data.getBanner_list();
                                urls.clear();
                                for (int a = 0; a< banner_list.size(); a++){
                                    urls.add(banner_list.get(a).getBanner_url());
                                }
                                bannerLayout.setViewUrls(urls);
                                zone_list = data.getZone_list();
                                gridAdapter.notifyDataSetChanged();
                                HomeBannerBean.DataBean.OptionListBean option_list = data.getOption_list();
                                area_list = option_list.getArea_list();
                                cityAdapter.notifyDataSetChanged();
                                salary = option_list.getSalary();
                                salaryAdapter.notifyDataSetChanged();
                                ageList = option_list.getAge();
                                ageAdapter.notifyDataSetChanged();
                                vocation = option_list.getVocation();
                                vocationAdapter.notifyDataSetChanged();
                                environment = option_list.getEnvironment();
                                environmentAdapter.notifyDataSetChanged();
                            }
                        }else if (homeBannerBean.getCode() ==10001){
                            ToastUtils.showToast(mContext,homeBannerBean.getMsg());
                            startActivity(new Intent(mContext, LoginActivity.class));
                            getActivity().finish();
                        }
                    }
                });
    }


    public void InitHomeData(){
        OkGo.<String>post(RequstUrl.URL.HomeData)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .params("zone_id",zone_id)
                .params("area_id",area_id)
                .params("salary_id",salary_id)
                .params("age_id",age_id)
                .params("vocation_id",vocation_id)
                .params("environment_id",environment_id)
                .params("job_id",job_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        HomeDataBean homeDataBean = new GsonBuilder().create().fromJson(response.body(), HomeDataBean.class);
                        if (homeDataBean.getCode()==1){
                            HomeDataBean.DataBeanX data = homeDataBean.getData();
                            homeDataList = data.getData();
                            homeFmAdapter.setListAll(homeDataList);
                            homeFmAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void startLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if(null != mLocationClient){
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //获取一次定位结果：
        mLocationOption.setOnceLocation(true);
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取最近3s内精度最高的一次定位结果：
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒
        mLocationOption.setInterval(3000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    private void InitUI(View inflate) {
        scrollview = inflate.findViewById(R.id.fm_home_scrollview);
        radioGroup = inflate.findViewById(R.id.fm_home_radioGroup);
        radiobutton_area = inflate.findViewById(R.id.fm_home_radiobutton_area);
        radiobutton_salary = inflate.findViewById(R.id.fm_home_radiobutton_salary);
        radiobutton_age = inflate.findViewById(R.id.fm_home_radiobutton_age);
        radiobutton_trade = inflate.findViewById(R.id.fm_home_radiobutton_trade);
        radiobutton_work = inflate.findViewById(R.id.fm_home_radiobutton_work);
        recyclerView = inflate.findViewById(R.id.fm_home_recyclerView);
        mRecyclerView = inflate.findViewById(R.id.fm_home_content_xRecyclerView);
        rl_city = inflate.findViewById(R.id.fm_home_rl_city);
        list_city = inflate.findViewById(R.id.fm_home_list_city);
        rl_salary = inflate.findViewById(R.id.fm_home_rl_salary);
        list_salary = inflate.findViewById(R.id.fm_home_list_salary);
        rl_age = inflate.findViewById(R.id.fm_home_rl_age);
        list_age = inflate.findViewById(R.id.fm_home_list_age);
        rl_vocation = inflate.findViewById(R.id.fm_home_rl_vocation);
        list_vocation = inflate.findViewById(R.id.fm_home_list_vocation);
        rl_environment = inflate.findViewById(R.id.fm_home_rl_environment);
        list_environment = inflate.findViewById(R.id.fm_home_list_environment);
        home_gridview = inflate.findViewById(R.id.fm_home_gridview);
        tv_cityname = inflate.findViewById(R.id.fm_home_tv_cityname);
        bannerLayout= inflate.findViewById(R.id.fm_home_banner);
        bannerLayout.setAutoPlay(true);
        bannerLayout.setImageLoader(new GlideImageLoader());
        //添加点击监听
        bannerLayout.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
            }
        });
        inflate.findViewById(R.id.fm_home_img_message).setOnClickListener(this);
        inflate.findViewById(R.id.fm_home_rl_search).setOnClickListener(this);
        SltGridAdapter();
        SltAdapter();
        recyclerViewAT();
        radiobuttonClick();
        scrollview.setOnScrollListener(new MyScrollView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                if (scrollview.canPullDown()){
                    InitData();
                }else if (scrollview.canPullUp()){
                    Log.e("底部滑动","hdfh");
                }
            }
        });
    }

    private void SltGridAdapter() {
        gridAdapter = new GridAdapter();
        home_gridview.setAdapter(gridAdapter);
        home_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, FamousRecruitmentActivity.class);
                intent.putExtra("zone_id",zone_list.get(i).getZone_id()+"");
                intent.putExtra("zone_name",zone_list.get(i).getZone_name());
                intent.putExtra("city_name",cityName);
                startActivity(intent);
            }
        });
    }

    private void SltAdapter() {
        cityAdapter = new CityAdapter();
        list_city.setAdapter(cityAdapter);
        list_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                area_id=area_list.get(i).getArea_id()+"";
                rl_city.setVisibility(View.GONE);
                IsArea=true;
                InitHomeData();
            }
        });
        list_city.setSelector(drawable);
        salaryAdapter = new SalaryAdapter();
        list_salary.setAdapter(salaryAdapter);
        list_salary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                salary_id=salary.get(i).getSalary_id()+"";
                rl_salary.setVisibility(View.GONE);
                IsSalary=true;
                InitHomeData();
            }
        });
        list_salary.setSelector(drawable);
        ageAdapter = new AgeAdapter();
        list_age.setAdapter(ageAdapter);
        list_age.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                age_id=ageList.get(i).getAge_id()+"";
                rl_age.setVisibility(View.GONE);
                IsAge=true;
                InitHomeData();
            }
        });
        list_age.setSelector(drawable);
        vocationAdapter = new VocationAdapter();
        list_vocation.setAdapter(vocationAdapter);
        list_vocation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                vocation_id=vocation.get(i).getVocation_id()+"";
                rl_vocation.setVisibility(View.GONE);
                IsVocation=true;
                InitHomeData();
            }
        });
        list_vocation.setSelector(drawable);
        environmentAdapter = new EnvironmentAdapter();
        list_environment.setAdapter(environmentAdapter);
        list_environment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                environment_id=environment.get(i).getEnvironment_id()+"";
                rl_environment.setVisibility(View.GONE);
                IsEiroment=true;
                InitHomeData();
            }
        });
        list_environment.setSelector(drawable);
    }

    private void recyclerViewAT() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        todayHeatAdapter = new TodayHeatAdapter(mContext);
        recyclerView.setAdapter(todayHeatAdapter);
        todayHeatAdapter.refreshData(5);
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        homeFmAdapter = new HomeFmAdapter(mContext);
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
        mRecyclerView.setAdapter(homeFmAdapter);
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
        homeFmAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Intent intent = new Intent(mContext, RecruitDetailActivity.class);
                intent.putExtra("job_id",homeDataList.get(position).getJob_id()+"");
                startActivity(intent);
            }
        });
        homeFmAdapter.notifyDataSetChanged();
    }

    private void radiobuttonClick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.fm_home_radiobutton_area:
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
                    case R.id.fm_home_radiobutton_salary:
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
                    case R.id.fm_home_radiobutton_age:
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
                    case R.id.fm_home_radiobutton_trade:
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
                    case R.id.fm_home_radiobutton_work:
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
                IsEiroment=true;
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
                IsEiroment=true;
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
                IsEiroment=true;
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
                IsEiroment=true;
            }
        });
        radiobutton_work.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (IsEiroment) {
                    rl_environment.setVisibility(View.VISIBLE);
                }else {
                    rl_environment.setVisibility(View.GONE);
                }
                IsEiroment=!IsEiroment;
                IsArea=true;
                IsSalary=true;
                IsAge=true;
                IsVocation=true;
            }
        });
    }

    private String imageTranslateUri(int resId) {

        Resources r = getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(resId) + "/"
                + r.getResourceTypeName(resId) + "/"
                + r.getResourceEntryName(resId));

        return uri.toString();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_home_img_message:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;
            case R.id.fm_home_rl_search:
                startActivity(new Intent(mContext, RecruitActivity.class));
                break;
        }
    }

    public class GridAdapter extends BaseAdapter{
       @Override
       public int getCount() {
           return zone_list.size();
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
           View inflate = getLayoutInflater().inflate(R.layout.home_griditem, null);
           ImageView grid_img= inflate.findViewById(R.id.grid_img);
           TextView guid_text = inflate.findViewById(R.id.guid_text);
           Glide.with(getContext()).load(RequstUrl.URL.HOST+zone_list.get(i).getZone_icon()).into(grid_img);
           guid_text.setText(zone_list.get(i).getZone_name());
           return inflate;
       }
   }

    public class CityAdapter extends  BaseAdapter{
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
            tv_city.setText(salary.get(i).getSalary_value()+"元");
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


    public void UpdateUI(){
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(mContext);
        }
        area_id="";
        age_id="";
        salary_id="";
        vocation_id="";
        environment_id="";
        progressDialog.show();
        SltAdapter();
        InitData();
    }
}

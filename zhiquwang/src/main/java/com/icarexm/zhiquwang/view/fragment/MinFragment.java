package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.bean.MineBean;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.ShadowDrawable;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.BaseInformationActivity;
import com.icarexm.zhiquwang.view.activity.BusinessCooperationActivity;
import com.icarexm.zhiquwang.view.activity.CertificationActivity;
import com.icarexm.zhiquwang.view.activity.DistributionTeamActivity;
import com.icarexm.zhiquwang.view.activity.EditPersonalActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.MessageActivity;
import com.icarexm.zhiquwang.view.activity.MyJobSearchActivity;
import com.icarexm.zhiquwang.view.activity.MyResumeActivity;
import com.icarexm.zhiquwang.view.activity.MyTeamActivity;
import com.icarexm.zhiquwang.view.activity.MyToJoinActivity;
import com.icarexm.zhiquwang.view.activity.MyWalletActivity;
import com.icarexm.zhiquwang.view.activity.SetActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinFragment extends Fragment implements View.OnClickListener {


    private Context mContext;
    private String token;
    private TextView tv_nickname;
    private TextView tv_address;
    private CircleImageView img_avatar;
    private double start_latitude;
    private double start_longitude;
    private String cityName;
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
                    district = aMapLocation.getDistrict();
                    //城市信息
                    cityName = aMapLocation.getCity();
                    tv_address.setText(cityName+district);
                    InitData();
                    SharedPreferences.Editor editor = share.edit();
                    editor.putString("cityName",cityName);
                    editor.putString("latitude", start_latitude +"");
                    editor.putString("longitude", start_longitude +"");
                    editor.commit();//提交
                }
            }
        }
    };
    AMapLocationClientOption option = new AMapLocationClientOption();
    private AMapLocationClientOption mLocationOption;
    private SharedPreferences share;
    private AMapLocationClient mLocationClient;
    private String district;
    private CustomProgressDialog progressDialog;

    public MinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        View inflate = inflater.inflate(R.layout.fragment_min, container, false);
        InitUI(inflate);
        InitData();
        startLocation();
        return inflate;
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

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.mine)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                        MineBean mineBean = new GsonBuilder().create().fromJson(response.body(), MineBean.class);
                        if (mineBean.getCode()==1){
                            MineBean.DataBean data = mineBean.getData();
                            tv_nickname.setText(data.getUsername());
                            Glide.with(mContext).load(RequstUrl.URL.HOST+data.getAvatar()).into(img_avatar);
                        }else if (mineBean.getCode() ==10001){
                            ToastUtils.showToast(mContext,mineBean.getMsg());
                            startActivity(new Intent(mContext, LoginActivity.class));
                            getActivity().finish();
                        }
                    }
                });
    }

    private void InitUI(View inflate) {
      inflate.findViewById(R.id.fm_min_rl_myjobsearch).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_myteam).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_img_edit).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_waller).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_set).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_resume).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_certification).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_to_join).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_businessCooperation).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_message).setOnClickListener(this);
        img_avatar = inflate.findViewById(R.id.fm_min_img_head);
        tv_nickname = inflate.findViewById(R.id.fm_min_tv_nickname);
        tv_address = inflate.findViewById(R.id.fm_min_tv_address);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_min_rl_myjobsearch:
                startActivity(new Intent(mContext, MyJobSearchActivity.class));
                break;
            case R.id.fm_min_rl_myteam:
                startActivity(new Intent(mContext, DistributionTeamActivity.class));
                break;
            case R.id.fm_min_img_edit:
                startActivity(new Intent(mContext, BaseInformationActivity.class));
                break;
            case R.id.fm_min_rl_waller:
                startActivity(new Intent(mContext, MyWalletActivity.class));
                break;
            case R.id.fm_min_rl_set:
                startActivity(new Intent(mContext, SetActivity.class));
                break;
            case R.id.fm_min_rl_resume:
                startActivity(new Intent(mContext, MyResumeActivity.class));
                break;
            case R.id.fm_min_rl_certification:
                startActivity(new Intent(mContext, CertificationActivity.class));
                break;
            case R.id.fm_min_rl_to_join:
                startActivity(new Intent(mContext, MyToJoinActivity.class));
                break;
            case R.id.fm_min_rl_businessCooperation:
                startActivity(new Intent(mContext, BusinessCooperationActivity.class));
                break;
            case R.id.fm_min_message:
                startActivity(new Intent(mContext, MessageActivity.class));
                break;
        }
    }

    public void UpdateUI(){
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(mContext);
        }
        progressDialog.show();
        InitData();
    }
}

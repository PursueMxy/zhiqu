package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
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
import com.icarexm.zhiquwang.bean.ChatListBean;
import com.icarexm.zhiquwang.bean.MineBean;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.ShadowDrawable;
import com.icarexm.zhiquwang.presenter.QueryWagesPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.BaseInformationActivity;
import com.icarexm.zhiquwang.view.activity.BusinessCooperationActivity;
import com.icarexm.zhiquwang.view.activity.CertificationActivity;
import com.icarexm.zhiquwang.view.activity.CustomerChatActivity;
import com.icarexm.zhiquwang.view.activity.DistributionTeamActivity;
import com.icarexm.zhiquwang.view.activity.EditPersonalActivity;
import com.icarexm.zhiquwang.view.activity.EntryEnterpriseActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.MessageActivity;
import com.icarexm.zhiquwang.view.activity.MessageListActivity;
import com.icarexm.zhiquwang.view.activity.MyJobSearchActivity;
import com.icarexm.zhiquwang.view.activity.MyResumeActivity;
import com.icarexm.zhiquwang.view.activity.MyTeamActivity;
import com.icarexm.zhiquwang.view.activity.MyToJoinActivity;
import com.icarexm.zhiquwang.view.activity.MyWalletActivity;
import com.icarexm.zhiquwang.view.activity.QueryWagesActivity;
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
    private String cityName="厦门市";
    private SharedPreferences share;
    private AMapLocationClient mLocationClient;
    private String district;
    private CustomProgressDialog progressDialog;
    private TextView tv_unread;

    public MinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_min, container, false);
        mContext = getContext();
        share = mContext.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        token = share.getString("token", "");
        cityName = share.getString("cityName", "");
        InitUI(inflate);
        LoadingDialogShow();
        InitData();
        return inflate;
    }




    private void InitData() {
        try {
            OkGo.<String>post(RequstUrl.URL.mine)
                    .params("token", token)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            LoadingDialogClose();
                            MineBean mineBean = new GsonBuilder().create().fromJson(response.body(), MineBean.class);
                            if (mineBean.getCode() == 1) {
                                MineBean.DataBean data = mineBean.getData();
                                tv_nickname.setText(data.getUsername());
                                Glide.with(mContext).load(RequstUrl.URL.HOST + data.getAvatar()).into(img_avatar);
                                if (data.getUnread_num() > 0) {
                                    tv_unread.setVisibility(View.VISIBLE);
                                    tv_unread.setText(data.getUnread_num() + "");
                                } else {
                                    tv_unread.setVisibility(View.GONE);
                                }
//                                timeHandler.postDelayed(timeRunnable, 50000);
                            } else if (mineBean.getCode() == 10001) {
                                try {
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                    getActivity().finish();
                                } catch (Exception e) {

                                }
                            }
                        }
                    });
        }catch (Exception e){}
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
      inflate.findViewById(R.id.fm_min_rl_entry_enterprise).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_query_wages).setOnClickListener(this);
      inflate.findViewById(R.id.fm_min_rl_customer_chat).setOnClickListener(this);
        img_avatar = inflate.findViewById(R.id.fm_min_img_head);
        tv_nickname = inflate.findViewById(R.id.fm_min_tv_nickname);
        tv_address = inflate.findViewById(R.id.fm_min_tv_address);
        tv_unread = inflate.findViewById(R.id.fm_min_tv_unread);
        if (!cityName.equals("")) {
            tv_address.setText(cityName);
        }else {
            cityName = share.getString("cityName", "");
            if (!cityName.equals("")) {
                tv_address.setText("厦门市");
            }else {
                tv_address.setText(cityName);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_min_rl_myjobsearch:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_myjobsearch)) {
                    startActivity(new Intent(mContext, MyJobSearchActivity.class));
                }
                break;
            case R.id.fm_min_rl_myteam:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_myteam)) {
                    startActivity(new Intent(mContext, DistributionTeamActivity.class));
                }
                break;
            case R.id.fm_min_img_edit:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_img_edit)) {
                    startActivity(new Intent(mContext, BaseInformationActivity.class));
                }
                break;
            case R.id.fm_min_rl_waller:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_waller)) {
                    startActivity(new Intent(mContext, MyWalletActivity.class));
                }
                break;
            case R.id.fm_min_rl_set:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_set)) {
                    startActivity(new Intent(mContext, SetActivity.class));
                }
                break;
            case R.id.fm_min_rl_resume:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_resume)) {
                    startActivity(new Intent(mContext, MyResumeActivity.class));
                }
                break;
            case R.id.fm_min_rl_certification:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_certification)) {
                    startActivity(new Intent(mContext, CertificationActivity.class));
                }
                break;
            case R.id.fm_min_rl_to_join:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_to_join)) {
                    startActivity(new Intent(mContext, MyToJoinActivity.class));
                }
                break;
            case R.id.fm_min_rl_businessCooperation:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_businessCooperation)) {
                    startActivity(new Intent(mContext, BusinessCooperationActivity.class));
                }
                break;
            case R.id.fm_min_message:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_message)) {
                    startActivity(new Intent(mContext, MessageActivity.class));
                }
                break;
            case R.id.fm_min_rl_entry_enterprise:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_message)) {
                    startActivity(new Intent(mContext, EntryEnterpriseActivity.class));
                }
                break;
            case R.id.fm_min_rl_query_wages:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_query_wages)){
                    startActivity(new Intent(mContext, QueryWagesActivity.class));
                }
                break;
            case R.id.fm_min_rl_customer_chat:
                if (!ButtonUtils.isFastDoubleClick(R.id.fm_min_rl_customer_chat)){
                    startActivity(new Intent(mContext, MessageListActivity.class));
                }
                break;
        }
    }

    public void UpdateUI(){
        InitData();
    }


    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(mContext);
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

    //防止多次点击获取验证码
    Handler timeHandler=new Handler();
    Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
            InitData();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeHandler.removeCallbacks(timeRunnable);
    }
}

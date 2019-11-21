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

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.ShadowDrawable;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.view.activity.BaseInformationActivity;
import com.icarexm.zhiquwang.view.activity.BusinessCooperationActivity;
import com.icarexm.zhiquwang.view.activity.CertificationActivity;
import com.icarexm.zhiquwang.view.activity.DistributionTeamActivity;
import com.icarexm.zhiquwang.view.activity.EditPersonalActivity;
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

    public MinFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        SharedPreferences share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
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
        return inflate;
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.BasicsInfo)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        BaseInforBean baseInforBean = new GsonBuilder().create().fromJson(response.body(), BaseInforBean.class);
                        if (baseInforBean.getCode()==1){
                            BaseInforBean.DataBean data = baseInforBean.getData();
//                            tv_address.setText();
                            tv_nickname.setText(data.getUser_name());
                            Glide.with(mContext).load(data.getAvatar()).into(img_avatar);
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
        }
    }
}

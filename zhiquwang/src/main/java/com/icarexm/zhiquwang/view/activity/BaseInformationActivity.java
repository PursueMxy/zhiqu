package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BaseInforBean;
import com.icarexm.zhiquwang.contract.BaseInformationContract;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.presenter.BaseInformationPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaseInformationActivity extends AppCompatActivity implements BaseInformationContract.View {

    @BindView(R.id.base_information_img_avatar)
    CircleImageView img_acatar;
    @BindView(R.id.base_information_tv_name)
    TextView tv_name;
    @BindView(R.id.base_information_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.base_information_tv_is_auth)
    TextView tv_is_auth;

    private BaseInformationPresenter baseInformationPresenter;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_information);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        baseInformationPresenter = new BaseInformationPresenter(this);
        baseInformationPresenter.GetBaseInfor(token);
    }
    @OnClick({R.id.base_information_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.base_information_img_back:
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

    public void UpdateUI(int code, String msg, BaseInforBean.DataBean data){
        if (code==1){
            tv_is_auth.setText(data.getIs_auth());
            tv_mobile.setText(data.getMobile());
            tv_name.setText(data.getUser_name());
            Glide.with(mContext).load(data.getAvatar()).into(img_acatar);
        }
    }
}

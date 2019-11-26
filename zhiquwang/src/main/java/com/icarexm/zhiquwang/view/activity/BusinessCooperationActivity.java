package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.BusinessCooperationContract;
import com.icarexm.zhiquwang.presenter.BusinessCooperationPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessCooperationActivity extends BaseActivity implements BusinessCooperationContract.View {

    @BindView(R.id.business_cooperation_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.business_cooperation_edt_company)
    EditText edt_company;
    @BindView(R.id.business_cooperation_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.business_cooperation_edt_need)
    EditText edt_need;
    private BusinessCooperationPresenter businessCooperationPresenter;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_cooperation);
        mContext = getApplicationContext();
        SharedPreferences share  =getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        businessCooperationPresenter = new BusinessCooperationPresenter(this);
        businessCooperationPresenter.GetCooperation(token);
    }

    @OnClick({R.id.business_cooperation_img_back,R.id.business_cooperation_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.business_cooperation_img_back:
                finish();
                break;
            case R.id.business_cooperation_btn_confirm:
                String company = edt_company.getText().toString();
                String mobile = edt_mobile.getText().toString();
                String need= edt_need.getText().toString();
                if (!company.equals("")){
                    if (!mobile.equals("")){
                        if (!need.equals("")){
                           businessCooperationPresenter.GetDoCooperation(token,company,mobile,need);
                        }else {
                            ToastUtils.showToast(mContext,"合作需求不能为空");
                        }

                    }else {
                        ToastUtils.showToast(mContext,"联系人不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"公司名称不能为空");
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

    public void UpdateUI(int code,String msg,String mobile){
        if (code==1) {
            tv_mobile.setText(mobile);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }
    public void UpdateUI(int code,String msg){
        if (code==1){
            ToastUtils.showToast(mContext,msg);
            finish();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}

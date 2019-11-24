package com.icarexm.zhiquwang.view.activity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.MyToJoinContract;
import com.icarexm.zhiquwang.presenter.MyToJoinPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyToJoinActivity extends BaseActivity implements MyToJoinContract.View {
     @BindView(R.id.my_to_join_ll_TypeOne)
    LinearLayout ll_typeOne;
     @BindView(R.id.my_to_join_rl_TypeTwo)
    RelativeLayout rl_typeTwo;
     @BindView(R.id.my_to_join_rl_TypeThree)
     RelativeLayout rl_typeThree;
     @BindView(R.id.my_to_join_edt_name)
     EditText edt_name;
     @BindView(R.id.my_to_join_edt_mobile)
     EditText edt_mobile;
     @BindView(R.id.my_to_join_edt_area)
     EditText edt_area;

     private int Type=1;
    private Context mContext;
    private MyToJoinPresenter myToJoinPresenter;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_join);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        InitUI();
        myToJoinPresenter = new MyToJoinPresenter(this);
    }

    private void InitUI() {
        if (Type==1){
            rl_typeThree.setVisibility(View.GONE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.VISIBLE);
        }else if (Type==2){
            rl_typeTwo.setVisibility(View.VISIBLE);
            rl_typeThree.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }else if (Type==3){
            rl_typeThree.setVisibility(View.VISIBLE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.my_to_join_img_back,R.id.my_to_join_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_to_join_img_back:
                finish();
                break;
            case R.id.my_to_join_btn_confirm:
                String name = edt_name.getText().toString();
                String mobile= edt_mobile.getText().toString();
                String area = edt_area.getText().toString();
                if (!name.equals("")){
                 if (mobile.equals("")){
                   if (!area.equals("")){
                       myToJoinPresenter.GetMyTojoin(token,name,mobile,area);
                   }else {
                       ToastUtils.showToast(mContext,"申请区域不能为空");
                   }
                 }else {
                     ToastUtils.showToast(mContext,"申请人电话不能为空");
                 }
                }else {
                    ToastUtils.showToast(mContext,"申请人不能为空");
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
}

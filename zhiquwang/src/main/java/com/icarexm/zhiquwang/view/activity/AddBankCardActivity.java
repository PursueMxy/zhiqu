package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.contract.AddBankCardContract;
import com.icarexm.zhiquwang.presenter.AddBankCardPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBankCardActivity extends BaseActivity implements AddBankCardContract.View {
     @BindView(R.id.add_bank_card_edt_bank_user)
     EditText edt_bank_user;
    @BindView(R.id.add_bank_card_edt_bank_name)
    EditText edt_bank_name;
    @BindView(R.id.add_bank_card_edt_bank_num)
    EditText edt_bank_num;
    private AddBankCardPresenter addBankCardPresenter;
    private Context mContext;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        addBankCardPresenter = new AddBankCardPresenter(this);
    }
    @OnClick({R.id.add_bank_card_img_back,R.id.add_bank_card_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_bank_card_img_back:
                finish();
                break;
            case R.id.add_bank_card_btn_confirm:
                String bank_name = edt_bank_name.getText().toString();
                String bank_num= edt_bank_num.getText().toString();
                String bank_user= edt_bank_user.getText().toString();
                if (!bank_user.equals("")){
                   if (!bank_num.equals("")){
                        if (!bank_name.equals("")){
                        addBankCardPresenter.GetAddCard(token,bank_user,bank_num,bank_name);
                        }else {
                            ToastUtils.showToast(mContext,"银行名称不能为空");
                        }
                   }else {
                       ToastUtils.showToast(mContext,"卡号不能为空");
                   }
                }else {
                    ToastUtils.showToast(mContext,"持卡人不能为空");
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

    public void UpdateUI(int code,String msg){
        if (code==1){
          finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}

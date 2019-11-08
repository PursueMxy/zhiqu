package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashWithdrawalActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.cash_withdrawal_tv_add_bank_card,R.id.cash_withdrawal_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cash_withdrawal_tv_add_bank_card:
                startActivity(new Intent(mContext,WithdrawalDtlActivity.class));
                break;
            case R.id.cash_withdrawal_img_back:
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
}

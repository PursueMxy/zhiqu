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

public class MyWalletActivity extends BaseActivity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
    }
    @OnClick({R.id.my_wallet_btn_cashwithdrawal,R.id.my_wallet_rl_entry_award,R.id.my_wallet_rl_withdrawal_dtl,R.id.my_wallet_rl_invite_award})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_wallet_btn_cashwithdrawal:
                startActivity(new Intent(mContext,CashWithdrawalActivity.class));
                break;
            case R.id.my_wallet_rl_entry_award:
                startActivity(new Intent(mContext,EntryAwardActivity.class));
                break;
            case R.id.my_wallet_rl_withdrawal_dtl:
                startActivity(new Intent(mContext,WithdrawalDtlActivity.class));
                break;
            case R.id.my_wallet_rl_invite_award:
                startActivity(new Intent(mContext,InviteAwardActivity.class));
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

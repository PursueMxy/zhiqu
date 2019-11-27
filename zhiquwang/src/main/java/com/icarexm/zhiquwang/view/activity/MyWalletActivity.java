package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.WalletBean;
import com.icarexm.zhiquwang.contract.MyWalletContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.MyWalletPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyWalletActivity extends BaseActivity implements MyWalletContract.View {
    @BindView(R.id.my_wallet_tv_price)
    TextView tv_price;
    private Context mContext;
    private MyWalletPresenter myWalletPresenter;
    private String token;
    private String withdrawal;
    private String balance;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        myWalletPresenter = new MyWalletPresenter(this);
        myWalletPresenter.GetMyWallet(token);

    }
    @OnClick({R.id.my_wallet_btn_cashwithdrawal,R.id.my_wallet_rl_entry_award,R.id.my_wallet_rl_withdrawal_dtl,R.id.my_wallet_rl_invite_award})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_wallet_btn_cashwithdrawal:
                Intent intent1 = new Intent(mContext, CashWithdrawalActivity.class);
                intent1.putExtra("withdrawal",withdrawal);
                intent1.putExtra("balance",balance);
                startActivity(intent1);
                break;
            case R.id.my_wallet_rl_entry_award:
                startActivity(new Intent(mContext,EntryAwardActivity.class));
                break;
            case R.id.my_wallet_rl_withdrawal_dtl:
                Intent intent = new Intent(mContext, WithdrawalDtlActivity.class);
                startActivity(intent);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String msg, WalletBean.DataBean data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1) {
            tv_price.setText(data.getBalance() + "元");
            withdrawal = data.getWithdrawal();
            balance = data.getBalance();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }
}

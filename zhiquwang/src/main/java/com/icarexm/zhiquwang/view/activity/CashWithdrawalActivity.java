package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.MyBankBean;
import com.icarexm.zhiquwang.contract.CashWithdrawalContract;
import com.icarexm.zhiquwang.presenter.CashWithdrawalPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CashWithdrawalActivity extends BaseActivity implements CashWithdrawalContract.View {

    @BindView(R.id.cash_withdrawal_rl_bank)
    RelativeLayout rl_bank;
    @BindView(R.id.cash_withdrawal_rl_nobank)
    RelativeLayout rl_nobank;
    @BindView(R.id.cash_withdrawal_tv_bank_name)
    TextView tv_bank_name;
    @BindView(R.id.cash_withdrawal_tv_bank_num)
    TextView tv_bank_num;
    @BindView(R.id.cash_withdrawal_tv_withdrawal)
    TextView tv_withdrawal;
    @BindView(R.id.cash_withdrawal_tv_cash_money)
    EditText edt_cash_money;

    private Context mContext;
    private String token;
    private CashWithdrawalPresenter cashWithdrawalPresenter;
    List<MyBankBean.DataBean> banklist=new ArrayList<>();
    private String withdrawal;
    private int bank_id=0;
    private String balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        Intent intent = getIntent();
        withdrawal = intent.getStringExtra("withdrawal");
        balance = intent.getStringExtra("balance");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        cashWithdrawalPresenter = new CashWithdrawalPresenter(this);
        cashWithdrawalPresenter.GetWithdrawal(token);
        tv_withdrawal.setText("(最少提现"+withdrawal+"元)");
    }
    @OnClick({R.id.cash_withdrawal_tv_add_bank_card,R.id.cash_withdrawal_img_back,R.id.cash_withdrawal_btn_cash})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.cash_withdrawal_tv_add_bank_card:
                startActivity(new Intent(mContext,AddBankCardActivity.class));
                break;
            case R.id.cash_withdrawal_img_back:
                finish();
                break;
            case R.id.cash_withdrawal_btn_cash:
                String cash_money= edt_cash_money.getText().toString();
                double money= Double.parseDouble(cash_money);
                double withdrawal_money = Double.parseDouble(withdrawal);
                double balance_money = Double.parseDouble(balance);
                Log.e("金额",money+"和"+withdrawal_money);
                if (money>withdrawal_money||money==withdrawal_money){
                   if (money<balance_money){
                       cashWithdrawalPresenter.GetdoWithdrawal(token,cash_money,bank_id+"");
                   }else {
                       ToastUtils.showToast(mContext,"不能大于可提现金额");
                   }
                }else {
                    ToastUtils.showToast(mContext,"不能小于最低提现金额");
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

    public void BankList(int code, String msg, List<MyBankBean.DataBean> data){
        if (code==1){
            banklist=data;
            if (banklist.size()>0){
             rl_nobank.setVisibility(View.GONE);
             rl_bank.setVisibility(View.VISIBLE);
             tv_bank_name.setText(banklist.get(0).getBank_name());
             tv_bank_num.setText("账号："+banklist.get(0).getBank_num());
             bank_id = banklist.get(0).getBank_id();
            }
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }

    public void UpdateUI(int code,String msg){
        if (code==1){
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}

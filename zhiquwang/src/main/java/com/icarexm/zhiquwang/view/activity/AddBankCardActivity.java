package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.contract.AddBankCardContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.AddBankCardPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBankCardActivity extends BaseActivity implements AddBankCardContract.View {
     @BindView(R.id.add_bank_card_edt_bank_user)
     EditText edt_bank_user;
    @BindView(R.id.add_bank_card_edt_bank_name)
    TextView edt_bank_name;
    @BindView(R.id.add_bank_card_edt_bank_num)
    EditText edt_bank_num;
    private AddBankCardPresenter addBankCardPresenter;
    private Context mContext;
    private String token;
    private String type;
    private int ADDBANK=20001;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        addBankCardPresenter = new AddBankCardPresenter(this);

    }

    private void InitUI() {
        edt_bank_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length()>12) {
                    GetBank(charSequence.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void GetBank(String bankNum){
        OkGo.<String>post(RequstUrl.URL.getBank)
                .params("card",bankNum)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(response.body(), PublicCodeBean.class);
                        if (publicCodeBean.getCode()==1){
                            edt_bank_name.setText(publicCodeBean.getMsg());
                        }else {
                            edt_bank_name.setText("");
                        }
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @OnClick({R.id.add_bank_card_img_back,R.id.add_bank_card_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.add_bank_card_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.add_bank_card_img_back)) {
                    finish();
                }
                break;
            case R.id.add_bank_card_btn_confirm:
                if (!ButtonUtils.isFastDoubleClick(R.id.add_bank_card_btn_confirm)) {
                    String bank_name = edt_bank_name.getText().toString();
                    String bank_num = edt_bank_num.getText().toString();
                    String bank_user = edt_bank_user.getText().toString();
                    if (!bank_user.equals("")) {
                        if (!bank_num.equals("")) {
                            if (!bank_name.equals("")) {
                                addBankCardPresenter.GetAddCard(token, bank_user, bank_num, bank_name);
                            } else {
                                ToastUtils.showToast(mContext, "银行名称不能为空");
                            }
                        } else {
                            ToastUtils.showToast(mContext, "卡号不能为空");
                        }
                    } else {
                        ToastUtils.showToast(mContext, "持卡人不能为空");
                    }
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
            if (type.equals("20001")){
                Intent intent = new Intent(mContext, BankListActivity.class);
                setResult(ADDBANK,intent);
                finish();
            }else {
              finish();
            }
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
        else {
            ToastUtils.showToast(mContext,msg);
        }
    }

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(this);
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
}

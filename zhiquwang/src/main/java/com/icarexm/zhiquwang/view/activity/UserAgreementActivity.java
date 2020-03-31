package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAgreementActivity extends BaseActivity {

    @BindView(R.id.user_agreement_tv_content)
    TextView tv_content;
    private Context mContext;
    private int AGREMEEN_CODE=1001;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        LoadingDialogShow();
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.getProtocol)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                      LoadingDialogClose();
                        PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                        if (resultBean.getCode()==1){
                            tv_content.setText( Html.fromHtml(resultBean.getData()));
                        }else if (resultBean.getCode() ==10001){
                            ToastUtils.showToast(mContext,resultBean.getMsg());
                            startActivity(new Intent(mContext,LoginActivity.class));
                            finish();
                        }
                    }
                });
    }

    @OnClick({R.id.user_agreement_img_back,R.id.user_agreement_agree})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.user_agreement_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.user_agreement_img_back)) {
                    finish();
                }
                break;
            case R.id.user_agreement_agree:
                if (!ButtonUtils.isFastDoubleClick(R.id.user_agreement_agree)) {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    setResult(AGREMEEN_CODE, intent);
                    finish();
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

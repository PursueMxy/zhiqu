package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    @BindView(R.id.about_tv_versionName)
    TextView tv_versionName;
    @BindView(R.id.about_tv_content)
    TextView tv_content;
    private Context mContext;
    private String appVersionName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        appVersionName = MxyUtils.getAppVersionName(mContext);
        tv_versionName.setText(appVersionName);
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.About)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                        if (resultBean.getCode()==1){
                            tv_content.setText( Html.fromHtml(resultBean.getData()));
                        }
                    }
                });
    }

    @OnClick({R.id.about_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.about_img_back:
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

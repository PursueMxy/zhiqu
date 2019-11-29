package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ContactUsBean;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends BaseActivity {

    @BindView(R.id.contact_us_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.contact_us_tv_content)
    TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ButterKnife.bind(this);
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.getContact)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        ContactUsBean contactUsBean = new GsonBuilder().create().fromJson(response.body(), ContactUsBean.class);
                        if (contactUsBean.getCode()==1){
                            ContactUsBean.DataBean data = contactUsBean.getData();
                            tv_mobile.setText(data.getMobile());
                            tv_content.setText(Html.fromHtml(data.getContact_us()));
                        }
                    }
                });
    }

    @OnClick({R.id.contact_us_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.contact_us_img_back:
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

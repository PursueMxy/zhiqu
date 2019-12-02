package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ContactUsBean;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends BaseActivity {

    @BindView(R.id.contact_us_tv_mobile)
    TextView tv_mobile;
    @BindView(R.id.contact_us_tv_content)
    TextView tv_content;
    @BindView(R.id.contact_us_address)
    TextView tv_address;
    private String latitude;
    private String longitude;
    private Context mContext;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        mContext = getApplicationContext();
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
                            tv_address.setText(data.getAddress());
                            latitude = data.getLatitude();
                            longitude = data.getLongitude();
                            address = data.getAddress();
                        }
                    }
                });
    }

    @OnClick({R.id.contact_us_img_back,R.id.contact_us_address})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.contact_us_img_back:
                finish();
                break;
            case R.id.contact_us_address:
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

    //调用地图导航
    public void startNaviGao() {
        if (MxyUtils.isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                //sourceApplication
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication="+"&poiname="+address+"&lat=" + latitude + "&lon=" + longitude + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                ToastUtils.showToast(mContext,"您尚未安装百度地图或地图版本过低");
            }
        } else if (MxyUtils.isAvilible(this, "com.baidu.BaiduMap")) {//传入指定应用包名
            try {
                Intent intent = Intent.getIntent("intent://map/direction?" +
                        "destination=latlng:" + latitude + "," + longitude + "|name:"+address+
                        "&mode=driving&" +     //导航路线方式
                        "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                startActivity(intent); //启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
                ToastUtils.showToast(mContext,"您尚未安装百度地图或地图版本过低");
            }
        }
        else {
            ToastUtils.showToast(mContext,"您尚未安装地图或地图版本过低");
        }
    }
}

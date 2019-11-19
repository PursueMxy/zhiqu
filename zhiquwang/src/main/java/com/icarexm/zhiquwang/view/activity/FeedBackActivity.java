package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends BaseActivity {
      @BindView(R.id.feedback_tv_textNumber)
      TextView tv_textNumber;
      @BindView(R.id.feedback_tv_content)
      EditText edt_content;
    private Context mContext;
    private SharedPreferences share;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        edt_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符即将被长度为after的新文本所取代。
                // 在这个方法里面改变s，会报错。
                tv_textNumber.setText((start+1)+"");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符刚刚取代了长度为before的旧文本。
                // 在这个方法里面改变s，会报错。
//                tv_textNumber.setText(""+start);
            }
            @Override
            public void afterTextChanged(Editable s) {
                //这个方法被调用，那么说明s字符串的某个地方已经被改变。
            }
        });
    }

    @OnClick({R.id.feedback_img_back,R.id.feedback_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.feedback_img_back:
                finish();
                break;
            case R.id.feedback_btn_confirm:
                String content = edt_content.getText().toString();
                if (!content.equals("")){
                    OkGo.<String>post(RequstUrl.URL.SbmitIdea)
                            .params("token", token)
                            .params("content",content)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                    if (resultBean.getCode()==1){
                                        finish();
                                    }else {
                                        ToastUtils.showToast(mContext,resultBean.getMsg());
                                    }
                                }
                            });
                }else {
                    ToastUtils.showToast(mContext,"请输入反馈内容");
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
}

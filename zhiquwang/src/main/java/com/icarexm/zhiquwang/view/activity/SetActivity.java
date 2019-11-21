package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.ClearCacheManager;
import com.icarexm.zhiquwang.utils.SysUtil;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {


    @BindView(R.id.set_tv_clean_cache)
    TextView tv_clean_cache;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        try {
            String cache = ClearCacheManager.getTotalCacheSize(SetActivity.this);
            if(SysUtil.isNotNUll(cache)){
                tv_clean_cache.setText(cache);
            }else {
                tv_clean_cache.setText("0.0B");
            }

        } catch (Exception e) {

        }
    }

    @OnClick({R.id.set_img_back,R.id.set_rl_clean_cache,R.id.set_rl_about,R.id.set_rl_change_password,
            R.id.set_rl_contact_us,R.id.set_rl_feedback,R.id.set_rl_messageset,R.id.set_btn_out_login})
    public  void onViewClick(View view){
        switch (view.getId()) {
            case R.id.set_img_back:
                finish();
                break;
            case R.id.set_rl_clean_cache:
                ClearCacheManager.clearAllCache(SetActivity.this);
                ToastUtils.showToast(mContext, "清除成功");
                tv_clean_cache.setText("0.0B");
                break;
            case R.id.set_rl_change_password:
                startActivity(new Intent(mContext, ChangePasswordActivity.class));
                break;
            case R.id.set_rl_contact_us:
                startActivity(new Intent(mContext, ContactUsActivity.class));
                break;
            case R.id.set_rl_feedback:
                startActivity(new Intent(mContext, FeedBackActivity.class));
                break;
            case R.id.set_rl_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            case R.id.set_rl_messageset:
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                startActivity(intent);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.BASE) {
                    // 进入设置系统应用权限界面
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                    return;
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 运行系统在5.x环境使用
                    // 进入设置系统应用权限界面
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    startActivity(intent);
                    return;
                }
                break;
            case R.id.set_btn_out_login:
                startActivity(new Intent(mContext,LoginActivity.class));
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

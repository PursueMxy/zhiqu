package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.icarexm.zhiquwang.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DistributionPosterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_poster);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.distribution_poster_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.distribution_poster_img_back:
                finish();
                break;
        }
    }
}

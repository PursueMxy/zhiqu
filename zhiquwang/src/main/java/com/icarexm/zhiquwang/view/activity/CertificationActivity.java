package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.icarexm.zhiquwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificationActivity extends BaseActivity {

   @BindView(R.id.certification_rl_TypeOne)
   LinearLayout rl_TypeOne;
   @BindView(R.id.certification_rl_TypeTwo)
   RelativeLayout rl_TypeTwo;

   private int Type=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certification);
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
      if (Type==1){
          rl_TypeOne.setVisibility(View.VISIBLE);
          rl_TypeTwo.setVisibility(View.GONE);
      }else {
          rl_TypeTwo.setVisibility(View.VISIBLE);
          rl_TypeOne.setVisibility(View.GONE);
      }
    }
}

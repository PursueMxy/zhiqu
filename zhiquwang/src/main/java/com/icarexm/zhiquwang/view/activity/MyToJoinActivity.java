package com.icarexm.zhiquwang.view.activity;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.icarexm.zhiquwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyToJoinActivity extends BaseActivity {
     @BindView(R.id.my_to_join_ll_TypeOne)
    LinearLayout ll_typeOne;
     @BindView(R.id.my_to_join_rl_TypeTwo)
    RelativeLayout rl_typeTwo;
     @BindView(R.id.my_to_join_rl_TypeThree)
     RelativeLayout rl_typeThree;

     private int Type=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_join);
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        if (Type==1){
            rl_typeThree.setVisibility(View.GONE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.VISIBLE);
        }else if (Type==2){
            rl_typeTwo.setVisibility(View.VISIBLE);
            rl_typeThree.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }else if (Type==3){
            rl_typeThree.setVisibility(View.VISIBLE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.my_to_join_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_to_join_img_back:
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

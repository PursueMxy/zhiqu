package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MyResumeAdapter;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyResumeActivity extends BaseActivity {

    private Context mContext;
    @BindView(R.id.my_resume_recyclerView)
    RecyclerView mRecyclerView;
    private int  ADDWORKHISTORY=5120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }



    private void InitUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(layoutManager);
        MyResumeAdapter myResumeAdapter = new MyResumeAdapter(mContext);
        mRecyclerView.setAdapter(myResumeAdapter);
    }

    @OnClick({R.id.my_resume_img_edit,R.id.my_resume_add_workHistory,R.id.my_resume_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_resume_img_edit:
                startActivity(new Intent(mContext,UpdateBaseInforActivity.class));
                break;
            case R.id.my_resume_img_back:
                finish();
                break;
            case R.id.my_resume_add_workHistory:
                Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                startActivityForResult(intent,ADDWORKHISTORY);
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

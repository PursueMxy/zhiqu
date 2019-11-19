package com.icarexm.zhiquwang.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.CommissionAdapter;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionActivity extends BaseActivity {

     @BindView(R.id.commission_recyclerView)
    XRecyclerView mRecyclerView;
    private List<SeeFundBean.DataBeanX.ListBean.DataBean> dataList=new ArrayList<>();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);
        mContext=getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }
    @OnClick({R.id.commission_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.commission_img_back:
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

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        CommissionAdapter commissionAdapter = new CommissionAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }
        });
        mRecyclerView.setAdapter(commissionAdapter);
        commissionAdapter.setListAll(dataList);
    }
}

package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.CommissionAdapter;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.EntryAwardContract;
import com.icarexm.zhiquwang.presenter.EntryAwardPresenter;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryAwardActivity extends BaseActivity implements EntryAwardContract.View {
    @BindView(R.id.entry_award_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.entry_award_tv_money)
    TextView tv_money;
    private List<SeeFundBean.DataBeanX.ListBean.DataBean> dataList=new ArrayList<>();
    private Context mContext;
    private String token;
    private EntryAwardPresenter EntryAwardPresenter;
    private CommissionAdapter commissionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_award);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        EntryAwardPresenter = new EntryAwardPresenter(this);
        EntryAwardPresenter.GetSeeFund(token,"2");
    }

    @OnClick({R.id.entry_award_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.entry_award_img_back:
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
        commissionAdapter = new CommissionAdapter(mContext);
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



    public void Update(int code, String msg, SeeFundBean.DataBeanX data){
        SeeFundBean.DataBeanX.ListBean list = data.getList();
        dataList = list.getData();
        commissionAdapter.setListAll(dataList);
        commissionAdapter.notifyDataSetChanged();
        String total_price = data.getTotal_price();
        tv_money.setText(total_price);
    }
}

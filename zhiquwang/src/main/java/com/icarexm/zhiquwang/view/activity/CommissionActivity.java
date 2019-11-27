package com.icarexm.zhiquwang.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.CommissionAdapter;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.CommissionContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.CommissionPresenter;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommissionActivity extends BaseActivity implements CommissionContract.View {

     @BindView(R.id.commission_recyclerView)
    XRecyclerView mRecyclerView;
     @BindView(R.id.commission_tv_money)
    TextView tv_money;
    private List<SeeFundBean.DataBeanX.ListBean.DataBean> dataList=new ArrayList<>();
    private Context mContext;
    private CommissionAdapter commissionAdapter;
    private CommissionPresenter commissionPresenter;
    private String token;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commission);
        mContext=getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        commissionPresenter = new CommissionPresenter(this);
        commissionPresenter.GetSeeFund(token,"2");
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
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1) {
            SeeFundBean.DataBeanX.ListBean list = data.getList();
            dataList = list.getData();
            commissionAdapter.setListAll(dataList);
            commissionAdapter.notifyDataSetChanged();
            String total_price = data.getTotal_price();
            tv_money.setText(total_price);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

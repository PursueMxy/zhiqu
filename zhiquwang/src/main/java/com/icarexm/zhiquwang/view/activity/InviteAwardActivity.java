package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.CommissionAdapter;
import com.icarexm.zhiquwang.bean.SeeFundBean;
import com.icarexm.zhiquwang.contract.InviteAwardContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.InviteAwardPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteAwardActivity extends BaseActivity implements InviteAwardContract.View {
    @BindView(R.id.invite_award_recyclerView)
    XRecyclerView mRecyclerView;
    @BindView(R.id.invite_award_tv_money)
    TextView tv_money;
    private List<SeeFundBean.DataBeanX.ListBean.DataBean> dataList=new ArrayList<>();
    private Context mContext;
    private CommissionAdapter commissionAdapter;
    private InviteAwardPresenter inviteAwardPresenter;
    private String token;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_award);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        LoadingDialogShow();
        InitUI();
        inviteAwardPresenter = new InviteAwardPresenter(this);
        inviteAwardPresenter.GetSeeFund(token,"4");
    }

    @OnClick({R.id.invite_award_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.invite_award_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.invite_award_img_back)) {
                    finish();
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

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        commissionAdapter = new CommissionAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                inviteAwardPresenter.GetSeeFund(token,"4");
                //加载更多
                mRecyclerView.refreshComplete();//刷新动画完成
            }
            @Override
            public void onLoadMore() {
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.setNoMore(true);
            }
        });
        mRecyclerView.setAdapter(commissionAdapter);
        commissionAdapter.setListAll(dataList);
    }

    public void Update(int code, String msg, SeeFundBean.DataBeanX data){
       LoadingDialogClose();
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

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(this);
            }
            progressDialog.show();
        }catch (Exception e){

        }
    }

    //关闭刷新
    public void LoadingDialogClose(){
        try {
            if (progressDialog != null){
                progressDialog.dismiss();
                progressDialog = null;
            }
        }catch (Exception e){

        }

    }
}

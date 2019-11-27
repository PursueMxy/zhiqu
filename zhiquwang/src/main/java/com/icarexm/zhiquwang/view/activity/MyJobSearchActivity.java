package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MyJobSearchAdapter;
import com.icarexm.zhiquwang.bean.JobSearchBean;
import com.icarexm.zhiquwang.contract.MyJobSearchContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.MyJobSearchPresenter;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJobSearchActivity extends BaseActivity implements MyJobSearchContract.View {

    @BindView(R.id.my_job_search_recyclerView)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private MyJobSearchPresenter myJobSearchPresenter;
    private String token;
    private String limit="20";
    private int page=0;
    private List<JobSearchBean.DataBeanX.DataBean> dataList=new ArrayList<>();
    private MyJobSearchAdapter myJobSearchAdapter;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job_search);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        myJobSearchPresenter = new MyJobSearchPresenter(this);
        myJobSearchPresenter.GetMyJob(token,limit,page+"");
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        myJobSearchAdapter = new MyJobSearchAdapter(mContext);
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
        mRecyclerView.setAdapter(myJobSearchAdapter);
        myJobSearchAdapter.setListAll(dataList);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10)));
            }
        });
    }

    @OnClick({R.id.myjobsearch_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.myjobsearch_img_back:
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String msg, JobSearchBean.DataBeanX data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code==1){
            dataList = data.getData();
            myJobSearchAdapter.setListAll(dataList);
            myJobSearchAdapter.notifyDataSetChanged();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }
}

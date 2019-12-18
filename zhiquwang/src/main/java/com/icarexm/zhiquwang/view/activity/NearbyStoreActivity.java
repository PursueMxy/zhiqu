package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.NearbyStoreAdapter;
import com.icarexm.zhiquwang.bean.NearbyStoreBean;
import com.icarexm.zhiquwang.contract.NearbyStoreContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.NearbyStorePresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NearbyStoreActivity extends BaseActivity implements NearbyStoreContract.View {
    @BindView(R.id.nearby_story_recyclerView)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private List<NearbyStoreBean.DataBeanX.DataBean> list=new ArrayList<>();
    private String token;
    private String latitude;
    private String longitude;
    private NearbyStorePresenter nearbyStorePresenter;
    private String 	limit="30";
    private int page=1;
    private NearbyStoreAdapter nearbyStoreAdapter;
    private String job_id;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_store);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        job_id = intent.getStringExtra("job_id");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        latitude = share.getString("latitude", "");
        longitude = share.getString("longitude", "");
        nearbyStorePresenter = new NearbyStorePresenter(this);
        InitUI();
        LoadingDialogShow();
        nearbyStorePresenter.GetNearbyStore(token,longitude,latitude,limit,page+"");
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        nearbyStoreAdapter = new NearbyStoreAdapter(this,job_id);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        nearbyStoreAdapter.setListAll(list);
        mRecyclerView.setAdapter(nearbyStoreAdapter);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                nearbyStorePresenter.GetNearbyStore(token,longitude,latitude,limit,page+"");
            }

            @Override
            public void onLoadMore() {
                page=page+1;
                nearbyStorePresenter.GetNearbyStore(token,longitude,latitude,limit,page+"");
            }
        });
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 0
                        , 0
                        , 0);
            }
        });
        nearbyStoreAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
//                startActivity(new Intent(mContext, RecruitDetailActivity.class));
            }
        });
    }

    @OnClick({R.id.nearby_story_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.nearby_story_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.nearby_story_img_back)) {
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String msg, NearbyStoreBean.DataBeanX data){
     LoadingDialogClose();
        if (code==1){
            if (page==1) {
                list = data.getData();
                nearbyStoreAdapter.setListAll(list);
                nearbyStoreAdapter.notifyDataSetChanged();
                mRecyclerView.refreshComplete();//加载动画完成
            }else {
                if (data.getData()!=null){
                    if (data.getData().size()>0){
                        list.addAll(data.getData());
                        nearbyStoreAdapter.addItemsToLast(data.getData());
                        nearbyStoreAdapter.notifyDataSetChanged();
                        mRecyclerView.loadMoreComplete();//加载动画完成
                    }else {
                        page=page-1;
                        //加载更多
                        mRecyclerView.loadMoreComplete();//加载动画完成
                        mRecyclerView.setNoMore(true);
                    }
                }
            }
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
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

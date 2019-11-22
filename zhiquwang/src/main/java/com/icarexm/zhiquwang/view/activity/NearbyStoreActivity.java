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
import com.icarexm.zhiquwang.presenter.NearbyStorePresenter;
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
    private int page=0;
    private NearbyStoreAdapter nearbyStoreAdapter;
    private String job_id;

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
        InitUI();
        nearbyStorePresenter = new NearbyStorePresenter(this);
        nearbyStorePresenter.GetNearbyStore(token,longitude,latitude,limit,page+"");
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        nearbyStoreAdapter = new NearbyStoreAdapter(this,job_id);
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
        nearbyStoreAdapter.setListAll(list);
        mRecyclerView.setAdapter(nearbyStoreAdapter);
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
        mRecyclerView.setRefreshing(true);//没有更多数据
    }

    @OnClick({R.id.nearby_story_img_back})
    public void  onViewClick(View view){
        switch (view.getId()){
            case R.id.nearby_story_img_back:
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

    public void UpdateUI(int code, String msg, NearbyStoreBean.DataBeanX data){
        if (code==1){
           list = data.getData();
           nearbyStoreAdapter.setListAll(list);
           nearbyStoreAdapter.notifyDataSetChanged();
        }else {
            ToastUtils.showToast(mContext,msg);
        }
    }
}

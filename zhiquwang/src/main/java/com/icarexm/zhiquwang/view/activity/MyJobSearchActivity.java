package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MyJobSearchAdapter;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyJobSearchActivity extends BaseActivity {

    @BindView(R.id.my_job_search_recyclerView)
    XRecyclerView mRecyclerView;
    private List<String> list=new ArrayList<>();
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_job_search);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        InitUI();
    }

    private void InitUI() {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        MyJobSearchAdapter myJobSearchAdapter = new MyJobSearchAdapter(mContext);
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
        myJobSearchAdapter.setListAll(list);
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
}

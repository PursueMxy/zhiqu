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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.FRAdapter;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecruitActivity extends BaseActivity {

     @BindView(R.id.recruit_edt_content)
    EditText edt_content;
     @BindView(R.id.recruit_recyclerView)
    XRecyclerView mRecyclerView;
    private List<HomeDataBean.DataBeanX.DataBean> homeDataLists=new ArrayList<>();
    private Context mContext;
    private SharedPreferences share;
    private String token;
    private String limit="20";
    private int page=0;
    private FRAdapter frAdapter;
    private String sequence="";
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit);
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        SearchData(sequence);
    }

    private void InitUI() {
        edt_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                sequence = charSequence.toString();
                SearchData(sequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        frAdapter = new FRAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //加载更多
                page=0;
                SearchData(sequence);
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }

            @Override
            public void onLoadMore() {
                page=page+1;
                SearchData(sequence);
                //加载更多
                mRecyclerView.loadMoreComplete();//加载动画完成
                mRecyclerView.refreshComplete();//刷新动画完成
            }
        });
        frAdapter.setListAll(homeDataLists);
        mRecyclerView.setAdapter(frAdapter);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , MxyUtils.dpToPx(mContext, MxyUtils.getDimens(mContext, R.dimen.dp_10))
                        , 0
                        , 0);
            }
        });
        frAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Intent intent = new Intent(mContext, RecruitDetailActivity.class);
                intent.putExtra("job_id",homeDataLists.get(position).getJob_id()+"");
                startActivity(intent);
            }
        });
        frAdapter.notifyDataSetChanged();
    }

    private void SearchData(String content) {
        OkGo.<String>post(RequstUrl.URL.HomeData)
                .params("token",token)
                .params("limit",limit)
                .params("page",page)
                .params("zone_id","")
                .params("area_id","")
                .params("salary_id","")
                .params("age_id","")
                .params("vocation_id","")
                .params("environment_id","")
                .params("job_id","")
                .params("key",content)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                        HomeDataBean homeDataBean = new GsonBuilder().create().fromJson(response.body(), HomeDataBean.class);
                        if (homeDataBean.getCode()==1){
                            HomeDataBean.DataBeanX data = homeDataBean.getData();
                            List<HomeDataBean.DataBeanX.DataBean> homeDataList = data.getData();
                            if (homeDataList.size()>0) {
                                homeDataLists=homeDataList;
                                if (page == 0) {
                                    frAdapter.setListAll(homeDataLists);
                                    frAdapter.notifyDataSetChanged();
                                } else {
                                    frAdapter.addItemsToLast(homeDataLists);
                                    frAdapter.notifyDataSetChanged();
                                }
                            }else {
                                page=0;
                                mRecyclerView.refreshComplete();//刷新动画完成
                            }
                        }
                    }
                });
    }

    @OnClick({R.id.recruit_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recruit_img_back:
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

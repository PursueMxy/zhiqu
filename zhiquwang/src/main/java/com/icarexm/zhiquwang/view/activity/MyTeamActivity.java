package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MyTeamAdapter;
import com.icarexm.zhiquwang.bean.MyTeanBean;
import com.icarexm.zhiquwang.contract.MyTeamContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.MyTeamPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.zhouyou.recyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTeamActivity extends BaseActivity implements MyTeamContract.View {

    @BindView(R.id.my_team_recyclerView)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private MyTeamPresenter myTeamPresenter;
    private String token;
    private String limit="20";
    private int page=0;
    private CustomProgressDialog progressDialog;
    private List<MyTeanBean.DataBeanX.DataBean> dataBeanList=new ArrayList<>();
    private MyTeamAdapter myTeamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_team);
         mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        LoadingDialogShow();
        myTeamPresenter = new MyTeamPresenter(this);
        myTeamPresenter.GetMyTeam(token,limit,page+"");
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        myTeamAdapter = new MyTeamAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
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
        mRecyclerView.setAdapter(myTeamAdapter);
        myTeamAdapter.setListAll(dataBeanList);
    }

    @OnClick({R.id.my_team_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_team_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.my_team_img_back)) {
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

    public void  UpdateUI(int code,String msg, MyTeanBean.DataBeanX data){
      LoadingDialogClose();
        ToastUtils.showToast(mContext,msg);
        if (code==1){
            dataBeanList = data.getData();
            myTeamAdapter.setListAll(dataBeanList);
            myTeamAdapter.notifyDataSetChanged();
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

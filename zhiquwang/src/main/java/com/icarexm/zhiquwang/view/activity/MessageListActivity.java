package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MessageAdapter;
import com.icarexm.zhiquwang.adapter.MessageListAdapter;
import com.icarexm.zhiquwang.bean.ChatListBean;
import com.icarexm.zhiquwang.contract.MessageListContract;
import com.icarexm.zhiquwang.presenter.MessageListPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.zhouyou.recyclerview.XRecyclerView;
import com.zhouyou.recyclerview.adapter.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageListActivity extends BaseActivity implements MessageListContract.View {

    @BindView(R.id.message_list_recycler)
    XRecyclerView mRecyclerView;
    private Context mContext;
    private MessageListAdapter messageListAdapter;
    private List<ChatListBean.DataBean> messageList=new ArrayList<>();
    private MessageListPresenter messageListPresenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        messageListPresenter = new MessageListPresenter(this);
        messageListPresenter.phoneGetChatList(token);
    }

    private void InitUI() {
        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        messageListAdapter = new MessageListAdapter(mContext);
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
        mRecyclerView.setAdapter(messageListAdapter);
        messageListAdapter.setListAll(messageList);
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
        messageListAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Object item, int position) {
                Intent intent = new Intent(mContext, CustomerChatActivity.class);
                intent.putExtra("job_id",messageList.get(position).getJob_id()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void UpdateMessageList(int code, String msg, List<ChatListBean.DataBean> dataBeanList) {
        timeHandler.postDelayed(timeRunnable,2000);
        if (code==1) {
            messageList = dataBeanList;
            messageListAdapter.setListAll(messageList);
            messageListAdapter.notifyDataSetChanged();
        }
    }

    @OnClick({R.id.message_list_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.message_list_img_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timeHandler.removeCallbacks(timeRunnable);
    }

    //防止多次点击获取验证码
    Handler timeHandler=new Handler();
    Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
            messageListPresenter.phoneGetChatList(token);
        }
    };
}

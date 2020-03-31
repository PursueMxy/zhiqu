package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.CustomerChatAdapter;
import com.icarexm.zhiquwang.adapter.MessageListAdapter;
import com.icarexm.zhiquwang.bean.ChatLogListBean;
import com.icarexm.zhiquwang.bean.ChatMessageBean;
import com.icarexm.zhiquwang.bean.CreatChatBean;
import com.icarexm.zhiquwang.bean.SendChatBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.bean.UserInitBean;
import com.icarexm.zhiquwang.chatroom.BaseChatRoom;
import com.icarexm.zhiquwang.chatroom.MainChatRoom;
import com.icarexm.zhiquwang.contract.CustomerChatContract;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.presenter.CustomerChatPresenter;
import com.icarexm.zhiquwang.socket.AppSocket;
import com.icarexm.zhiquwang.socket.BaseSocket;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.GifSizeFilter;
import com.icarexm.zhiquwang.utils.IConstants;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhouyou.recyclerview.XRecyclerView;


import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomerChatActivity extends BaseActivity implements Observer, AppSocket.SendOnItemClick, CustomerChatContract.View {

    @BindView(R.id.customer_chat_recycler)
    XRecyclerView mRecyclerView;
    @BindView(R.id.customer_label)
    LabelsView labelsView;
    @BindView(R.id.customer_chat_edt_content)
    EditText edt_content;
    @BindView(R.id.customer_chat_tv_title)
    TextView tv_title;
    private Context mContext;
    private CreatChatBean.DataBean dataBean;
    private List<ChatMessageBean.NameValuePairsBean> chat_list=new ArrayList<>();
    private CustomerChatAdapter customerChatAdapter;
    private CustomerChatPresenter customerChatPresenter;
    private String token;
    private String job_id;
    private TextView tv_position;
    private TextView tv_salary;
    private TextView tv_age;
    private LabelsView head_labels;
    private List<String> word_list;
    private boolean is_sendchat=false;
    private static final int REQUEST_CODE=1001;
    private int limit=10;
    private int page=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_customer_chat);
        Intent intent = getIntent();
        job_id = intent.getStringExtra("job_id");
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        MainChatRoom.getInstance().addObserver(this);
        AppSocket.getInstance().setOnItemClickListener(this);
        customerChatPresenter = new CustomerChatPresenter(this);
        customerChatPresenter.phoneCreateChat(token,job_id);
        InitUI();
    }


    private void InitUI() {
        labelsView.setMaxLines(1);
//        labelsView.setLabels(chat_list);
        View chat_head = getLayoutInflater().inflate(R.layout.chat_head_item, null);
        tv_position = chat_head.findViewById(R.id.chat_head_item_tv_position);
        tv_salary = chat_head.findViewById(R.id.chat_head_item_tv_salary);
        tv_age = chat_head.findViewById(R.id.chat_head_item_tv_age);
        head_labels = chat_head.findViewById(R.id.chat_head_labels);
        mRecyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        customerChatAdapter = new CustomerChatAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setFootViewText("拼命加载中","已经全部");
        mRecyclerView.addHeaderView(chat_head);
        mRecyclerView.setLoadingMoreEnabled(false);
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
//                mRecyclerView.setNoMore(true);
            }
        });
        mRecyclerView.setAdapter(customerChatAdapter);
        customerChatAdapter.setListAll(chat_list);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0
                        , 30
                        , 0
                        , 0);
            }
        });
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                String word = word_list.get(position);
                String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                        word, "1", dataBean.getUser_avatar()));
                ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), 1585652699,
                        word, 1, dataBean.getUser_avatar(), 1);
                chat_list.add(nameValuePairsBean);
                customerChatAdapter.setListAll(chat_list);
                customerChatAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition( chat_list.size()- 1);
                AppSocket.getInstance().sendMessage(msg);
                if (!is_sendchat) {
                    AppSocket.getInstance().ChatBackcall();
                    is_sendchat=true;
                }
            }
        });
    }

    private void UserInit(String user_id) {
        AppSocket.getInstance().addUser(new GsonBuilder().create().toJson(new UserInitBean(user_id)));

    }

    @Override
    public void update(Observable observable, final Object o) {
    }

    @OnClick({R.id.customer_chat_img_send_message,R.id.customer_chat_img_back,R.id.customer_chat_img_send_img})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.customer_chat_img_send_message:
                String  content= edt_content.getText().toString();
                if (!content.equals("")) {
                    String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                            content, "1", dataBean.getUser_avatar()));
                    ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), 1585652699,
                            content, 1, dataBean.getUser_avatar(), 1);
                    chat_list.add(nameValuePairsBean);
                    customerChatAdapter.setListAll(chat_list);
                    customerChatAdapter.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition( chat_list.size()- 1);
                    AppSocket.getInstance().sendMessage(msg);
                    if (!is_sendchat) {
                        AppSocket.getInstance().ChatBackcall();
                        is_sendchat=true;
                    }
                    edt_content.setText("");
                }else {
                    ToastUtils.showToast(mContext,"消息不能为空" );
                }
                break;
            case R.id.customer_chat_img_back:
                finish();
                break;
            case R.id.customer_chat_img_send_img:
                if (!ButtonUtils.isFastDoubleClick(R.id.customer_chat_img_send_img)) {
                    try {
                        Matisse.from(this)
                                .choose(MimeType.ofImage(), false)
                                .countable(true)
                                .capture(true)
                                .captureStrategy(new CaptureStrategy(true, "com.icarexm.zhiquwang.fileprovider", "test"))
                                .maxSelectable(1)
                                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                                .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_110))
                                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR)
                                .thumbnailScale(0.85f)
                                .imageEngine(new GlideEngine())
                                .showSingleMediaType(true)
                                .originalEnable(true)
                                .maxOriginalSize(10)
                                .autoHideToolbarOnSingleTap(true)
                                .forResult(REQUEST_CODE);
                    } catch (Exception e) {
                        //权限申请
                        ToastUtils.showToast(mContext, "请允许权限");
                        XXPermissions.with(this)
                                .request(new OnPermission() {

                                    @Override
                                    public void hasPermission(List<String> granted, boolean isAll) {

                                    }

                                    @Override
                                    public void noPermission(List<String> denied, boolean quick) {

                                    }
                                });
                    }
                }
                break;
        }
    }

    @Override
    public void updateMessage(String content) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ChatMessageBean chatMessageBean = new GsonBuilder().create().fromJson(content, ChatMessageBean.class);
                customerChatAdapter.addItemToLast(chatMessageBean.getNameValuePairs());
                chat_list.add(chatMessageBean.getNameValuePairs());
                customerChatAdapter.notifyDataSetChanged();
                mRecyclerView.smoothScrollToPosition( chat_list.size()- 1);
            }
        });
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void updateCreateChat(int code, String msg, CreatChatBean.DataBean data) {
         if (data!=null){
             dataBean=data;
             customerChatPresenter.phoneGetChatRecord(token,data.getChat_id()+"",limit,page);
             tv_title.setText(data.getAdmin_name());
             UserInit(data.getUser_id()+"");
             tv_position.setText(data.getJob_name());
             tv_age.setText(data.getAge());
             tv_salary.setText(data.getSalary_start()+"~"+data.getSalary_end()+"("+data.getSalary_hour()+")");
             List<String> head_lab=new ArrayList<>();
             for (int a=0;a<data.getLabel_arr().size();a++){
                 head_lab.add(data.getLabel_arr().get(a).getLabel_name());
             }
             head_labels.setLabels(head_lab);
             List<CreatChatBean.DataBean.WordBean> word = data.getWord();
             word_list = new ArrayList<>();
             for (int a=0;a<word.size();a++){
                 word_list.add(word.get(a).getWord());
             }
             labelsView.setLabels(word_list);
         }
    }

    @Override
    public void UpdateChatLog(int code, String msg, ChatLogListBean.DataBeanX data) {
        if (code==1){
            List<ChatLogListBean.DataBeanX.DataBean> dataList = data.getData();
            for (int a=dataList.size()-1;a>0;a--){
                String avatar;
                if ( dataList.get(a).getSide()==1){
                    avatar=dataList.get(a).getAdmin_avatar();
                }else {
                    avatar=dataList.get(a).getUser_avatar();
                }
                ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataList.get(a).getChat_id(),dataList.get(a).getUser_id(), dataList.get(a).getAdmin_id() + "", dataList.get(a).getJob_id(), 1585652699,
                        dataList.get(a).getContent(), dataList.get(a).getType(), avatar, dataList.get(a).getSide());
                chat_list.add(nameValuePairsBean);
            }
            customerChatAdapter.setListAll(chat_list);
            customerChatAdapter.notifyDataSetChanged();
            mRecyclerView.smoothScrollToPosition(chat_list.size()- 1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_CODE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (uris.size()>0) {
                        List<String> strings = Matisse.obtainPathResult(data);
                        File file = new File(strings.get(0));//实例化数据库文件
                        OkGo.<String>post(RequstUrl.URL.UploadImg)
                                .params("img",file)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                                        UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                                         String avatar = DataBean.getUrl();
                                        String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                                                avatar, "2", dataBean.getUser_avatar()));
                                        ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), 1585652699,
                                                avatar, 2, dataBean.getUser_avatar(), 1);
                                        chat_list.add(nameValuePairsBean);
                                        customerChatAdapter.notifyDataSetChanged();
                                        mRecyclerView.smoothScrollToPosition( chat_list.size()- 1);
                                        AppSocket.getInstance().sendMessage(msg);
                                    }
                                });
                    }
                    break;
                default:
            }
        }
    }
}

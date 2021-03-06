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
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
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
import com.icarexm.zhiquwang.view.dialog.ImgBoostDialog;
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
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class CustomerChatActivity extends BaseActivity implements Observer, AppSocket.SendOnItemClick, CustomerChatContract.View, CustomerChatAdapter.ImageOnClickListtener {

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
    private int limit=20;
    private int page=1;
    private String on_send;
    private SharedPreferences share;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //让布局向上移来显示软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_customer_chat);
        Intent intent = getIntent();
        job_id = intent.getStringExtra("job_id");
        mContext = getApplicationContext();
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        on_send = share.getString("on_send", "");
        ButterKnife.bind(this);
        MainChatRoom.getInstance().addObserver(this);
        AppSocket.getInstance().setOnItemClickListener(this);
        customerChatPresenter = new CustomerChatPresenter(this);
        customerChatPresenter.phoneCreateChat(token,job_id);
        if (on_send.equals("true")) {
            AppSocket.getInstance().ChatBackcall();
            SharedPreferences.Editor editor = share.edit();
            editor.putString("on_send","false");
            editor.commit();//提交
        }else {
        }
        InitUI();
    }


    private void InitUI() {
        labelsView.setMaxLines(2);
        View chat_head = getLayoutInflater().inflate(R.layout.chat_head_item, null);
        tv_position = chat_head.findViewById(R.id.chat_head_item_tv_position);
        tv_salary = chat_head.findViewById(R.id.chat_head_item_tv_salary);
        tv_age = chat_head.findViewById(R.id.chat_head_item_tv_age);
        head_labels = chat_head.findViewById(R.id.chat_head_labels);
        mRecyclerView.setNestedScrollingEnabled(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        customerChatAdapter = new CustomerChatAdapter(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addHeaderView(chat_head);
        mRecyclerView.setLoadingMoreEnabled(false);
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //加载更多
                mRecyclerView.refreshComplete();//刷新动画完成
                 page=++page;
                customerChatPresenter.phoneGetChatRecord(token,dataBean.getChat_id()+"",limit,page);
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
                        , 20
                        , 0
                        , 20);
            }
        });
        labelsView.setOnLabelClickListener(new LabelsView.OnLabelClickListener() {
            @Override
            public void onLabelClick(TextView label, Object data, int position) {
                String word = word_list.get(position);
                long currentTime = System.currentTimeMillis()/1000;
                String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                        word, "1", dataBean.getUser_avatar(),currentTime+""));
                ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), (int)currentTime ,
                        word, 1, dataBean.getUser_avatar(), 1);
                chat_list.add(nameValuePairsBean);
                customerChatAdapter.addItemToLast(nameValuePairsBean);
                customerChatAdapter.notifyDataSetChanged();
                    AppSocket.getInstance().sendMessage(msg);
                ScrollLoading();
            }
        });
        chat_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RecruitDetailActivity.class);
                intent.putExtra("job_id",dataBean.getJob_id()+"");
                intent.putExtra("zone_name",dataBean.getJob_name());
                startActivity(intent);
                finish();
            }
        });
        customerChatAdapter.setImageOnClickListtener(this);
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
                    String s= System.currentTimeMillis()/1000+"";
                    int currentTime = Integer.parseInt(s);
                    String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                            content, "1", dataBean.getUser_avatar(),currentTime+""));
                    ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), (int)currentTime ,
                            content, 1, dataBean.getUser_avatar(), 1);
                    chat_list.add(nameValuePairsBean);
                    customerChatAdapter.addItemToLast(nameValuePairsBean);
                    customerChatAdapter.notifyDataSetChanged();
                    AppSocket.getInstance().sendMessage(msg);
                    ScrollLoading();
                    edt_content.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
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
                                .permission(Permission.CAMERA,Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE,Permission.ACCESS_FINE_LOCATION,
                                        Permission.ACCESS_COARSE_LOCATION,Permission.READ_CALENDAR)
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
    public void updateMessage(ChatMessageBean.NameValuePairsBean nameValuePairsBean) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                customerChatAdapter.addItemToLast(nameValuePairsBean);
                customerChatAdapter.notifyDataSetChanged();
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
             page=1;
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
         }else {

         }
    }

    @Override
    public void UpdateChatLog(int code, String msg, ChatLogListBean.DataBeanX data) {
            if (code==1){
                if (page==1) {
                    chat_list.clear();
                    List<ChatLogListBean.DataBeanX.DataBean> dataList = data.getData();
                    for (int a = dataList.size() - 1; a > -1; a--) {
                        String avatar;
                        if (dataList.get(a).getSide() == 2) {
                            avatar = dataList.get(a).getAdmin_avatar();
                        } else {
                            avatar = dataList.get(a).getUser_avatar();
                        }
                        ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataList.get(a).getChat_id(), dataList.get(a).getUser_id(), dataList.get(a).getAdmin_id() + "", dataList.get(a).getJob_id(), dataList.get(a).getTime(),
                                dataList.get(a).getContent(), dataList.get(a).getType(), avatar, dataList.get(a).getSide());
                        chat_list.add(nameValuePairsBean);
                    }
                    customerChatAdapter.setListAll(chat_list);
                    customerChatAdapter.notifyDataSetChanged();
                    page = 1;
                    ScrollLoading();
                }else {
                    List<ChatLogListBean.DataBeanX.DataBean> dataList = data.getData();
                    for (int a = dataList.size() - 1; a > -1; a--) {
                        String avatar;
                        if (dataList.get(a).getSide() == 2) {
                            avatar = dataList.get(a).getAdmin_avatar();
                        } else {
                            avatar = dataList.get(a).getUser_avatar();
                        }
                        ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataList.get(a).getChat_id(), dataList.get(a).getUser_id(), dataList.get(a).getAdmin_id() + "", dataList.get(a).getJob_id(), dataList.get(a).getTime(),
                                dataList.get(a).getContent(), dataList.get(a).getType(), avatar, dataList.get(a).getSide());
                        chat_list.add(0,nameValuePairsBean);
                    }
                    customerChatAdapter.setListAll(chat_list);
                    customerChatAdapter.notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(1);
                }
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
                        Luban.with(this)
                                .load(file)
                                .ignoreBy(100)
                                .setTargetDir(getPath())
                                .filter(new CompressionPredicate() {
                                    @Override
                                    public boolean apply(String path) {
                                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                                    }
                                })
                                .setCompressListener(new OnCompressListener() {
                                    @Override
                                    public void onStart() {
                                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                    }

                                    @Override
                                    public void onSuccess(File file) {
                                        // TODO 压缩成功后调用，返回压缩后的图片文件
                                        UploadImg(file);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        // TODO 当压缩过程出现问题时调用
                                    }
                                }).launch();

                    }
                    break;
                default:
            }
        }
    }

    private void UploadImg(File file) {
        OkGo.<String>post(RequstUrl.URL.UploadImg)
                .params("img",file)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String s= System.currentTimeMillis()/1000+"";
                        int currentTime = Integer.parseInt(s);
                        UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                        UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                        String avatar = DataBean.getUrl();
                        String msg = new GsonBuilder().create().toJson(new SendChatBean(dataBean.getChat_id() + "", dataBean.getUser_id() + "", dataBean.getAdmin_id() + "", dataBean.getJob_id() + "",
                                avatar, "2", dataBean.getUser_avatar(),currentTime+""));
                        ChatMessageBean.NameValuePairsBean nameValuePairsBean = new ChatMessageBean.NameValuePairsBean(dataBean.getChat_id(), dataBean.getUser_id(), dataBean.getAdmin_id() + "", dataBean.getJob_id(), currentTime,
                                avatar, 2, dataBean.getUser_avatar(), 1);
                        customerChatAdapter.addItemToLast(nameValuePairsBean);
                        customerChatAdapter.notifyDataSetChanged();
                        chat_list.add(nameValuePairsBean);
                        AppSocket.getInstance().sendMessage(msg);
                        ScrollLoading();
                    }
                });
    }

    @Override
    public void ImageDialog(String url) {
        new ImgBoostDialog(this,url).show();
    }

    public void  ScrollLoading(){
        tv_title.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.smoothScrollToPosition(chat_list.size()+1);
            }
        },200);
    }

    private String getPath() {
        String path = Environment.getExternalStorageDirectory() + "/zqw/image/";
        File file = new File(path);
        if (file.mkdirs()) {
            return path;
        }
        return path;
    }
}

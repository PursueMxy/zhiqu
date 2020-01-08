package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;

import com.danikula.videocache.HttpProxyCacheServer;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.MyApplication;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.IndicatorAdapter;
import com.icarexm.zhiquwang.adapter.ViewPagerAdapter;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.bean.InviteUrlBean;
import com.icarexm.zhiquwang.bean.JobDetailBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.contract.RecruitDetailContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.CustomVideoView;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.presenter.RecruitDetailPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.MxyUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.wxapi.WXEntryActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecruitDetailActivity extends BaseActivity implements RecruitDetailContract.View{

    private Context mContext;
    @BindView(R.id.recruit_dtl_viewPage)
    ViewPager2 recruit_dtl_viewPage;
    @BindView(R.id.recruit_dtl_rcv_indicator)
    RecyclerView recyclerView;
    @BindView(R.id.recruit_dtl_labels)
    LabelsView labelsView;
    @BindView(R.id.recruit_dtl_tv_jobName)
    TextView tv_job_name;
    @BindView(R.id.recruit_dtl_tv_salary)
    TextView tv_salary;
    @BindView(R.id.recruit_dtl_tv_company)
    TextView tv_company;
    @BindView(R.id.recruit_dtl_tv_address)
    TextView tv_address;
    @BindView(R.id.recruit_dtl_tv_label_price)
    TextView tv_label_price;
    @BindView(R.id.recruit_dtl_tv_zhSalary)
    TextView tv_zhSalary;
    @BindView(R.id.recruit_dtl_tv_hourSalary)
    TextView tv_hourSalary;
    @BindView(R.id.recruit_dtl_tv_other_benefits)
    TextView tv_other_benefits;
    @BindView(R.id.recruit_dtl_ask_tv_age)
    TextView tv_age;
    @BindView(R.id.recruit_dtl_ask_tv_education)
    TextView tv_education;
    @BindView(R.id.recruit_dtl_ask_tv_workingLife)
    TextView tv_workingLife;
    @BindView(R.id.recruit_dtl_tv_other_ask)
    TextView tv_other_ask;
    @BindView(R.id.recruit_dtl_tv_workingContent)
    TextView tv_workingContent;
    @BindView(R.id.recruit_dtl_tv_introduce)
    TextView tv_introduce;
    @BindView(R.id.recruit_dtl_listview)
    ListView list_tuijian;
    @BindView(R.id.recruit_dtl_tv_title)
    TextView tv_title;
    @BindView(R.id.recruit_dtl_video)
    CustomVideoView videoView;
    @BindView(R.id.recruit_dtl_img_one)
    ImageView recruit_dtl_img_one;
    private int CurrentItem=0;
    private ViewPagerAdapter viewPagerAdapter;
    private IndicatorAdapter indicatorAdapter;
    private View dialog_callphone;
    private TextView tv_phone_number;
    private AlertDialog alertDialog;
    private RecruitDetailPresenter recruitDetailPresenter;
    private String token;
    private String job_id;
    private int limit=20;
    private int page=1;
    private String area_id;
    private String salary_id;
    private String age_id;
    private String vocation_id;
    private String environment_id;
    private String zone_id;
    private List<String> img_arr=new ArrayList<>();
    private int have_video=1;
    private List<HomeDataBean.DataBeanX.DataBean> homeDataList=new ArrayList<>();
    private MyAdapter myAdapter;
    private String mobile;
    private CustomProgressDialog progressDialog;
    private String latitude;
    private String longitude;
    private String address;
    private String enterprise_name;
    private String inviteUrl;
    private String zone_name="";
    /**
     * 发送本地广播的action
     */
    public static final String LOCAL_BROADCAST = "com.zhkj.syyj.LOCAL_BROADCAST_COLLECT";
    private LocalBroadcastManager localBroadcastManager;
    private LocalReceiver localReceiver;
    private HttpProxyCacheServer proxy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_detail);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        job_id = intent.getStringExtra("job_id");
        zone_name = intent.getStringExtra("zone_name");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
        LoadingDialogShow();
        InitDataUrl();
        recruitDetailPresenter = new RecruitDetailPresenter(this);
        recruitDetailPresenter.GetJobDetail(token,job_id);
        recruitDetailPresenter.GetHomeData(token,limit+"",page+"",zone_id,area_id,salary_id,age_id,vocation_id,environment_id,job_id);
    }

    private void InitDataUrl() {
    OkGo.<String>post(RequstUrl.URL.inviteUrl)
            .params("token",token)
            .execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    InviteUrlBean inviteUrlBean = new GsonBuilder().create().fromJson(response.body(), InviteUrlBean.class);
                    if(inviteUrlBean.getCode()==1){
                        inviteUrl = inviteUrlBean.getData().getUrl();
                    }
                }
            });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        recruitDetailPresenter.GetJobDetail(token,job_id);
    }

    @OnClick({R.id.recruit_dtl_tv_wechat,R.id.recruit_dtl_tv_nearby_store,R.id.recruit_dtl_tv_callPhone,R.id.recruit_dtl_btn_one_key_enroll
    ,R.id.recruit_dtl_img_back,R.id.recruit_dtl_tv_address,R.id.recruit_dtl_img_invite_url})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.recruit_dtl_tv_wechat:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_tv_wechat)) {
                    WechatDialog();
                }
                break;
            case R.id.recruit_dtl_tv_nearby_store:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_tv_nearby_store)) {
                    Intent intent = new Intent(mContext, NearbyStoreActivity.class);
                    intent.putExtra("job_id", job_id);
                    startActivity(intent);
                }
                break;
            case R.id.recruit_dtl_tv_callPhone:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_tv_callPhone)) {
                    callPhoneDialog();
                }
                break;
            case R.id.recruit_dtl_img_invite_url:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_img_invite_url)) {
                    WechatDialog();
                }
                break;
            case R.id.recruit_dtl_btn_one_key_enroll:
                Log.e("token",token+"和"+job_id);
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_btn_one_key_enroll)) {
                    OkGo.<String>post(RequstUrl.URL.applicationInfo)
                            .params("token", token)
                            .params("job_id", job_id)
                            .execute(new StringCallback() {
                                private int code;
                                private String msg;
                                @Override
                                public void onSuccess(Response<String> response) {
                                    PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                    msg = resultBean.getMsg();
                                    code = resultBean.getCode();
                                    if (resultBean.getCode() == 1) {
                                        enrollDialog(code, msg);
                                    } else if (resultBean.getCode() == 10003) {
                                        enrollDialog(code, msg);
                                    } else if (resultBean.getCode() == 20001) {
                                        imperdectDialog(code, msg);
                                    } else {
                                        ToastUtils.showToast(mContext, msg);
                                    }
                                }

                            });
                }
                break;
            case R.id.recruit_dtl_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_img_back)) {
                    finish();
                }
                break;
            case R.id.recruit_dtl_tv_address:
                if (!ButtonUtils.isFastDoubleClick(R.id.recruit_dtl_tv_address)) {
                    startNaviGao();
                }
                break;
        }
    }

    private void InitUI() {
        proxy = MyApplication.getProxy(getApplicationContext());
        //设置视频控制器,组件可以控制视频的播放，暂停，快进，组件，不需要你实现
       videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {

            }
        });
       videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int
                    extra) {
               videoView.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
               videoView.setVisibility(View.GONE);
                return true;
            }
        });
       videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if(what==MediaPlayer.MEDIA_INFO_BUFFERING_START ){
                    //                   加载中
                }else{
                    //播放中
                }
                return true;

            }
        });
        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
        localReceiver = new LocalReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(LOCAL_BROADCAST);   //添加action
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        indicatorAdapter = new IndicatorAdapter(mContext, img_arr, CurrentItem, have_video);
        recyclerView.setAdapter(indicatorAdapter);
        myAdapter = new MyAdapter();
        list_tuijian.setAdapter(myAdapter);
        list_tuijian.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                finish();
                Intent intent = new Intent(mContext, RecruitDetailActivity.class);
                intent.putExtra("job_id",homeDataList.get(position).getJob_id()+"");
                startActivity(intent);
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // imageView.setVisibility(View.GONE);
                mediaPlayer.setLooping(true);
                mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mediaPlayer, int what, int i1) {

                        //开始播放时，就把显示第一帧的ImageView gone 掉
                        if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                            // video started; hide the placeholder.
                            recruit_dtl_img_one.setVisibility(View.GONE);
                            //videoView.seekTo(0);
                            return true;
                        }
                        return false;
                    }
                });
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int
                    extra) {
                videoView.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                Log.e("播放","播放异常");
                return true;
            }
        });
        //设置点击事件，OnClickListener不好用
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (videoView.isPlaying()){
                    videoView.pause();
                }else {
                    videoView.start();
                }
                return false;
            }
        });
    }

    //转发微信弹出窗
    public void  WechatDialog(){
        final BottomDialog bottomDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_wechat_share, null);
        inflate.findViewById(R.id.dialog_wechat_share_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        inflate.findViewById(R.id.dialog_wechat_share_tv_friend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXEntryActivity.ShareWeixin(mContext,MyApplication.iwxapi,inviteUrl,0);

            }
        });
        inflate.findViewById(R.id.dialog_wechat_share_tv_monments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WXEntryActivity.ShareWeixin(mContext,MyApplication.iwxapi,inviteUrl,1);
            }
        });
        bottomDialog.setContentView(inflate);
        bottomDialog.show();
    }

    private void enrollDialog(int code,String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RecruitDetailActivity.this);
        View dialog_affirm = getLayoutInflater().inflate(R.layout.dialog_affirm, null);
        TextView tv_content= dialog_affirm.findViewById(R.id.dialog_affirm_tv_content);
        builder.setView(dialog_affirm);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        tv_content.setText(msg);
        dialog_affirm.findViewById(R.id.dialog_affirm_btn_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dialog_affirm.findViewById(R.id.dialog_affirm_btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code==1) {
                    OkGo.<String>post(RequstUrl.URL.toApplication)
                            .params("token", token)
                            .params("job_id", job_id)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                    if (resultBean.getCode() == 1) {
                                      startActivity(new Intent(mContext, RecruitDetailActivity.class));
                                    }
                                    ToastUtils.showToast(mContext, resultBean.getMsg());

                                }
                            });
                    alertDialog.dismiss();
                }else if (code==10003){
                 startActivity(new Intent(mContext,MyResumeActivity.class));
                 finish();
                } else if (code==20001){
                    alertDialog.dismiss();
                }
            }
        });

    }

    private void imperdectDialog(int code,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(RecruitDetailActivity.this);
        View dialog_affirm = getLayoutInflater().inflate(R.layout.dialog_imperfect, null);
        EditText edt_name= dialog_affirm.findViewById(R.id.dialog_imperfect_edt_name);
        EditText edt_mobile= dialog_affirm.findViewById(R.id.dialog_imperfect_edt_mobile);
        builder.setView(dialog_affirm);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        dialog_affirm.findViewById(R.id.dialog_imperfect_btn_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = edt_mobile.getText().toString();
                String name = edt_name.getText().toString();
                if (!mobile.equals("")){
                    if (!name.equals("")){
                        OkGo.<String>post(RequstUrl.URL.toApplication)
                                .params("token", token)
                                .params("job_id", job_id)
                                .params("real_name",name)
                                .params("phone",mobile)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                        if (resultBean.getCode() == 1) {
                                          startActivity(new Intent(mContext, RecruitDetailActivity.class));
                                        }else if (resultBean.getCode() ==10001){
                                            ToastUtils.showToast(mContext,resultBean.getMsg());
                                            startActivity(new Intent(mContext,LoginActivity.class));
                                            finish();
                                        }
                                        ToastUtils.showToast(mContext, resultBean.getMsg());

                                    }
                                });
                        alertDialog.dismiss();
                    }else {
                        ToastUtils.showToast(mContext,"姓名不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"手机号不能为空");
                }

            }
        });
    }
//    private Handler handler=new Handler();
//    private Runnable runnable=new Runnable() {
//        @Override
//        public void run() {
//            if (CurrentItem<homeDataList.size()-1){
//                CurrentItem++;
//            }else {
//                CurrentItem=0;
//            }
//            recruit_dtl_viewPage.setCurrentItem(CurrentItem);
//            handler.postDelayed(runnable,DELAYMILLIS);
//        }
//    };
    //电话联系
    public void callPhoneDialog(){
        dialog_callphone = getLayoutInflater().inflate(R.layout.dialog_callphone, null);
        tv_phone_number = dialog_callphone.findViewById(R.id.dialog_callphone_tv_number);
        tv_phone_number.setText(mobile);
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        dialog_callphone.findViewById(R.id.dialog_callphone_tv_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcall = new Intent();
                //设置拨打电话的动作
                intentcall.setAction(Intent.ACTION_CALL);
                //设置拨打电话的号码
                intentcall.setData(Uri.parse("tel:" + mobile));
                //开启打电话的意图
                startActivity(intentcall);
                alertDialog.dismiss();
            }
        });
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        alertDialog = builder.setView(dialog_callphone)
                .create();
        alertDialog.show();
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

   //返回数据跟新UI
    public void UpdateUI(int code, String msg, JobDetailBean.DataBean dataBean){
        LoadingDialogClose();
        if (code==1){
            latitude = dataBean.getLatitude();
            longitude = dataBean.getLongitude();
            address = dataBean.getAddress();
            enterprise_name = dataBean.getEnterprise_name();
            img_arr = dataBean.getImg_arr();
            have_video = dataBean.getHave_video();
            viewPagerAdapter = new ViewPagerAdapter(this, img_arr, recruit_dtl_viewPage, have_video);
            recruit_dtl_viewPage.setAdapter(viewPagerAdapter);
            recruit_dtl_viewPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                // 当前页面被滑动时调用
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
                // 当新的页面被选中时调用
                @Override
                public void onPageSelected(int position) {
                    CurrentItem=position;
                    indicatorAdapter.refreshData(CurrentItem);
                    indicatorAdapter.notifyDataSetChanged();
                     if (CurrentItem>0){
                         videoView.setVisibility(View.GONE);
                     }
                    super.onPageSelected(position);
                }
                // 当滑动状态改变时调用
                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
            indicatorAdapter.refreshData( img_arr, CurrentItem, have_video);
            indicatorAdapter.refreshData(CurrentItem);
            indicatorAdapter.notifyDataSetChanged();
            List<JobDetailBean.DataBean.LabelArrBean> label_arr = dataBean.getLabel_arr();
            ArrayList<String> label = new ArrayList<>();
            try {
                for (int a=0;a<label_arr.size();a++){
                    label.add(label_arr.get(a).getLabel_name());
                }
            }catch (Exception e){

            }
            labelsView.setLabels(label);
            tv_title.setText(dataBean.getJob_name());
            tv_job_name.setText(dataBean.getJob_name());
            tv_salary.setText(dataBean.getSalary()+"/月("+dataBean.getSalary_hour()+"/小时)");
            tv_company.setText(dataBean.getEnterprise_name());
            tv_address.setText(dataBean.getAddress());
            tv_label_price.setText(dataBean.getLabel_price());
            tv_zhSalary.setText("综合工资："+dataBean.getSalary()+"/月");
            tv_other_benefits.setText(dataBean.getSalary_other());
            tv_age.setText("性别年龄："+dataBean.getAge_explain());
            tv_education.setText("学      历："+dataBean.getEducation());
            tv_workingLife.setText("工作年限："+dataBean.getWork_year());
            tv_other_ask.setText(dataBean.getNeed_other());
            tv_workingContent.setText(dataBean.getWork_describe());
            tv_introduce.setText(dataBean.getIntroduce());
            mobile = dataBean.getMobile();
            try {
                if (zone_name.equals("") || zone_name == null) {
                    tv_hourSalary.setText("工资架构：" + dataBean.getSalary_hour() );
                } else if (zone_name.equals("高价小时工")) {
                    tv_hourSalary.setText("小时工资：" + dataBean.getSalary_hour() );
                } else {
                    tv_hourSalary.setText("工资架构：" + dataBean.getSalary_hour());
            }
            }catch (Exception e){
                tv_hourSalary.setText("工资架构：" + dataBean.getSalary_hour());
            }
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }

    }


    public  void UpdateUI(int code,String msg, HomeDataBean.DataBeanX data){
        if (code==1){
            homeDataList = data.getData();
            myAdapter.notifyDataSetChanged();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }


    public class MyAdapter extends  BaseAdapter{
        @Override
        public int getCount() {
            return homeDataList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.list_home_fm, null);
           LabelsView labels = itemView.findViewById(R.id.labels);
           TextView tv_position = itemView.findViewById(R.id.list_home_fm_tv_position);
           TextView tv_salary = itemView.findViewById(R.id.list_home_fm_tv_salary);
           TextView tv_age = itemView.findViewById(R.id.list_home_fm_tv_age);
           TextView tv_address = itemView.findViewById(R.id.list_home_fm_tv_address);
            List<HomeDataBean.DataBeanX.DataBean.LabelArrBean> label_arr = homeDataList.get(position).getLabel_arr();
            TextView tv_label_price=  itemView.findViewById(R.id.list_home_fm_tv_label_price);
            ArrayList<String> label = new ArrayList<>();
            for (int a=0;a<label_arr.size();a++){
                label.add(label_arr.get(a).getLabel_name());
            }
            labels.setLabels(label);
            tv_position.setText(homeDataList.get(position).getJob_name());
            tv_salary.setText(homeDataList.get(position).getSalary()+"/月"+homeDataList.get(position).getSalary_hour()+"/时");
            tv_age.setText(homeDataList.get(position).getAge());
            tv_address.setText(homeDataList.get(position).getAddress());
            tv_label_price.setText(homeDataList.get(position).getLabel_price());
            return itemView;
        }
    }

    //调用地图导航
    public void startNaviGao() {
        if (MxyUtils.isAvilible(mContext, "com.autonavi.minimap")) {
            try {
                //sourceApplication
                Intent intent = Intent.getIntent("androidamap://navi?sourceApplication="+enterprise_name+"&poiname="+address+"&lat=" + latitude + "&lon=" + longitude + "&dev=0");
                startActivity(intent);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                ToastUtils.showToast(mContext,"您尚未安装百度地图或地图版本过低");
            }
        } else if (MxyUtils.isAvilible(this, "com.baidu.BaiduMap")) {//传入指定应用包名
            try {
                Intent intent = Intent.getIntent("intent://map/direction?" +
                        "destination=latlng:" + latitude + "," + longitude + "|name:"+address+
                        "&mode=driving&" +     //导航路线方式
                        "&src=appname#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                startActivity(intent); //启动调用
            } catch (URISyntaxException e) {
                Log.e("intent", e.getMessage());
                ToastUtils.showToast(mContext,"您尚未安装百度地图或地图版本过低");
            }
        }
        else {
            ToastUtils.showToast(mContext,"您尚未安装地图或地图版本过低");
        }
    }


    private class LocalReceiver extends BroadcastReceiver {

        private String proxyUrl;

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(!action.equals(LOCAL_BROADCAST)){
                return ;
            }
            boolean queryCity = intent.getBooleanExtra("collect",false);  //判断是否需要调用查询城市
            Log.e("执行了",queryCity+"哈哈哈哈");
            //如果是接收到需要查询城市信息的广播   则去执行该方法
            if(queryCity){
                if (have_video==2) {
                    String s = img_arr.get(0);
                    Bitmap netVideoBitmap = getNetVideoBitmap(s);
                    videoView.setVisibility(View.VISIBLE);
                    recruit_dtl_img_one.setVisibility(View.VISIBLE);
                    recruit_dtl_img_one.setImageBitmap(netVideoBitmap);
                    MediaController localMediaController = new MediaController(RecruitDetailActivity.this);
                    videoView.setMediaController(localMediaController);
                    proxyUrl = proxy.getProxyUrl(s);
                    videoView.setVideoPath(proxyUrl);
                    videoView.requestFocus();//让VideiView获取焦点
                    videoView.start();
                    MediaController mc = new MediaController(RecruitDetailActivity.this);
                    videoView.setMediaController(mc);
                    } else {
                        videoView.setVisibility(View.GONE);
                    }
            }

        }
    }


    //获取网络视频第一帧
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
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

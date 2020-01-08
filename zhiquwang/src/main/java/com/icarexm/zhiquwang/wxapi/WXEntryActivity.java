package com.icarexm.zhiquwang.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.LoginBean;
import com.icarexm.zhiquwang.bean.PublicCodeBean;
import com.icarexm.zhiquwang.bean.WechatBean;
import com.icarexm.zhiquwang.presenter.LoginPresenter;
import com.icarexm.zhiquwang.utils.AppContUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.utils.WxUtil;
import com.icarexm.zhiquwang.view.activity.HomeActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.LogonActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Context mContext;
    private static IWXAPI wxapi;
    private static int mTargetScene = SendMessageToWX.Req.WXSceneSession;
    private static int mTargetSceneTimeline = SendMessageToWX.Req.WXSceneTimeline ;
    private SharedPreferences share;
    private String token;
    private String mobile;
    private String password;
    @BindView(R.id.wxentry_edt_mobile)
    EditText edt_mobile;
    @BindView(R.id.wxentry_edt_password)
    EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mobile = share.getString("mobile", "");
        password = share.getString("password", "");
        mContext = getApplicationContext();
        wxapi = WXAPIFactory.createWXAPI(this, AppContUtils.WX_APP_ID, false);
        wxapi.registerApp(AppContUtils.WX_APP_ID);
        wxapi.handleIntent(getIntent(), this);
        ButterKnife.bind(this);
        edt_mobile.setText(mobile);
        edt_password.setText(password);
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        wxapi = WXAPIFactory.createWXAPI(this, AppContUtils.WX_APP_ID, true);
        wxapi.registerApp(AppContUtils.WX_APP_ID);
        wxapi.handleIntent(getIntent(), this);
    }

    public static IWXAPI InitWeiXin(Context context , @NonNull String weixin_app_id){
        if(TextUtils.isEmpty(weixin_app_id)){
            Toast.makeText(context.getApplicationContext(),"微信appID不能为空",Toast.LENGTH_LONG).show();
        }
        wxapi = WXAPIFactory.createWXAPI(context, weixin_app_id, false);
        wxapi.registerApp(weixin_app_id);
        return wxapi;
    }

    public static  void  loginWeixin(Context context,IWXAPI api){
        if (!api.isWXAppInstalled()){
            Toast.makeText(context.getApplicationContext(),"请先安装微信客户端",Toast.LENGTH_SHORT).show();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "com_icarexm_zhiquwang_wxapi";
        api.sendReq(req);

    }


    public static void  ShareWeixin(Context context, IWXAPI api, String url, int type){
        if (type==0) {
            //初始化一个WXWebpageObject，填写url
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl = url;
            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "求职就用职趣网！";
            msg.description = "专为蓝领打造的求职平台，海量热门职位，返费拿不停！";
            Bitmap thumbBmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_logo);
            msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);

            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = mTargetScene;
            //调用api接口，发送数据到微信
            wxapi.sendReq(req);
        }else  if (type==1){
            //初始化一个WXWebpageObject，填写url
            WXWebpageObject webpage = new WXWebpageObject();
            webpage.webpageUrl =url;
            //用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象
            WXMediaMessage msg = new WXMediaMessage(webpage);
            msg.title = "求职就用职趣网！";
            msg.description = "专为蓝领打造的求职平台，海量热门职位，返费拿不停！";
            Bitmap thumbBmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_logo);
            msg.thumbData = WxUtil.bmpToByteArray(thumbBmp, true);
            //构造一个Req
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = buildTransaction("webpage");
            req.message = msg;
            req.scene = mTargetSceneTimeline;
            //调用api接口，发送数据到微信
            wxapi.sendReq(req);
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                finish();
                break;
        }
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            // 发送成功
            case BaseResp.ErrCode.ERR_OK:
                // 获取code
                try {
                    String code = ((SendAuth.Resp) resp).code;
                    PostWechatLogin(code);
                }catch (Exception e){
                    ToastUtils.showToast(mContext,"分享成功");
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Intent intent1 = new Intent(mContext, LoginActivity.class);
                intent1.putExtra("type","nowechat");
                intent1.putExtra("code","0000");
                startActivity(intent1);
                //拒绝
                break;
            case BaseResp.ErrCode. ERR_USER_CANCEL:
                //取消
                Intent intent2 = new Intent(mContext, LoginActivity.class);
                intent2.putExtra("type","nowechat");
                intent2.putExtra("code","1111");
                startActivity(intent2);
                break;
                default:
                    finish();
                    break;
        }
    }

    private static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 微信登陆
     */
    public void PostWechatLogin( String code){
        OkGo.<String>post(RequstUrl.URL.WechatLogin)
                .params("code",code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String content = response.body();
                        PublicCodeBean publicCodeBean = new GsonBuilder().create().fromJson(response.body(), PublicCodeBean.class);
                        if (publicCodeBean.getCode()==1){
                            LoginBean result = new GsonBuilder().create().fromJson(content, LoginBean.class);
                            SharedPreferences.Editor editor = share.edit();
                            editor.putString("mobile",mobile);
                            editor.putString("password",password);
                            editor.putString("token",result.getData()+"");
                            editor.commit();//提交
                            setAlias(result.getOther());
                            startActivity(new Intent(mContext, HomeActivity.class));
                            finish();
                        }else if (publicCodeBean.getCode()==10004){
                            WechatBean wechatBean = new GsonBuilder().create().fromJson(content, WechatBean.class);
                            Intent intent = new Intent(mContext, LogonActivity.class);
                            intent.putExtra("type","wechat");
                            intent.putExtra("openid", wechatBean.getData().getOpenid());
                            startActivity(intent);
                            finish();
                        }else {
                             startActivity(new Intent(mContext,LoginActivity.class));
                             finish();
                        }
                    }
                });
    }

    /*
     * 绑定别名
     */
    private static final int GET_MEG_SUG=0;
    private static final int MSG_SET_ALIAS=1001;

    private void setAlias(int userid) {
        String alias = String.valueOf("alias_"+userid);
        // 调用JPush API设置Alias
        mHandler1.sendMessage(mHandler1.obtainMessage(MSG_SET_ALIAS, alias));
    }

    private final Handler mHandler1 = new Handler() {//专门用了一个Handler对象处理别名的注册问题
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            JPushInterface.setAliasAndTags(getApplicationContext(),
                    (String) msg.obj, null, mAliasCallback);
        }
    };

    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success极光推送别名设置成功";
                    break;
                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.极光推送别名设置失败，60秒后重试";
                    mHandler1.sendMessageDelayed(mHandler1.obtainMessage(MSG_SET_ALIAS, alias), 1000 * 60);
                    break;
                default:
                    logs = "极光推送设置失败，Failed with errorCode = " + code;
                    break;
            }
        }
    };

}

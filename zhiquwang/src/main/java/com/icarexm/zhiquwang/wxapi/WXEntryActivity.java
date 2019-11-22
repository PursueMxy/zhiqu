package com.icarexm.zhiquwang.wxapi;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.AppContUtils;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private Context mContext;
    private static IWXAPI wxapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        Intent intent = getIntent();
        mContext = getApplicationContext();
        wxapi = WXAPIFactory.createWXAPI(this, AppContUtils.WX_APP_ID, false);
        wxapi.registerApp(AppContUtils.WX_APP_ID);
        wxapi.handleIntent(getIntent(), this);
    }

    public static IWXAPI InitWeiXin(Context context , @NonNull String weixin_app_id){
        if(TextUtils.isEmpty(weixin_app_id)){
//            Toast.makeText(context.getApplicationContext(),"微信appID不能为空",Toast.LENGTH_LONG).show();
        }
        wxapi = WXAPIFactory.createWXAPI(context, weixin_app_id, true);
        wxapi.registerApp(weixin_app_id);
        return wxapi;
    }

    public static  void  loginWeixin(Context context,IWXAPI api){
        if (!api.isWXAppInstalled()){
            Toast.makeText(context.getApplicationContext(),"请先安装微信客户端",Toast.LENGTH_SHORT).show();
        }
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        api.sendReq(req);
    }

    @Override
    public void onReq(BaseReq baseReq) {
        switch (baseReq.getType()) {
            case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
                break;
            case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
                break;
            default:
                break;
        }
    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            // 发送成功
            case BaseResp.ErrCode.ERR_OK:
                // 获取code
                String code = ((SendAuth.Resp) resp).code;
               Intent intent = new Intent(mContext, LoginActivity.class);
               intent.putExtra("type","wechat");
               intent.putExtra("code",code);
               startActivity(intent);
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
        }
    }


}

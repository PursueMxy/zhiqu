package com.icarexm.zhiquwang.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.TeamBean;
import com.icarexm.zhiquwang.contract.DistributionTeamContract;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.DistributionTeamPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DistributionTeamActivity extends BaseActivity implements DistributionTeamContract.View {
    @BindView(R.id.distribution_team_img_head)
    CircleImageView img_head;
    @BindView(R.id.distribution_team_tv_nickname)
    TextView tv_nickname;
    @BindView(R.id.distribution_team_tv_money)
    TextView tv_money;

    private Context mContext;
    private String token;
    private DistributionTeamPresenter distributionTeamPresenter;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distribution_team);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        mContext = getApplicationContext();
        ButterKnife.bind(this);
       LoadingDialogShow();
        distributionTeamPresenter = new DistributionTeamPresenter(this);
        distributionTeamPresenter.GetTeam(token);
    }
    @OnClick({R.id.distribution_team_rl_team,R.id.distribution_team_img_back,R.id.distribution_team_rl_commission
            ,R.id.distribution_team_rl_poster,R.id.distribution_team_img_message})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.distribution_team_rl_team:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_team_rl_team)) {
                    startActivity(new Intent(mContext, MyTeamActivity.class));
                }
                break;
            case R.id.distribution_team_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_team_img_back)) {
                    finish();
                }
                break;
            case R.id.distribution_team_rl_commission:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_team_rl_commission)) {
                    startActivity(new Intent(mContext, CommissionActivity.class));
                }
                break;
            case R.id.distribution_team_rl_poster:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_team_rl_poster)) {
                    startActivity(new Intent(mContext, DistributionPosterActivity.class));
                }
                break;
            case R.id.distribution_team_img_message:
                if (!ButtonUtils.isFastDoubleClick(R.id.distribution_team_img_message)) {
                    startActivity(new Intent(mContext, MessageActivity.class));
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

    public void UpdateUI(int code,String msg, TeamBean.DataBean data){
       LoadingDialogClose();
        if (code==1) {
            tv_money.setText(data.getTotal_commission());
            tv_nickname.setText(data.getUser_name());
            Glide.with(mContext).load(RequstUrl.URL.HOST + data.getAvatar()).into(img_head);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
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

package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.QueryWagesBean;
import com.icarexm.zhiquwang.presenter.QueryWagesPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.dialog.SeleteMothDialog;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.junit.Before;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WagesDetailActivity extends AppCompatActivity implements SeleteMothDialog.DateOnClickListtener {

    @BindView(R.id.wages_detail_tv_company)
    TextView tv_company;
    @BindView(R.id.wages_detail_tv_month_price)
    TextView tv_month_price;
    @BindView(R.id.wages_detail_tv_base_price)
    TextView tv_base_price;
    @BindView(R.id.wages_detail_tv_hour_price)
    TextView tv_hour_price;
    @BindView(R.id.wages_detail_tv_hour)
    TextView tv_hour;
    @BindView(R.id.wages_detail_tv_extra_price)
    TextView tv_extra_price;
    @BindView(R.id.wages_detail_tv_duty_day)
    TextView tv_dudy_day;
    @BindView(R.id.wages_detail_tv_subsidy)
    TextView tv_subsidy;
    @BindView(R.id.wages_detail_tv_deduct_price)
    TextView tv_deduct_price;
    @BindView(R.id.wages_detail_tv_tax_before)
    TextView tv_tax_before;
    @BindView(R.id.wages_detail_tv_actual_price)
    TextView tv_actual_price;
    @BindView(R.id.wages_detail_tv_time)
    TextView tv_time;


    private String month;
    private Context mContext;
    private String token;
    private String name;
    private String card;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wages_detail);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        QueryWagesBean  queryWagesBean = new GsonBuilder().create().fromJson(content, QueryWagesBean.class);
        ButterKnife.bind(this);
        InitUI(queryWagesBean.getData());
    }

    private void InitUI(QueryWagesBean.DataBean data) {
        name = data.getName();
        card = data.getCard();
        num = data.getNum();
        tv_company.setText(data.getCompany());
            tv_month_price.setText(data.getMonth_price());
            tv_base_price.setText(data.getBase_price());
            tv_hour_price.setText(data.getHour_price());
            tv_hour.setText(data.getHour()+"");
            tv_extra_price.setText(data.getExtra_price());
            tv_dudy_day.setText(data.getDuty_day()+"");
            tv_subsidy.setText(data.getSubsidy());
            tv_deduct_price.setText(data.getDeduct_price());
            tv_tax_before.setText(data.getTax_before());
            tv_actual_price.setText(data.getActual_price());
            tv_time.setText(data.getMonth());
            month = data.getMonth();
    }

    @OnClick({R.id.wages_detail_img_back,R.id.wages_detail_tv_time})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.wages_detail_img_back:
                finish();
                break;
            case R.id.wages_detail_tv_time:
                ToastUtils.showToast(mContext,month.substring(0,4));
                SeleteMothDialog seleteMothDialog = new SeleteMothDialog(this, Integer.parseInt(month.substring(0, 4)));
                seleteMothDialog.show();
                seleteMothDialog.setDateOnClickListtener(this);
                break;
        }
    }

    @Override
    public void UpdateDate(String time) {
        tv_time.setText(time);
        PostHonorariumSearch(token,name,card,num,time);
    }


    /**
     * 查询工资
     */
    public void PostHonorariumSearch( String token, String name, String card, String num, String month){
        OkGo.<String>post(RequstUrl.URL.honorariumSearch)
                .params("token",token)
                .params("name",name)
                .params("card",card)
                .params("num",num)
                .params("month",month)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        QueryWagesBean queryWagesBean = new GsonBuilder().create().fromJson(response.body(), QueryWagesBean.class);
                        if (queryWagesBean.getCode()==1) {
                            InitUI(queryWagesBean.getData());
                        }else {
                            ToastUtils.showToast(mContext,"数据为空");
                            tv_month_price.setText("数据为空");
                            tv_base_price.setText("数据为空");
                            tv_hour_price.setText("数据为空");
                            tv_hour.setText("数据为空");
                            tv_extra_price.setText("数据为空");
                            tv_dudy_day.setText("数据为空");
                            tv_subsidy.setText("数据为空");
                            tv_deduct_price.setText("数据为空");
                            tv_tax_before.setText("数据为空");
                            tv_actual_price.setText("数据为空");
                        }
                    }
                });
    }
}

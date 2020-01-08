package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.EntryEnterpriseBean;
import com.icarexm.zhiquwang.bean.PublicDataBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryEnterpriseActivity extends BaseActivity {

    @BindView(R.id.entry_enterprise_tv_name)
    TextView tv_name;
    @BindView(R.id.entry_enterprise_tv_age)
    TextView tv_age;
    @BindView(R.id.entry_enterprise_tv_address)
    TextView tv_address;
    @BindView(R.id.entry_enterprise_tv_today)
    TextView tv_today;
    @BindView(R.id.entry_enterprise_tv_number_day)
    TextView tv_number_day;
    @BindView(R.id.entry_enterprise_tv_salary)
    TextView tv_salary;
    @BindView(R.id.entry_enterprise_labels)
    LabelsView labels;
    @BindView(R.id.entry_enterprise_ll)
    LinearLayout ll_one;
    private String token;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_enterprise);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitData();
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.CurrentEnterprise)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        PublicDataBean publicDataBean = new GsonBuilder().create().fromJson(response.body(), PublicDataBean.class);
                        if (publicDataBean.getCode()==1) {
                            try {
                                EntryEnterpriseBean entryEnterpriseBean = new GsonBuilder().create().fromJson(response.body(), EntryEnterpriseBean.class);
                                EntryEnterpriseBean.DataBean data = entryEnterpriseBean.getData();
                                tv_name.setText(data.getJob_name());
                                tv_address.setText(data.getAddress());
                                tv_age.setText(data.getAge() + "岁");
                                tv_salary.setText(data.getSalary());
                                tv_today.setText(data.getJob_days() + "（已入职" + data.getJob_days() + "天)");
                                tv_number_day.setText(data.getPlay_days() + "天");
                                List<EntryEnterpriseBean.DataBean.LabelArrBean> label_arr = data.getLabel_arr();
                                ArrayList<String> label = new ArrayList<>();
                                for (int a = 0; a < label_arr.size(); a++) {
                                    label.add(label_arr.get(a).getLabel_name());
                                }
                                labels.setLabels(label);
                                ll_one.setVisibility(View.VISIBLE);
                            } catch (Exception e) {
                                ll_one.setVisibility(View.GONE);
                                ToastUtils.showToast(mContext, "获取失败");
                            }
                        }else {
                            ll_one.setVisibility(View.GONE);
                            ToastUtils.showToast(mContext,publicDataBean.getMsg());
                        }
                    }
                });
    }

    @OnClick({R.id.entry_enterprise_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.entry_enterprise_img_back:
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

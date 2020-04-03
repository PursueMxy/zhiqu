package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.QueryWagesBean;
import com.icarexm.zhiquwang.contract.QueryWagesContract;
import com.icarexm.zhiquwang.presenter.QueryWagesPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QueryWagesActivity extends BaseActivity implements QueryWagesContract.View {

    private Context mContext;
    @BindView(R.id.query_wages_edt_name)
    EditText edt_name;
    @BindView(R.id.query_wages_edt_card)
    EditText edt_card;
    @BindView(R.id.query_wages_edt_job_number)
    EditText edt_job_number;
    private QueryWagesPresenter queryWagesPresenter;
    private String token;
    private String monthDateInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_wages);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        queryWagesPresenter = new QueryWagesPresenter(this);
    }

    @OnClick({R.id.query_wages_btn_confirm,R.id.query_wages_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.query_wages_btn_confirm:
                if (!ButtonUtils.isFastDoubleClick(R.id.query_wages_btn_confirm)) {
                    monthDateInt = DateUtils.getMonthDateInt();
                    String name = edt_name.getText().toString();
                    String card = edt_card.getText().toString();
                    String job_number = edt_job_number.getText().toString();
                    if (!name.equals("")) {
                        if (!card.equals("")) {
                            if (!job_number.equals("")) {
                                queryWagesPresenter.honorariumSearch(token, name, card, job_number, monthDateInt);
                            } else {
                                ToastUtils.showToast(mContext, "工号不能为空");
                            }
                        } else {
                            ToastUtils.showToast(mContext, "身份证号不能为空");
                        }
                    } else {
                        ToastUtils.showToast(mContext, "名字不能为空");
                    }
                }
                break;
            case R.id.query_wages_img_back:
                finish();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void UpdateWages(QueryWagesBean queryWagesBean,String content) {
        if (queryWagesBean.getCode()!=0){
            Intent intent = new Intent(mContext, WagesDetailActivity.class);
            intent.putExtra("content",content);
            intent.putExtra("monthDateInt",monthDateInt);
            startActivity(intent);
        }else {
            ToastUtils.showToast(mContext,queryWagesBean.getMsg());
        }
    }
}

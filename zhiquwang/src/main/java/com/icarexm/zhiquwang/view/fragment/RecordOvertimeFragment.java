package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.OverTimeBean;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.BaseActivity;
import com.icarexm.zhiquwang.view.activity.BasePayActivity;
import com.icarexm.zhiquwang.view.activity.LoginActivity;
import com.icarexm.zhiquwang.view.activity.OvertimeApprovalActivity;
import com.icarexm.zhiquwang.view.activity.OvertimeStatisticsActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecordOvertimeFragment extends Fragment implements View.OnClickListener {


    private Context mContext;
    private RelativeLayout rl_typeOne;
    private RelativeLayout rl_typeTwo;
    private String TypeOfWork="1";
    private String token;
    private SharedPreferences share;
    private TextView two_tv_salary;
    private TextView two_tv_dt;
    private TextView tv_price;
    private TextView tv_hour;
    private TextView tv_salary;
    private CustomProgressDialog progressDialog;
    private TextView tv_set_basepay;
    private TextView two_tv_set_basepay;

    public RecordOvertimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        TypeOfWork = share.getString("TypeOfWork", "");
        if (TypeOfWork.equals("")){
            TypeOfWork="1";
        }
        View inflate = inflater.inflate(R.layout.fragment_record_overtime, container, false);
        InitUI(inflate);
        InitData();
        return inflate;
    }

    private void InitData() {
        try {
            if (progressDialog == null){
                progressDialog = CustomProgressDialog.createDialog(mContext);
            }
            progressDialog.show();
        }catch (Exception e){
            progressDialog = null;
        }
        OkGo.<String>post(RequstUrl.URL.Overtime)
                .params("token",token)
                .params("type",TypeOfWork)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (progressDialog != null){
                            progressDialog.dismiss();
                            progressDialog = null;
                        }
                            OverTimeBean overTimeBean = new GsonBuilder().create().fromJson(response.body(), OverTimeBean.class);
                            if (overTimeBean.getCode()==1){
                                if (TypeOfWork.equals("1")){
                                    tv_salary.setText(overTimeBean.getData().getPrice()+"");
                                    tv_hour.setText(overTimeBean.getData().getHours()+"H");
                                    tv_price.setText(overTimeBean.getData().getOver_price()+"");
                                    if (!overTimeBean.getData().getBase_pay().equals("")){
                                        tv_set_basepay.setText("基本工资:\n"+overTimeBean.getData().getBase_pay());
                                    }
                                }else if (TypeOfWork.equals("2")){
                                    two_tv_dt.setText(overTimeBean.getData().getHours()+"H");
                                    two_tv_salary.setText(overTimeBean.getData().getPrice()+"");
                                    if (!overTimeBean.getData().getBase_pay().equals("")){
                                        two_tv_set_basepay.setText("小时工资:\n"+overTimeBean.getData().getBase_pay());
                                    }
                                }
                            }else if (overTimeBean.getCode() ==10001){
                                startActivity(new Intent( getActivity(), LoginActivity.class));
                                getActivity().finish();
                            }
                    }
                });
    }

    private void InitUI(View inflate) {
        rl_typeOne = inflate.findViewById(R.id.fm_recordOvertime_rl_TypeOne);
        rl_typeTwo = inflate.findViewById(R.id.fm_recordOvertime_rl_TypeTwo);
        two_tv_salary = inflate.findViewById(R.id.fm_record_overtime_two_tv_salary);
        two_tv_dt = inflate.findViewById(R.id.fm_record_overtime_two_tv_dt);
        tv_salary = inflate.findViewById(R.id.fm_record_overtime_tv_salary);
        tv_hour = inflate.findViewById(R.id.fm_record_overtime_tv_hour);
        tv_price = inflate.findViewById(R.id.fm_record_overtime_tv_price);
        tv_set_basepay = inflate.findViewById(R.id.fm_record_overtime_tv_set_basepay);
        two_tv_set_basepay = inflate.findViewById(R.id.fm_record_overtime_two_tv_set_basepay);
        inflate.findViewById(R.id.fm_record_overtime_tv_title).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_two_tv_title).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_tv_set_basepay).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_two_tv_set_basepay).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_statistics).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_approval).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_two_tv_statistics).setOnClickListener(this);
        inflate.findViewById(R.id.fm_record_overtime_overtime_two_tv_approval).setOnClickListener(this);
        if (TypeOfWork.equals("2")){
            rl_typeOne.setVisibility(View.GONE);
            rl_typeTwo.setVisibility(View.VISIBLE);
        }else {
            rl_typeTwo.setVisibility(View.GONE);
            rl_typeOne.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fm_record_overtime_tv_set_basepay:
                Intent intent4 = new Intent(mContext, BasePayActivity.class);
                intent4.putExtra("Base_Type",TypeOfWork);
                startActivity(intent4);
                break;
            case R.id.fm_record_overtime_two_tv_set_basepay:
                Intent intent5 = new Intent(mContext, BasePayActivity.class);
                intent5.putExtra("Base_Type",TypeOfWork);
                startActivity(intent5);
                break;
            case R.id.fm_record_overtime_overtime_statistics:
                Intent intent = new Intent(mContext, OvertimeStatisticsActivity.class);
                intent.putExtra("TypeOfWork",TypeOfWork);
                startActivity(intent);
                break;
            case R.id.fm_record_overtime_overtime_approval:
                Intent intent1 = new Intent(mContext, OvertimeApprovalActivity.class);
                intent1.putExtra("TypeOfWork",TypeOfWork);
                startActivity(intent1);
                break;
            case R.id.fm_record_overtime_overtime_two_tv_approval:
                Intent intent2 = new Intent(mContext, OvertimeApprovalActivity.class);
                intent2.putExtra("TypeOfWork",TypeOfWork);
                startActivity(intent2);
                break;
            case R.id.fm_record_overtime_overtime_two_tv_statistics:
                Intent intent3 = new Intent(mContext, OvertimeStatisticsActivity.class);
                intent3.putExtra("TypeOfWork",TypeOfWork);
                startActivity(intent3);
                break;
            case R.id.fm_record_overtime_two_tv_title:
                TypeOfWork="1";
                rl_typeTwo.setVisibility(View.GONE);
                rl_typeOne.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("TypeOfWork",TypeOfWork);
                editor.commit();//提交
                InitData();
                break;
            case R.id.fm_record_overtime_tv_title:
                TypeOfWork="2";
                rl_typeOne.setVisibility(View.GONE);
                rl_typeTwo.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor1 = share.edit();
                editor1.putString("TypeOfWork",TypeOfWork);
                editor1.commit();//提交
                InitData();
                break;
        }
    }

    public void UpdateUI(){
        InitData();
    }


}

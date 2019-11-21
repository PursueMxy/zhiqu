package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.NearbyStoreBean;
import com.icarexm.zhiquwang.bean.PublicResultBean;
import com.icarexm.zhiquwang.custview.LabelsView;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.activity.RecruitDetailActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.util.ArrayList;

public class NearbyStoreAdapter extends HelperRecyclerViewAdapter<NearbyStoreBean.DataBeanX.DataBean> {
    private final String token;
    public Context context;
    public String job_id;

    public NearbyStoreAdapter(Context context,String job_id) {
        super(context, R.layout.list_nearbystore);
        this.context=context;
        this.job_id=job_id;
        SharedPreferences share = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,NearbyStoreBean.DataBeanX.DataBean item) {
       ImageView img_arr = viewHolder.getView(R.id.list_nearby_img_arr);
       TextView tv_storeName= viewHolder.getView(R.id.list_nearby_tv_storeName);
        TextView tv_distance = viewHolder.getView(R.id.list_nearby_tv_distance);
        TextView tv_time = viewHolder.getView(R.id.list_nearby_tv_time);
        TextView tv_address = viewHolder.getView(R.id.list_nearby_tv_address);
        TextView tv_mobile = viewHolder.getView(R.id.list_nearby_tv_mobile);
        tv_storeName.setText("门店名称："+item.getShop_name());
        tv_distance.setText(item.getDistance());
        tv_time.setText("上班时间："+item.getOffice_hours());
        tv_address.setText(item.getAddress());
        tv_mobile.setText(item.getMobile());
        TextView tv_sing_up = viewHolder.getView(R.id.list_nearby_tv_sing_up);
        tv_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkGo.<String>post(RequstUrl.URL.applicationInfo)
                        .params("token",token)
                        .params("job_id",job_id)
                        .execute(new StringCallback() {

                            private int code;
                            private String msg;

                            @Override
                            public void onSuccess(Response<String> response) {
                                PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                msg = resultBean.getMsg();
                                code = resultBean.getCode();
                                if (resultBean.getCode()==1){
                                  enrollDialog();
                                }else if (resultBean.getCode()==10003){
                                    enrollDialog();
                                }else if (resultBean.getCode()==20001){
                                    imperdectDialog();
                                }else {
                                    ToastUtils.showToast(mContext,msg);
                                }
                            }

                            private void enrollDialog() {
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                View dialog_affirm = inflateItemView(R.layout.dialog_affirm, null);
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
                                                    .params("alliance_id", item.getAlliance_id())
                                                    .execute(new StringCallback() {
                                                        @Override
                                                        public void onSuccess(Response<String> response) {
                                                            PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                                            if (resultBean.getCode() == 1) {
                                                                mContext.startActivity(new Intent(mContext, RecruitDetailActivity.class));
                                                            }
                                                            ToastUtils.showToast(mContext, resultBean.getMsg());

                                                        }
                                                    });
                                            alertDialog.dismiss();
                                        }else if (code==20001){
                                            alertDialog.dismiss();
                                        }
                                    }
                                });

                            }


                            private void imperdectDialog(){
                                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                                View dialog_affirm = inflateItemView(R.layout.dialog_imperfect, null);
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
                                                        .params("alliance_id", item.getAlliance_id())
                                                        .params("real_name",name)
                                                        .params("phone",mobile)
                                                        .execute(new StringCallback() {
                                                            @Override
                                                            public void onSuccess(Response<String> response) {
                                                                PublicResultBean resultBean = new GsonBuilder().create().fromJson(response.body(), PublicResultBean.class);
                                                                if (resultBean.getCode() == 1) {
                                                                    mContext.startActivity(new Intent(mContext, RecruitDetailActivity.class));
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
                        });
            }
        });


    }

}

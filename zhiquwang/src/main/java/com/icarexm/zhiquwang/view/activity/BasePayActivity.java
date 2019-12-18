package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.BasePayBean;
import com.icarexm.zhiquwang.bean.LiteSubsidyBean;
import com.icarexm.zhiquwang.bean.LiteWeekBean;
import com.icarexm.zhiquwang.bean.SubsidyBean;
import com.icarexm.zhiquwang.contract.BasePayContract;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.presenter.BasePayPresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasePayActivity extends BaseActivity implements BasePayContract.View {

     @BindView(R.id.base_pay_tv_basic_salary)
    TextView tv_basic_salary;
     @BindView(R.id.base_pay_listView)
     NoScrollListView listView;
     @BindView(R.id.base_pay_edt_pay)
     EditText edt_pay;
     private String Base_Type="1";
    private BasePayPresenter basePayPresenter;
    private String token;
    private List<BasePayBean.DataBean.SubsidyBean> subsidy_list=new ArrayList<>();
    private MyAdapter myAdapter;
    private Context mContext;
    private List<SubsidyBean> addSubsidyList=new ArrayList<>();
    private List<LiteSubsidyBean> all=new ArrayList<>();
    private CustomProgressDialog progressDialog;
    private String pay="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pay);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        Intent intent = getIntent();
        Base_Type = intent.getStringExtra("Base_Type");
        InitUI();
       LoadingDialogShow();
        basePayPresenter = new BasePayPresenter(this);
        basePayPresenter.GetOvertimeInfo(token,Base_Type);
    }

    private void InitUI() {
     if (Base_Type.equals("1")){
         tv_basic_salary.setText("底薪设置");
     }else {
         tv_basic_salary.setText("小时工资");
     }
        myAdapter = new MyAdapter();
     listView.setAdapter(myAdapter);
    }


    @OnClick({R.id.base_pay_img_back,R.id.base_pay_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.base_pay_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.base_pay_img_back)) {
                    finish();
                }
                break;
            case R.id.base_pay_btn_confirm:
                if (!ButtonUtils.isFastDoubleClick(R.id.base_pay_btn_confirm)) {
                    pay = edt_pay.getText().toString();
                    all.clear();
                    addSubsidyList.clear();
                    all = LitePal.findAll(LiteSubsidyBean.class);
                    for (int a = 0; a < all.size(); a++) {
                        Log.e("生活会sdfd", all.get(a).getPrice() + "和" + all.get(a).getSubsidy_name() + "加" + all.get(a).getSubsidy_id());
                        addSubsidyList.add(new SubsidyBean(all.get(a).getSubsidy_id(), all.get(a).getSubsidy_name(), Integer.parseInt(all.get(a).getPrice())));
                    }
                    String Subsidy = new GsonBuilder().create().toJson(addSubsidyList);
                    if (!pay.equals("")) {
                        basePayPresenter.GetsetOvertime(token, Base_Type, pay, Subsidy);
                    } else {
                        ToastUtils.showToast(mContext, "底薪不能为空");
                    }
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

    public void UpdateUI(int code, String msg, BasePayBean.DataBean data){
     LoadingDialogClose();
        if (code==1){
            subsidy_list = data.getSubsidy();
            LitePal.deleteAll(LiteSubsidyBean.class);
            for (int a=0;a<subsidy_list.size();a++){
                LiteSubsidyBean liteSubsidyBean = new LiteSubsidyBean();
                liteSubsidyBean.setSubsidy_id(subsidy_list.get(a).getSubsidy_id());
                liteSubsidyBean.setPrice(subsidy_list.get(a).getPrice()+"");
                liteSubsidyBean.setSubsidy_name(subsidy_list.get(a).getSubsidy_name());
                liteSubsidyBean.save();
            }
            edt_pay.setText(data.getBase_pay()+"");
            all.clear();
            all = LitePal.findAll(LiteSubsidyBean.class);
            myAdapter.notifyDataSetChanged();
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    public void UpdateUI(int code,String msg){
        if (code==1){
            finish();
            Intent intent = new Intent(mContext, HomeActivity.class);
            intent.putExtra("currentItems","1");
            startActivity(intent);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    public  class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return all.size();
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
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.list_base_pay, null);
            TextView  tv_name = (TextView) inflate.findViewById(R.id.list_base_pay_subsidy_name);
            EditText editText= (EditText) inflate.findViewById(R.id.list_base_pay_edt_subsidy_id);
            TextView tv_id = inflate.findViewById(R.id.list_base_pay_tv_id);
            tv_id.setText(all.get(position).getSubsidy_id()+"");
            tv_name.setText(all.get(position).getSubsidy_name()+"");
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    Log.e("修改数据d",charSequence.toString()+"和");
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int i = Integer.parseInt(tv_id.getText().toString());
                    String s = editText.getText().toString();
                    if (s.equals("")||s==null){
                        s="0";
                    }
                    LiteSubsidyBean liteSubsidyBean = new LiteSubsidyBean();
                    liteSubsidyBean.setPrice(s+"");
                    liteSubsidyBean.updateAll("subsidy_id= "+i);
                    Log.e("修改数据",s+"和"+i);
                    all.clear();
                    all = LitePal.findAll(LiteSubsidyBean.class);
                }
            });
            if (all.get(position).getPrice().equals("0")){
               editText.setText("");
            }else {
               editText.setText(all.get(position).getPrice() + "");
            }

            return inflate;
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

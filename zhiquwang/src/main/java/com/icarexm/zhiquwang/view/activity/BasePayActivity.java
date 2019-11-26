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
import com.icarexm.zhiquwang.bean.SubsidyBean;
import com.icarexm.zhiquwang.contract.BasePayContract;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.presenter.BasePayPresenter;
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
                finish();
                break;
            case R.id.base_pay_btn_confirm:
                String pay = edt_pay.getText().toString();
                all = LitePal.findAll(LiteSubsidyBean.class);
                for (int a=0;a< all.size();a++){
                    addSubsidyList.add(new SubsidyBean(all.get(a).getSubsidy_id(),all.get(a).getSubsidy_name(),all.get(a).getPrice()));
                }
                String Subsidy = new GsonBuilder().create().toJson(addSubsidyList);
                if (!pay.equals("")){
                    basePayPresenter.GetsetOvertime(token,Base_Type,pay,Subsidy);
                }else {
                    ToastUtils.showToast(mContext,"底薪不能为空");
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
        if (code==1){
            subsidy_list = data.getSubsidy();
            LitePal.deleteAll(LiteSubsidyBean.class);
            for (int a=0;a<subsidy_list.size();a++){
                LiteSubsidyBean liteSubsidyBean = new LiteSubsidyBean();
                liteSubsidyBean.setSubsidy_id(subsidy_list.get(a).getSubsidy_id());
                liteSubsidyBean.setPrice(subsidy_list.get(a).getPrice());
                liteSubsidyBean.setSubsidy_name(subsidy_list.get(a).getSubsidy_name());
                liteSubsidyBean.save();
            }
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

        @SuppressLint("ViewHolder")
        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            ViewHolder holder;
            if (null == convertView){
                convertView = getLayoutInflater().inflate(R.layout.list_base_pay,null);
                holder =new ViewHolder(convertView,position);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv_name.setText(all.get(position).getSubsidy_name());
            return convertView;
        }

        class ViewHolder{
            TextView tv_name;
            EditText editText;
            public ViewHolder(View view,int pisition){
                tv_name = (TextView) view.findViewById(R.id.list_base_pay_subsidy_name);
                editText= (EditText) view.findViewById(R.id.list_base_pay_edt_subsidy_id);
                editText.setTag(pisition);//存tag值
                editText.addTextChangedListener(new TextSwitcher(this));
            }
        }


        class TextSwitcher implements TextWatcher {
            private ViewHolder mHolder;

            public TextSwitcher(ViewHolder mHolder) {
                this.mHolder = mHolder;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int position = (int) mHolder.editText.getTag();//取tag值
                String subsidy_name = all.get(position).getSubsidy_name();
                LiteSubsidyBean liteSubsidyBean = new LiteSubsidyBean();
                int Price = Integer.parseInt(s.toString());
                liteSubsidyBean.setPrice(Price);
                liteSubsidyBean.updateAll("subsidy_name = ?",subsidy_name);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

        }
    }
}

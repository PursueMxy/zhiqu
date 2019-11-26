package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.MyBankBean;
import com.icarexm.zhiquwang.custview.NoScrollListView;
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

public class BankListActivity extends BaseActivity {

    @BindView(R.id.bank_list_banks)
    NoScrollListView lv_banks;
    private String token;
    private List<MyBankBean.DataBean> bankList=new ArrayList<>();
    private MyAdapter myAdapter;
    private Context mContext;
    private int ADDBANK=20001;
    private int BANKLIST=22222;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitData();
        InitUI();
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        InitData();
    }
    private void InitUI() {
        myAdapter = new MyAdapter();
        lv_banks.setAdapter(myAdapter);
        lv_banks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CashWithdrawalActivity.class);
                intent.putExtra("bank_id",bankList.get(i).getBank_id()+"");
                intent.putExtra("bank_name",bankList.get(i).getBank_name());
                intent.putExtra("bank_num",bankList.get(i).getBank_num());
                setResult(BANKLIST,intent);
                finish();
            }
        });
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.myBank)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        MyBankBean myBankBean = new GsonBuilder().create().fromJson(response.body(), MyBankBean.class);
                        if (myBankBean.getCode()==1){
                            bankList = myBankBean.getData();
                            myAdapter.notifyDataSetChanged();
                        }else if (myBankBean.getCode() ==10001){
                            ToastUtils.showToast(mContext,myBankBean.getMsg());
                            startActivity(new Intent(mContext,LoginActivity.class));
                            finish();
                        }
                    }
                });
    }

    @OnClick({R.id.bank_list_img_back,R.id.bank_list_rl_add})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.bank_list_img_back:
                finish();
                break;
            case R.id.bank_list_rl_add:
                Intent intent = new Intent(mContext, AddBankCardActivity.class);
                intent.putExtra("type","20001");
                startActivityForResult(intent,20001);
                break;
        }
    }

    public class  MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return bankList.size();
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
        public View getView(int i, View view, ViewGroup viewGroup) {
            View inflate = getLayoutInflater().inflate(R.layout.list_banks, null);
            TextView tv_bank_name = inflate.findViewById(R.id.list_banks_tv_bank_name);
            TextView tv_bank_num = inflate.findViewById(R.id.list_banks_tv_bank_num);
            tv_bank_name.setText(bankList.get(i).getBank_name());
            tv_bank_num.setText(bankList.get(i).getBank_num());
            return inflate;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==ADDBANK&&resultCode==ADDBANK){
            InitData();
        }
    }
}

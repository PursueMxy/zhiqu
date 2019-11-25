package com.icarexm.zhiquwang.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.ListStatisticsAdapter;
import com.icarexm.zhiquwang.adapter.StatisticsAdapter;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;
import com.icarexm.zhiquwang.bean.StatisticsBean;
import com.icarexm.zhiquwang.contract.OvertimeStatisticsContract;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.presenter.OvertimeStatisticsPresenter;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OvertimeStatisticsActivity extends BaseActivity implements OvertimeStatisticsContract.View {

    @BindView(R.id.overtime_statistics_time)
    TextView tv_time;
    @BindView(R.id.overtime_statistics_listView)
    NoScrollListView listView_statistics;
    @BindView(R.id.overtime_statistics_elv)
    ExpandableListView statistics_elv;
    private String token;
    private OvertimeStatisticsPresenter overtimeStatisticsPresenter;
    private String typeOfWork;
    private List<StatisticsBean.DataBean.TotalInfoBean> total_info=new ArrayList<>();
    private MyAdapter myAdapter;
    private Context mContext;
    private StatisticsAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overtime_statistics);
        ButterKnife.bind(this);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        typeOfWork = intent.getStringExtra("TypeOfWork");
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        String monthDate = DateUtils.getMonthDate();
        tv_time.setText(monthDate);
        initExpandableListView();
        overtimeStatisticsPresenter = new OvertimeStatisticsPresenter(this);
        overtimeStatisticsPresenter.GetRecords(token,typeOfWork);
    }


    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        myOrderAdapter = new StatisticsAdapter(mContext,total_info);
        statistics_elv.setAdapter(myOrderAdapter);
    }

    @OnClick({R.id.overtime_sss_img_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.overtime_sss_img_back:
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String message, StatisticsBean.DataBean data){
        if (code==1) {
            total_info = data.getTotal_info();
//            myAdapter.notifyDataSetChanged();
            myOrderAdapter = new StatisticsAdapter(mContext,total_info);
            statistics_elv.setAdapter(myOrderAdapter);
        }else {
            ToastUtils.showToast(mContext,message);
        }
    }

    public class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return total_info.size();
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
            View inflate = getLayoutInflater().inflate(R.layout.list_statistics, null);
            RecyclerView recyclerView = inflate.findViewById(R.id.list_recyclerView);
            List<StatisticsBean.DataBean.TotalInfoBean.InfoBean> info = total_info.get(i).getInfo();
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            ListStatisticsAdapter listStatisticsAdapter = new ListStatisticsAdapter(mContext, info);
            recyclerView.setAdapter(listStatisticsAdapter);
            return inflate;
        }

    }
}

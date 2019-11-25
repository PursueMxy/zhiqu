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
import android.widget.BaseExpandableListAdapter;
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
    @BindView(R.id.overtime_statistics_elv)
    ExpandableListView statistics_elv;
    private String token;
    private OvertimeStatisticsPresenter overtimeStatisticsPresenter;
    private String typeOfWork;
    private List<StatisticsBean.DataBean.TotalInfoBean> total_info = new ArrayList<>();
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
        overtimeStatisticsPresenter.GetRecords(token, typeOfWork);
    }


    /**
     * 初始化ExpandableListView
     * 创建数据适配器adapter，并进行初始化操作
     */
    private void initExpandableListView() {
        initExpandableListViewData(total_info);
        myOrderAdapter = new StatisticsAdapter(mContext);
        statistics_elv.setAdapter(myOrderAdapter);
    }

    /**
     * 初始化ExpandableListView的数据
     * 并在数据刷新时，页面保持当前位置
     *
     * @param datas 购物车的数据
     */
    private void initExpandableListViewData(List<StatisticsBean.DataBean.TotalInfoBean> datas) {
        if (datas != null && datas.size() > 0) {
            //刷新数据时，保持当前位置
            myOrderAdapter.setData(datas);
            //使所有组展开
            for (int i = 0; i < myOrderAdapter.getGroupCount(); i++) {
                statistics_elv.expandGroup(i);
            }

            //使组点击无效果（true）
            statistics_elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                @Override
                public boolean onGroupClick(ExpandableListView parent, View v,
                                            int groupPosition, long id) {
//                    Log.e("点击事件",groupPosition+"加"+id);
                    return true;
                }
            });
        }
    }

    @OnClick({R.id.overtime_sss_img_back})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.overtime_sss_img_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void UpdateUI(int code, String message, StatisticsBean.DataBean data) {
        if (code == 1) {
            total_info = data.getTotal_info();
            initExpandableListViewData(total_info);
        } else {
            ToastUtils.showToast(mContext, message);
        }
    }
}

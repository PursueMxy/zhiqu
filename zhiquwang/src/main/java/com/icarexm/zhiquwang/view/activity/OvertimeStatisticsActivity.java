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
import android.view.LayoutInflater;
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
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.NoScrollListView;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.presenter.OvertimeStatisticsPresenter;
import com.icarexm.zhiquwang.utils.DateUtils;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OvertimeStatisticsActivity extends BaseActivity implements OvertimeStatisticsContract.View {

    @BindView(R.id.overtime_statistics_time)
    TextView tv_time;
    @BindView(R.id.overtime_statistics_elv)
    ExpandableListView statistics_elv;
    @BindView(R.id.overtime_statistics_tv_money)
    TextView tv_money;
    @BindView(R.id.overtime_statistics_total_day)
    TextView tv_total_day;
    private String token;
    private OvertimeStatisticsPresenter overtimeStatisticsPresenter;
    private String typeOfWork;
    private List<StatisticsBean.DataBean.TotalInfoBean> total_info = new ArrayList<>();
    private Context mContext;
    private StatisticsAdapter myOrderAdapter;
    private CustomProgressDialog progressDialog;
    private int StartYear=2019;
    private int StopYear=2050;
    private String SltYear="1990年";
    private String SltMonth="0月";
    private String[] SltNull=new String[]{"请先选择上级"};
    private static final String[] BIRTH_MONTH=new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    private List<String> YearList=new ArrayList<>();

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
        InitYear();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        initExpandableListView();
        overtimeStatisticsPresenter = new OvertimeStatisticsPresenter(this);
        overtimeStatisticsPresenter.GetRecords(token, typeOfWork,"");
    }

    private void InitYear() {
        for (int a=StartYear;a<StopYear;a++){
            YearList.add(a+"年");
        }
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

    @OnClick({R.id.overtime_sss_img_back,R.id.overtime_statistics_time})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.overtime_sss_img_back:
                finish();
                break;
            case R.id.overtime_statistics_time:
                    BirthDateDialog();
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


    private void BirthDateDialog(){
        SltYear="1990";
        SltMonth="01";
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_birth_date, null);
        MyWheelView groupwva_one = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_one);
        groupwva_one.setItems(YearList,92);
        MyWheelView groupwva_two = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_two);
        groupwva_two.setItems(Arrays.asList(SltNull),0);
        groupwva_one.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                SltYear=item;
                SltMonth="01";
                groupwva_two.setItems(Arrays.asList(BIRTH_MONTH),0);
            }
        });
        groupwva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                if(item.equals("请先选择上级")){
                    item="01";
                }
                SltMonth=item;
            }
        });
        BirthDateInflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_groupDialog.dismiss();
            }
        });
        BirthDateInflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_time.setText( SltYear+SltMonth);
                try {
                    long times = DateUtils.dateYearMoth(SltYear + SltMonth);
                    overtimeStatisticsPresenter.GetRecords(token, typeOfWork,times+"");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                age_groupDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (age_groupDialog !=null && age_groupDialog.isShowing()) {
            return;
        }

        age_groupDialog.setContentView(BirthDateInflate);
        age_groupDialog.show();
    }

    public void UpdateUI(int code, String message, StatisticsBean.DataBean data) {
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (code == 1) {
            total_info = data.getTotal_info();
            initExpandableListViewData(total_info);
            tv_money.setText(data.getTotal_price()+"");
            tv_total_day.setText("加班"+data.getTotal_day()+"天 共"+data.getTotal_time()+"小时");
        } else if (code ==10001){
            ToastUtils.showToast(mContext,message);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }else {
            ToastUtils.showToast(mContext, message);
        }
    }
}

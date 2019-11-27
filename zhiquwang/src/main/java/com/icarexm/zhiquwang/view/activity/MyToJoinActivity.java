package com.icarexm.zhiquwang.view.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.AllianceInfoBean;
import com.icarexm.zhiquwang.bean.AreaBean;
import com.icarexm.zhiquwang.bean.CityNameBean;
import com.icarexm.zhiquwang.bean.RegionBean;
import com.icarexm.zhiquwang.contract.MyToJoinContract;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.presenter.MyToJoinPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyToJoinActivity extends BaseActivity implements MyToJoinContract.View {
     @BindView(R.id.my_to_join_ll_TypeOne)
     LinearLayout ll_typeOne;
     @BindView(R.id.my_to_join_rl_TypeTwo)
     RelativeLayout rl_typeTwo;
     @BindView(R.id.my_to_join_rl_TypeThree)
     RelativeLayout rl_typeThree;
     @BindView(R.id.my_to_join_edt_name)
     EditText edt_name;
     @BindView(R.id.my_to_join_edt_mobile)
     EditText edt_mobile;
     @BindView(R.id.my_to_join_edt_area)
     TextView tv_area;
     private int Type=1;
    private Context mContext;
    private MyToJoinPresenter myToJoinPresenter;
    private String token;
    private String ProvinceName;
    private String CityName;
    private List<String> provinceList=new ArrayList<>();
    private List<String> CityList=new ArrayList<>();
    private String[] SltNull=new String[]{"请先选择上级"};
    private List<CityNameBean.DataBean> city_list=new ArrayList<>();
    private MyWheelView groupwva_two;
    private List<AreaBean.DataBean> area_list=new ArrayList<>();
    private CustomProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_join);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        InitUI();
        //加载页添加
        if (progressDialog == null){
            progressDialog = CustomProgressDialog.createDialog(this);
        }
        progressDialog.show();
        myToJoinPresenter = new MyToJoinPresenter(this);
        myToJoinPresenter.GetallianceInfo(token);
    }

    private void InitData() {
        OkGo.<String>post(RequstUrl.URL.getCity)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        provinceList.clear();
                        CityNameBean cityNameBean = new GsonBuilder().create().fromJson(response.body(), CityNameBean.class);
                        if (cityNameBean.getCode()==1){
                            city_list = cityNameBean.getData();
                            for (int a=0;a<city_list.size();a++){
                                provinceList.add(city_list.get(a).getArea_name());
                            }
                            AddressDialog();
                        }
                    }
                });
    }

    public void GetArea(String area_id){
        OkGo.<String>post(RequstUrl.URL.getArea)
                .params("token",token)
                .params("area_id",area_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        AreaBean areaBean = new GsonBuilder().create().fromJson(response.body(), AreaBean.class);
                        CityList.clear();
                        if (areaBean.getCode()==1){
                            area_list = areaBean.getData();
                            for (int b=0;b<area_list.size();b++){
                                CityList.add(area_list.get(b).getArea_name());
                            }
                            groupwva_two.setItems(CityList,0);
                        }
                    }
                });
    }

    private void InitUI() {
        if (Type==1){
            rl_typeThree.setVisibility(View.GONE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.VISIBLE);
        }else if (Type==2){
            rl_typeTwo.setVisibility(View.VISIBLE);
            rl_typeThree.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }else if (Type==3){
            rl_typeThree.setVisibility(View.VISIBLE);
            rl_typeTwo.setVisibility(View.GONE);
            ll_typeOne.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.my_to_join_img_back,R.id.my_to_join_btn_confirm,R.id.my_to_join_rl_address})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_to_join_img_back:
                finish();
                break;
            case R.id.my_to_join_btn_confirm:
                String name = edt_name.getText().toString();
                String mobile= edt_mobile.getText().toString();
                String area = tv_area.getText().toString();
                if (!name.equals("")){
                 if (!mobile.equals("")){
                   if (!area.equals("")){
                       myToJoinPresenter.GetMyTojoin(token,name,mobile,area);
                   }else {
                       ToastUtils.showToast(mContext,"申请区域不能为空");
                   }
                 }else {
                     ToastUtils.showToast(mContext,"申请人电话不能为空");
                 }
                }else {
                    ToastUtils.showToast(mContext,"申请人不能为空");
                }
                break;
            case R.id.my_to_join_rl_address:
                //获取可加盟城市
                InitData();
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

    public void UpdateUI(int code , String msg, int type, AllianceInfoBean.DataBean data){
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        if (type==2){
            if (code==1){
                myToJoinPresenter.GetallianceInfo(token);
            }else {
                ToastUtils.showToast(mContext,msg);
            }
        }else {
            if (code==1){
                int audit = data.getAudit();
                if (audit==0){
                    rl_typeThree.setVisibility(View.GONE);
                    rl_typeTwo.setVisibility(View.GONE);
                    ll_typeOne.setVisibility(View.VISIBLE);
                }else if (audit==1){
                    rl_typeTwo.setVisibility(View.VISIBLE);
                    rl_typeThree.setVisibility(View.GONE);
                    ll_typeOne.setVisibility(View.GONE);
                }else if (audit==2){
                    rl_typeThree.setVisibility(View.VISIBLE);
                    rl_typeTwo.setVisibility(View.GONE);
                    ll_typeOne.setVisibility(View.GONE);
                }else if (audit==3){
                    rl_typeThree.setVisibility(View.GONE);
                    rl_typeTwo.setVisibility(View.GONE);
                    ll_typeOne.setVisibility(View.VISIBLE);
                    ToastUtils.showToast(mContext,msg);
                    tv_area.setText(data.getArea());
                     edt_mobile.setText(data.getMobile()+"");
                     edt_name.setText(data.getName());
                }
            }else {
                ToastUtils.showToast(mContext,msg);
            }
        }
       if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }

    private void AddressDialog() {
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_address, null);
        MyWheelView groupwva_one = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_one);
        groupwva_one.setItems(provinceList,0);
        groupwva_two = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_two);
        groupwva_two.setItems(Arrays.asList(SltNull),0);
        groupwva_one.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                ProvinceName =item;
                GetArea(city_list.get(selectedIndex).getArea_id()+"");
            }
        });
        groupwva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                CityName =item;
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
                if (!CityName.equals("")) {
                    tv_area.setText(ProvinceName + CityName);
                    age_groupDialog.dismiss();
                }else {
                    ToastUtils.showToast(mContext,"你没有修改地址");
                }
            }
        });
        //防止弹出两个窗口
        if (age_groupDialog !=null && age_groupDialog.isShowing()) {
            return;
        }

        age_groupDialog.setContentView(BirthDateInflate);
        age_groupDialog.show();
    }
}

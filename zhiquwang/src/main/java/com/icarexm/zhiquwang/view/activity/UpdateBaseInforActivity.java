package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.RegionBean;
import com.icarexm.zhiquwang.bean.UploadImgBean;
import com.icarexm.zhiquwang.custview.BottomDialog;
import com.icarexm.zhiquwang.custview.CircleImageView;
import com.icarexm.zhiquwang.custview.mywheel.MyWheelView;
import com.icarexm.zhiquwang.utils.GifSizeFilter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateBaseInforActivity extends BaseActivity {

     @BindView(R.id.update_base_infor_tv_education)
    TextView tv_education;
     @BindView(R.id.update_base_infor_tv_birth_date)
     TextView tv_birth_date;
     @BindView(R.id.update_base_infor_tv_sex)
     TextView tv_sex;
     @BindView(R.id.update_base_tv_address)
     TextView tv_address;
     @BindView(R.id.update_base_tv_nickname)
     TextView tv_nickname;
     @BindView(R.id.update_base_tv_mobile)
     TextView tv_mobile;
     @BindView(R.id.base_information_img_avatar)
    CircleImageView img_avatar;
    private Context mContext;
    private static final String[] AGEGROUP = new String[]{"博士","硕士","本科","大专", "高中","中专","初中及以下"};
    private String ageGroupString="本科";
    private static final String[] BIRTH_MONTH=new String[]{"1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"};
    private List<String> YearList=new ArrayList<>();
    private int StartYear=1900;
    private int StopYear=2050;
    private String SltYear="1990年";
    private String SltMonth="0月";
    private String[] SEX_LIST=new String[]{"男","女"};
    private String sex="男";
    private String[] SltNull=new String[]{"请先选择上级"};
    private static final Gson gson = new Gson();
    private static final JsonParser parser = new JsonParser();
    private static final String jsonFile = "region.json";// assets文件夹中
    private List<String> provinceList=new ArrayList<>();
    private List<String> CityList=new ArrayList<>();
    private List<RegionBean> jsonData=new ArrayList<>();
    private int  BASEINFOCODE=5121;
    private String ProvinceName="";
    private String CityName="";
    private String city;
    private String avatar;
    private String real_name;
    private String birth;
    private String education;
    private String mobile;
    private static final int REQUEST_CODE=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_base_infor);
        mContext = getApplicationContext();
        Intent intent = getIntent();
        avatar = intent.getStringExtra("avatar");
        city = intent.getStringExtra("city");
        real_name = intent.getStringExtra("real_name");
        sex = intent.getStringExtra("sex");
        birth = intent.getStringExtra("birth");
        education = intent.getStringExtra("education");
        mobile = intent.getStringExtra("mobile");
        ButterKnife.bind(this);
        //权限申请
        XXPermissions.with(this)
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
        InitYear();
        InitAddress();
        tv_education.setText(education);
        tv_sex.setText(sex);
        tv_birth_date.setText(birth);
        tv_address.setText(city);
        tv_nickname.setText(real_name);
        tv_mobile.setText(mobile);
        Glide.with(mContext).load(RequstUrl.URL.HOST+avatar).into(img_avatar);
    }

    private void InitAddress() {
        jsonData = getJsonData(mContext);
        for(int a=0;a<jsonData.size();a++){
            provinceList.add(jsonData.get(a).provinceName);
        }
    }
    private void cityList(List<RegionBean.City> citys) {
        CityList.clear();
        for(int a=0;a<citys.size();a++){
            CityList.add(citys.get(a).cityName);
        }
    }

    private void InitYear() {
        for (int a=StartYear;a<StopYear;a++){
            YearList.add(a+"年");
        }
    }

    public static List<RegionBean> getJsonData(Context context) {
        List<RegionBean> result = new ArrayList<>();
        try {
            String jsonString = loadJson2Str(context, jsonFile);
            JsonArray jsonArray = stringToJsonArray(jsonString);
            if (jsonArray == null) {
                return result;
            }

            for (JsonElement j : jsonArray) {
                result.add(gson.fromJson(j, RegionBean.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String loadJson2Str(Context context, String fileName) {
        String result = "";
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = context.getAssets().open(fileName);
            bos = new ByteArrayOutputStream();
            byte[] bytes = new byte[4 * 1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            result = new String(bos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
                if (bos != null)
                    bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static JsonArray stringToJsonArray(String str) {
        try {
            return parser.parse(str).getAsJsonArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @OnClick({R.id.update_base_infor_img_back,R.id.update_base_infor_rl_education,R.id.update_base_rl_time,
            R.id.update_base_rl_sex,R.id.update_base_rl_address,R.id.update_base_btn_confirm,
        R.id.base_information_img_avatar})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.update_base_infor_img_back:
                finish();
                break;
            case R.id.update_base_infor_rl_education:
                EducationDialog();
                break;
            case R.id.update_base_rl_time:
                BirthDateDialog();
                break;
            case R.id.update_base_rl_sex:
                SexDialog();
                break;
            case R.id.update_base_rl_address:
               AddressDialog();
                break;
            case R.id.base_information_img_avatar:
                try{
                Matisse.from(this)
                        .choose(MimeType.ofImage(), false)
                        .countable(true)
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true, "com.zhkj.syyj.fileprovider", "test"))
                        .maxSelectable(1)
                        .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                        .gridExpectedSize(getResources().getDimensionPixelSize(R.dimen.dp_110))
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .showSingleMediaType(true)
                        .originalEnable(true)
                        .maxOriginalSize(10)
                        .autoHideToolbarOnSingleTap(true)
                        .forResult(REQUEST_CODE); }catch (Exception e){
                    //权限申请
                    ToastUtils.showToast(mContext,"请允许权限");
                    XXPermissions.with(this)
                            .request(new OnPermission() {

                                @Override
                                public void hasPermission(List<String> granted, boolean isAll) {

                                }

                                @Override
                                public void noPermission(List<String> denied, boolean quick) {

                                }
                            });
                }
                break;
            case R.id.update_base_btn_confirm:
                String sex= tv_sex.getText().toString();
                String address = tv_address.getText().toString();
                String birth_date= tv_birth_date.getText().toString();
                String education = tv_education.getText().toString();
                if (!address.equals("")){
                    if (!sex.equals("")){
                       if (!birth_date.equals("")){
                           if (!education.equals("")){
                               Intent intent = new Intent(mContext, MyResumeActivity.class);
                               intent.putExtra("sex",sex);
                               intent.putExtra("address",address);
                               intent.putExtra("birth_date",birth_date);
                               intent.putExtra("education",education);
                               intent.putExtra("avatar",avatar);
                               setResult(BASEINFOCODE,intent);
                               finish();
                           }else {
                               ToastUtils.showToast(mContext,"学历不能为空");
                           }
                       }else {
                           ToastUtils.showToast(mContext,"出生年月不能为空");
                       }
                    }else {
                        ToastUtils.showToast(mContext,"性别不能为空");
                    }
                }else {
                    ToastUtils.showToast(mContext,"地址不能为空");
                }
                break;
        }
    }

    private void AddressDialog() {
        ProvinceName="";
        CityName="";
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View  BirthDateInflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_address, null);
        MyWheelView groupwva_one = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_one);
        groupwva_one.setItems(provinceList,0);
        MyWheelView groupwva_two = BirthDateInflate.findViewById(R.id.dialog_bottom_wheel_two);
        groupwva_two.setItems(Arrays.asList(SltNull),0);
        groupwva_one.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                ProvinceName=item;
                RegionBean regionBean = jsonData.get(selectedIndex);
                cityList(regionBean.citys);
                groupwva_two.setItems(CityList,0);
            }
        });
        groupwva_two.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                CityName=item;
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
                    tv_address.setText(ProvinceName + CityName);
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

    private void EducationDialog() {
        ageGroupString="本科";
        tv_education.setText(ageGroupString);
        final BottomDialog age_groupDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View age_groupinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title = age_groupinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("学历");
        MyWheelView age_groupwva = age_groupinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Arrays.asList(AGEGROUP),2);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                ageGroupString=item;
            }
        });
        age_groupinflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                age_groupDialog.dismiss();
            }
        });
        age_groupinflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_education.setText(ageGroupString);
                age_groupDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (age_groupDialog !=null && age_groupDialog.isShowing()) {
            return;
        }
        age_groupDialog.setContentView(age_groupinflate);
        age_groupDialog.show();
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
                tv_birth_date.setText( SltYear+SltMonth);
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

    private void SexDialog() {
        final BottomDialog SexDialog = new BottomDialog(this, R.style.ActionSheetDialogStyle);
        View Sexinflate = LayoutInflater.from(mContext).inflate(R.layout.dialog_bottom_education, null);
        TextView tv_title =Sexinflate.findViewById(R.id.dialog_bottom_education_tv_title);
        tv_title.setText("性别");
        MyWheelView age_groupwva = Sexinflate.findViewById(R.id.dialog_bottom_wheel);
        age_groupwva.setItems(Arrays.asList(SEX_LIST),0);
        age_groupwva.setOnItemSelectedListener(new MyWheelView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int selectedIndex, String item) {
                sex=item;
            }
        });
        Sexinflate.findViewById(R.id.dialog_bottom_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SexDialog.dismiss();
            }
        });
        Sexinflate.findViewById(R.id.dialog_bottom_img_opt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_sex.setText(sex);
                SexDialog.dismiss();
            }
        });
        //防止弹出两个窗口
        if (SexDialog !=null && SexDialog.isShowing()) {
            return;
        }

        SexDialog.setContentView(Sexinflate);
        SexDialog.show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_CODE:
                    List<Uri> uris = Matisse.obtainResult(data);
                    if (uris.size()>0) {
                        Glide.with(this).load(uris.get(0)).into(img_avatar);
                        List<String> strings = Matisse.obtainPathResult(data);
                        File file = new File(strings.get(0));//实例化数据库文件
                        OkGo.<String>post(RequstUrl.URL.UploadImg)
                                .params("img",file)
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        UploadImgBean uploadImgBean = new GsonBuilder().create().fromJson(response.body(), UploadImgBean.class);
                                        UploadImgBean.DataBean DataBean= uploadImgBean.getData();
                                        avatar = DataBean.getUrl();
                                    }
                                });
                    }
                    break;
                default:
            }
        }
    }
}

package com.icarexm.zhiquwang.view.activity;

import androidx.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.adapter.MyResumeAdapter;
import com.icarexm.zhiquwang.adapter.OnItemClickListener;
import com.icarexm.zhiquwang.adapter.TodayHeatAdapter;
import com.icarexm.zhiquwang.bean.AddResumeBean;
import com.icarexm.zhiquwang.bean.ResumeBean;
import com.icarexm.zhiquwang.contract.MyResumeContract;
import com.icarexm.zhiquwang.presenter.MyResumePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyResumeActivity extends BaseActivity implements MyResumeContract.View {

    private Context mContext;
    @BindView(R.id.my_resume_recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.my_resume_tv_name)
    TextView tv_name;
    @BindView(R.id.my_resume_tv_education)
    TextView tv_education;
    @BindView(R.id.my_resume_img_avatar)
    ImageView img_acatar;
    @BindView(R.id.my_resume_edt_personal_introduce)
    EditText edt_personal_introduce;
    private int  ADDWORKHISTORY=5120;
    private int  BASEINFOCODE=5121;
    private List<ResumeBean.DataBean.ExperienceBean> addResumeList=new ArrayList<>();
    private MyResumeAdapter myResumeAdapter;
    private String experience="";
    private MyResumePresenter myResumePresenter;
    private String token;
    private String avatar;
    private String real_name;
    private String sex;
    private String birth;
    private String city;
    private String education;
    private String mobile;
    private String address;
    private String birth_date;
    private String age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);
        mContext = getApplicationContext();
        ButterKnife.bind(this);
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        InitUI();
        myResumePresenter = new MyResumePresenter(this);
        myResumePresenter.GetMineInfo(token);
    }



    private void InitUI() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecyclerView.setLayoutManager(layoutManager);
        myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
        mRecyclerView.setAdapter(myResumeAdapter);
        myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                startActivityForResult(intent,ADDWORKHISTORY);
            }
        });
    }

    @OnClick({R.id.my_resume_img_edit,R.id.my_resume_add_workHistory,R.id.my_resume_img_back,R.id.my_resume_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_resume_img_edit:
                Intent intent1 = new Intent(mContext, UpdateBaseInforActivity.class);
                intent1.putExtra("avatar",avatar);
                intent1.putExtra("city",city);
                intent1.putExtra("real_name",real_name);
                intent1.putExtra("sex",sex);
                intent1.putExtra("birth",birth);
                intent1.putExtra("education",education);
                intent1.putExtra("mobile",mobile);
                startActivityForResult(intent1,BASEINFOCODE);
                break;
            case R.id.my_resume_img_back:
                finish();
                break;
            case R.id.my_resume_add_workHistory:
                Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                startActivityForResult(intent,ADDWORKHISTORY);
                break;
            case R.id.my_resume_btn_confirm:
                String personal_introduce = edt_personal_introduce.getText().toString();
                myResumePresenter.GetAddResume(token,avatar,real_name,sex,birth_date,address,education,personal_introduce,experience);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==ADDWORKHISTORY&&resultCode==ADDWORKHISTORY){
                String bean = data.getStringExtra("bean");
                addResumeList.add(new GsonBuilder().create().fromJson(bean,ResumeBean.DataBean.ExperienceBean.class));
                experience = new GsonBuilder().create().toJson(addResumeList);
                myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
                mRecyclerView.setAdapter(myResumeAdapter);
                myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                        startActivityForResult(intent,ADDWORKHISTORY);
                    }
                });
            }else  if (requestCode==BASEINFOCODE&&resultCode==BASEINFOCODE){
                sex = data.getStringExtra("sex");
                address = data.getStringExtra("address");
                birth_date = data.getStringExtra("birth_date");
                education = data.getStringExtra("education");
                tv_education.setText(age+education);
            }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void  UpdateUI(int code,String msg, ResumeBean.DataBean data){
        if (code==1){
         tv_name.setText(data.getUser_name());
         tv_education.setText(data.getEducation());
          Glide.with(mContext).load(data.getAvatar()).into(img_acatar);
          avatar = data.getAvatar();
          real_name = data.getUser_name();
          birth = data.getBirth();
          education = data.getEducation();
          city = data.getCity();
          sex = data.getSex();
          age = data.getAge();
          mobile = data.getMobile();
          tv_education.setText(age+education);
          addResumeList= data.getExperience();
          myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
          mRecyclerView.setAdapter(myResumeAdapter);
          myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                    startActivityForResult(intent,ADDWORKHISTORY);
                }
            });
        }
    }
}

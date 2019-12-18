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
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.presenter.MyResumePresenter;
import com.icarexm.zhiquwang.utils.ButtonUtils;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;

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
    private String age;
    private CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_resume);
        mContext = getApplicationContext();
        SharedPreferences share = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        token = share.getString("token", "");
        ButterKnife.bind(this);
        InitUI();
       LoadingDialogShow();
        myResumePresenter = new MyResumePresenter(this);
        myResumePresenter.GetMineInfo(token);
    }



    private void InitUI() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
        mRecyclerView.setAdapter(myResumeAdapter);
        myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                intent.putExtra("type","update");
                intent.putExtra("company_name",addResumeList.get(position).getCompany_name());
                intent.putExtra("job_content",addResumeList.get(position).getJob_content());
                intent.putExtra("job_start",addResumeList.get(position).getJob_start());
                intent.putExtra("job_end",addResumeList.get(position).getJob_end());
                intent.putExtra("leave_cause",addResumeList.get(position).getLeave_cause());
                intent.putExtra("position",position+"");
                startActivityForResult(intent,ADDWORKHISTORY);
            }
        });
    }

    @OnClick({R.id.my_resume_img_edit,R.id.my_resume_add_workHistory,R.id.my_resume_img_back,R.id.my_resume_btn_confirm})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.my_resume_img_edit:
                if (!ButtonUtils.isFastDoubleClick(R.id.my_resume_img_edit)) {
                    Intent intent1 = new Intent(mContext, UpdateBaseInforActivity.class);
                    intent1.putExtra("avatar", avatar);
                    intent1.putExtra("city", city);
                    intent1.putExtra("real_name", real_name);
                    intent1.putExtra("sex", sex);
                    intent1.putExtra("birth", birth);
                    intent1.putExtra("education", education);
                    intent1.putExtra("mobile", mobile);
                    startActivityForResult(intent1, BASEINFOCODE);
                }
                break;
            case R.id.my_resume_img_back:
                if (!ButtonUtils.isFastDoubleClick(R.id.myjobsearch_img_back)) {
                    finish();
                }
                break;
            case R.id.my_resume_add_workHistory:
                if (!ButtonUtils.isFastDoubleClick(R.id.my_resume_add_workHistory)) {
                    Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                    intent.putExtra("type", "add");
                    startActivityForResult(intent, ADDWORKHISTORY);
                }
                break;
            case R.id.my_resume_btn_confirm:
                if (!ButtonUtils.isFastDoubleClick(R.id.my_resume_btn_confirm)) {
                    String personal_introduce = edt_personal_introduce.getText().toString();
                    myResumePresenter.GetAddResume(token, avatar, real_name, sex, birth, city, education, personal_introduce, experience);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode==ADDWORKHISTORY&&resultCode==ADDWORKHISTORY){
                String bean = data.getStringExtra("bean");
                String position = data.getStringExtra("position");
                if (!position.equals("00000")){
                    addResumeList.remove(Integer.parseInt(position));
                }
                addResumeList.add(new GsonBuilder().create().fromJson(bean,ResumeBean.DataBean.ExperienceBean.class));
                experience = new GsonBuilder().create().toJson(addResumeList);
                myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
                mRecyclerView.setAdapter(myResumeAdapter);
                myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                        intent.putExtra("type","update");
                        intent.putExtra("company_name",addResumeList.get(position).getCompany_name());
                        intent.putExtra("job_content",addResumeList.get(position).getJob_content());
                        intent.putExtra("job_start",addResumeList.get(position).getJob_start());
                        intent.putExtra("job_end",addResumeList.get(position).getJob_end());
                        intent.putExtra("leave_cause",addResumeList.get(position).getLeave_cause());
                        intent.putExtra("position",position+"");
                        startActivityForResult(intent,ADDWORKHISTORY);
                    }
                });
            }else  if (requestCode==BASEINFOCODE&&resultCode==BASEINFOCODE){
                sex = data.getStringExtra("sex");
                city = data.getStringExtra("address");
                birth = data.getStringExtra("birth_date");
                education = data.getStringExtra("education");
                avatar=data.getStringExtra("avatar");
                tv_education.setText(age+education);
                Glide.with(mContext).load(RequstUrl.URL.HOST+avatar).into(img_acatar);
            }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void  UpdateUI(int code,String msg, ResumeBean.DataBean data){
       LoadingDialogClose();
        if (code==1){
         tv_name.setText(data.getUser_name());
          tv_education.setText(data.getEducation());
          Glide.with(mContext).load(RequstUrl.URL.HOST+data.getAvatar()).into(img_acatar);
          avatar = data.getAvatar();
          real_name = data.getUser_name();
          birth = data.getBirth();
          education = data.getEducation();
          city = data.getCity();
          sex = data.getSex();
          age = data.getAge();
          mobile = data.getMobile();
          tv_education.setText(age+"·"+education);
            edt_personal_introduce.setText(data.getPersonal_introduce());
          addResumeList = data.getExperience();
          experience = new GsonBuilder().create().toJson(addResumeList);
          myResumeAdapter = new MyResumeAdapter(mContext,addResumeList);
          mRecyclerView.setAdapter(myResumeAdapter);
          myResumeAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(mContext, AddWorkHistoryActivity.class);
                    intent.putExtra("type","update");
                    intent.putExtra("company_name",addResumeList.get(position).getCompany_name());
                    intent.putExtra("job_content",addResumeList.get(position).getJob_content());
                    intent.putExtra("job_start",addResumeList.get(position).getJob_start());
                    intent.putExtra("job_end",addResumeList.get(position).getJob_end());
                    intent.putExtra("leave_cause",addResumeList.get(position).getLeave_cause());
                    intent.putExtra("position",position+"");
                    startActivityForResult(intent,ADDWORKHISTORY);
                }
            });
            ToastUtils.showToast(mContext,msg);
        }else if (code==20002){
            ToastUtils.showToast(mContext,msg);
        }else if (code ==10001){
            ToastUtils.showToast(mContext,msg);
            startActivity(new Intent(mContext,LoginActivity.class));
            finish();
        }
    }


    public void UpdateUI(int code,String msg){
        ToastUtils.showToast(mContext,msg);
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

package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;
import com.icarexm.zhiquwang.view.activity.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideThreeFragment extends Fragment {

    private SharedPreferences share;
    private CustomProgressDialog progressDialog;
    private Context mContext;

    public static GuideThreeFragment newInstance() {

        return new GuideThreeFragment();
    }


    public GuideThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getContext();
        View inflate = inflater.inflate(R.layout.fragment_guide_three, container, false);
        share = mContext.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        inflate.findViewById(R.id.guide_three_btn_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                SharedPreferences.Editor editor = share.edit();
                editor.putString("bootpage","true");
                editor.commit();
                getActivity().finish();
            }
        });
        return inflate;
    }

    //显示刷新数据
    public void LoadingDialogShow(){
        try {

            if (progressDialog == null) {
                progressDialog = CustomProgressDialog.createDialog(mContext);
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

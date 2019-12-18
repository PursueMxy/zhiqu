package com.icarexm.zhiquwang.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuideTwoFragment extends Fragment {


    private Context mContext;
    private CustomProgressDialog progressDialog;

    public static GuideTwoFragment newInstance() {

        return new GuideTwoFragment();
    }

    public GuideTwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_guide_two, container, false);
        mContext = getContext();
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

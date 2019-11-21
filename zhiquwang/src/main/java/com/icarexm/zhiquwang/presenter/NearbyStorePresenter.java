package com.icarexm.zhiquwang.presenter;

import com.google.gson.GsonBuilder;
import com.icarexm.zhiquwang.bean.NearbyStoreBean;
import com.icarexm.zhiquwang.contract.NearbyStoreContract;
import com.icarexm.zhiquwang.model.NearbyStoreModel;

public class NearbyStorePresenter implements NearbyStoreContract.Presenter {

    public NearbyStoreContract.View mView;
    public NearbyStoreModel nearbyStoreModel;

    public NearbyStorePresenter(NearbyStoreContract.View view){
        this.mView=view;
        this.nearbyStoreModel=new NearbyStoreModel();
    }

    //获取附件门店
    public void GetNearbyStore(String token,String longitude,String latitude,String limit,String page){
     nearbyStoreModel.PostNearbyStore(this,token,longitude,latitude,limit,page);
    }

    //门店数据返回
    public void setNearbyStoreModel(String content){
        NearbyStoreBean nearbyStoreBean = new GsonBuilder().create().fromJson(content, NearbyStoreBean.class);
        mView.UpdateUI(nearbyStoreBean.getCode(),nearbyStoreBean.getMsg(),nearbyStoreBean.getData());
    }
}

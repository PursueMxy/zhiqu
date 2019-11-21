package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.NearbyStoreContract;
import com.icarexm.zhiquwang.presenter.NearbyStorePresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class NearbyStoreModel implements NearbyStoreContract.Model {

    public void PostNearbyStore(NearbyStorePresenter nearbyStorePresenter, String token, String longitude, String latitude, String limit, String page){
        OkGo.<String>post(RequstUrl.URL.NearbyStores)
                .params("token",token)
                .params("longitude",longitude)
                .params("latitude",latitude)
                .params("limit",limit)
                .params("page",page)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                     nearbyStorePresenter.setNearbyStoreModel(response.body());
                    }
                });
    }
}

package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.AddBankCardContract;
import com.icarexm.zhiquwang.presenter.AddBankCardPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class AddBankCardModel implements AddBankCardContract.Model {
    public void PosttAddCard(AddBankCardPresenter addBankCardPresenter,String token, String bank_user, String bank_name, String bank_num){
        OkGo.<String>post(RequstUrl.URL.addBank)
                .params("token",token)
                .params("bank_user",bank_user)
                .params("bank_name",bank_name)
                .params("bank_num",bank_num)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                   addBankCardPresenter.SetAddBank(response.body());
                    }
                });
    }
}

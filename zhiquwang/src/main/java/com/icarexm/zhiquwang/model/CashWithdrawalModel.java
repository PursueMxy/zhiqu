package com.icarexm.zhiquwang.model;

import com.icarexm.zhiquwang.contract.CashWithdrawalContract;
import com.icarexm.zhiquwang.presenter.CashWithdrawalPresenter;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class CashWithdrawalModel implements CashWithdrawalContract.Model {
    //获取银行列表
    public void PostCashWithdrawa(CashWithdrawalPresenter cashWithdrawalPresenter, String token){
        OkGo.<String>post(RequstUrl.URL.myBank)
                .params("token",token)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                    cashWithdrawalPresenter.SetWithdrawal(response.body());
                    }
                });
    }

    /**
     *  提现金额
     */
    public void PostdoWithdrawal(CashWithdrawalPresenter cashWithdrawalPresenter,String token,String price,String bank_id){
           OkGo.<String>post(RequstUrl.URL.doWithdrawal)
                   .params("token",token)
                   .params("price",price)
                   .params("bank_id",bank_id)
                   .execute(new StringCallback() {
                       @Override
                       public void onSuccess(Response<String> response) {
                        cashWithdrawalPresenter.SetdoWithdrawal(response.body());
                       }
                   });
    }

}

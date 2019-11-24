package com.icarexm.zhiquwang.presenter;

import com.icarexm.zhiquwang.contract.MyToJoinContract;
import com.icarexm.zhiquwang.model.MyToJoinModel;

public class MyToJoinPresenter implements MyToJoinContract.Presenter {
    public MyToJoinContract.View mView;
    public MyToJoinModel myToJoinModel;

    public MyToJoinPresenter(MyToJoinContract.View view){
        this.mView=view;
        myToJoinModel=new MyToJoinModel();
    }

     public void GetMyTojoin(String token,String name,String mobile,String area){
      myToJoinModel.PostMyTojoin(this,token,name,mobile,area);
    }

    /**
     * 加入返回
     */
    public void SetMyToJoin(String content){

    }
}

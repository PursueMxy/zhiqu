package com.icarexm.zhiquwang.presenter;

import com.icarexm.zhiquwang.contract.MyTeamContract;
import com.icarexm.zhiquwang.model.MyTeamModel;
import com.icarexm.zhiquwang.model.MyToJoinModel;

public class MyTeamPresenter implements MyTeamContract.Presenter {

    public MyTeamContract.View mView;

    public MyTeamModel myTeamModel;

    public MyTeamPresenter(MyTeamContract.View view){
        this.mView=view;
        myTeamModel=new MyTeamModel();
    }

    public void GetMyTeam(String token,String limit,String page){
      myTeamModel.PostMyTeam(this,token,limit,page);
    }
}

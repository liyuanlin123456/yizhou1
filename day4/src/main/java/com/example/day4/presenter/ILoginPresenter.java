package com.example.day4.presenter;

import com.example.day4.contract.ILoginContract;
import com.example.day4.entity.UserEntity;
import com.example.day4.model.LoginModel;
import com.example.day4.net.RequestCallback;

import java.util.HashMap;

public class ILoginPresenter extends ILoginContract.ILoginPresenter {
    private LoginModel loginModel;
    private ILoginContract.ILoginView iLoginView;

    public ILoginPresenter(ILoginContract.ILoginView iLoginView) {
        this.loginModel=new LoginModel();
        this.iLoginView = iLoginView;
    }

    @Override
    public void login(HashMap<String, String> params) {
        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iLoginView!=null){
                        iLoginView.onFailUre(msg);
                    }
                }

                @Override
                public void onsuccess(UserEntity userEntity) {
                    if (iLoginView!=null){
                        iLoginView.onSuccess(userEntity);
                    }
                }

                @Override
                public void onsuccessMsg(String msg) {
                    if (iLoginView!=null){
                        iLoginView.onSuccessMsg(msg);
                    }
                }
            });
        }
    }
}

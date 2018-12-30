package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.contract.ILoginContract;
import com.example.lenovo.myapplication.contract.IRegisterContract;
import com.example.lenovo.myapplication.entity.UserEntity;
import com.example.lenovo.myapplication.model.LoginModel;
import com.example.lenovo.myapplication.net.RequestCallback;

import java.util.HashMap;

public class LoginPresenter extends ILoginContract.LoginPresenter {
    private LoginModel loginModel;
    private ILoginContract.ILoginView iLoginView;

    public LoginPresenter(ILoginContract.ILoginView iLoginView) {
        this.loginModel=new LoginModel();
        this.iLoginView = iLoginView;
    }
    @Override
    public void login(HashMap<String, String> params) {
        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void failUre(String msg) {
                    if (iLoginView!=null){
                        iLoginView.failUre(msg);
                    }
                }

                @Override
                public void successMsg(String msg) {
                    if (iLoginView!=null){
                        iLoginView.successMsg(msg);
                    }
                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iLoginView!=null){
                        iLoginView.success(userEntity);
                    }
                }
            });
        }
    }
}

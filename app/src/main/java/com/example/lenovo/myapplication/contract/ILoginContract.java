package com.example.lenovo.myapplication.contract;

import com.example.lenovo.myapplication.entity.UserEntity;
import com.example.lenovo.myapplication.net.RequestCallback;

import java.util.HashMap;

public interface ILoginContract {
    public abstract class LoginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface ILoginModel{
        void login(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface ILoginView{
        void mobileError(String error);
        void passwordError(String error);
        void failUre(String msg);
        void successMsg(String result);
        void success(UserEntity userEntity);
    }
}

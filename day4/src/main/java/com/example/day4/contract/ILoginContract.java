package com.example.day4.contract;

import com.example.day4.entity.UserEntity;
import com.example.day4.net.RequestCallback;

import java.util.HashMap;

public interface ILoginContract {
    public abstract class ILoginPresenter{
        public abstract void login(HashMap<String,String> params);
    }
    interface ILoginModel{
        void login(HashMap<String,String> params,RequestCallback requestCallback);
    }
    interface ILoginView{
        void onMobileError(String error);
        void onPasswordError(String error);
        void onFailUre(String msg);
        void onSuccess(UserEntity userEntity);
        void onSuccessMsg(String msg);
    }
}

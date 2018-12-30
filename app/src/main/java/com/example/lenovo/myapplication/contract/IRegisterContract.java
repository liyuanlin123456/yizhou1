package com.example.lenovo.myapplication.contract;

import com.example.lenovo.myapplication.entity.UserEntity;

import java.util.HashMap;

public interface IRegisterContract {
    public abstract class RegisterPresenter{
        public abstract void register(HashMap<String,String> params);
    }
    interface IRegisterModel{
        void register(HashMap<String,String> params);
    }
    interface IRegisterView{
        void mobileError(String error);
        void successMsg(String result);
        void failUre(String msg);
    }
}

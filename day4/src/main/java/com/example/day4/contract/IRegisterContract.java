package com.example.day4.contract;

import java.util.HashMap;

public interface IRegisterContract {
    public abstract class IRegisterPresenter{
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

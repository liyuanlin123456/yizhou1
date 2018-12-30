package com.example.yizhou.contract;

import com.example.yizhou.entity.User;
import com.example.yizhou.net.RequestCallback;

import java.util.HashMap;

public interface IShowContrace {
    public abstract class IShowPresenter{
        public abstract void show(HashMap<String,String> params);
    }
    interface IShowModel{
        void show(HashMap<String,String> params, RequestCallback requestCallback);
    }
    interface iShowView{
        void onKeywordsError(String error);
        void onPageError(String error);
        void onFailUre(String msg);
        void onSuccess(User user);
        void onSuccessMsg(String msg);
    }
}

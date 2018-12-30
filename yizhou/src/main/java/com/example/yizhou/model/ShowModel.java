package com.example.yizhou.model;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.yizhou.api.UserApi;
import com.example.yizhou.contract.IShowContrace;
import com.example.yizhou.entity.User;
import com.example.yizhou.net.OkhttpCallback;
import com.example.yizhou.net.OkhttpUtils;
import com.example.yizhou.net.RequestCallback;
import com.google.gson.Gson;

import java.util.HashMap;

public class ShowModel implements IShowContrace.IShowModel {
    Handler handler=new Handler();
    @Override
    public void show(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkhttpUtils.getmInstance().doPost(UserApi.User_SHOW, params, new OkhttpCallback() {
            @Override
            public void failUre(String msg) {
                if (requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onFailUre("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void success(String result) {
                if (!TextUtils.isEmpty(result)){
                    requestCall(result,requestCallback);
                }
            }
        });
    }

    private void requestCall(String result, final RequestCallback requestCallback) {
        final User user = new Gson().fromJson(result, User.class);
        if (requestCallback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallback.onSuccess(user);
                }
            });
        }
    }
}

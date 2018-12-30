package com.example.day4.model;

import android.os.Handler;

import com.example.day4.api.UserApi;
import com.example.day4.contract.IRegisterContract;
import com.example.day4.net.OkhttpCallback;
import com.example.day4.net.OkhttpUtils;

import java.util.HashMap;

public class RegisterModel implements IRegisterContract.IRegisterModel {
    Handler handler=new Handler();
    private RegisterCallback registerCallback;
    @Override
    public void register(HashMap<String, String> params) {
        OkhttpUtils.getmInstance().doPost(UserApi.USER_REG, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (registerCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerCallback.onFailUre("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void success(final String result) {
                if (registerCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            registerCallback.onResponse(result);
                        }
                    });
                }
            }
        });
    }

    public void setRegisterCallback(RegisterCallback registerCallback) {
        this.registerCallback = registerCallback;
    }

    public interface RegisterCallback{
        void onFailUre(String msg);
        void onResponse(String result);
    }
}

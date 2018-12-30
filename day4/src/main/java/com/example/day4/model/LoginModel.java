package com.example.day4.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.day4.api.UserApi;
import com.example.day4.contract.ILoginContract;
import com.example.day4.entity.UserEntity;
import com.example.day4.net.OkhttpCallback;
import com.example.day4.net.OkhttpUtils;
import com.example.day4.net.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel implements ILoginContract.ILoginModel {
    Handler handler=new Handler();
    @Override
    public void login(HashMap<String, String> params, final RequestCallback requestCallback) {
        OkhttpUtils.getmInstance().doPost(UserApi.USER_LOGIN, params, new OkhttpCallback() {
            @Override
            public void failure(String msg) {
                if (requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onFailUre("网络可能不稳定，请稍后再试");
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
        /*OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> p:params.entrySet()){
            formBody.add(p.getKey(),p.getValue());
        }
        Request request = new Request.Builder().url(UserApi.USER_LOGIN).post(formBody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            requestCallback.onFailUre("网络不稳定，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String request = response.body().string();
                final int code = response.code();
                if (!TextUtils.isEmpty(request)){
                    requestCall(request,requestCallback,code);
                }
            }
        });*/
    }

    private void requestCall(String request, final RequestCallback requestCallback) {
        final UserEntity userEntity = new Gson().fromJson(request, UserEntity.class);
        if (requestCallback!=null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    requestCallback.onsuccess(userEntity);
                }
            });
        }
    }
}

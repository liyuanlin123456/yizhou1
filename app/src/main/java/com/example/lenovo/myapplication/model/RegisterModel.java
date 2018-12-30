package com.example.lenovo.myapplication.model;

import android.os.Handler;

import com.example.lenovo.myapplication.api.UserApi;
import com.example.lenovo.myapplication.contract.IRegisterContract;

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

public class RegisterModel implements IRegisterContract.IRegisterModel {
    Handler handler=new Handler();
    private RegisterCallback registerCallback;
    @Override
    public void register(HashMap<String, String> params) {
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> p:params.entrySet()){
            formBody.add(p.getKey(),p.getValue());
        }
        Request request = new Request.Builder().url(UserApi.USER_REG).post(formBody.build()).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
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
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        registerCallback.onResponse(result);
                    }
                });
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

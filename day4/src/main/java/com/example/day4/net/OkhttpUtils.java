package com.example.day4.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    private Handler handler=new Handler();
    private OkHttpClient okHttpClient;
    private static OkhttpUtils mInstance;

    private OkhttpUtils() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkhttpUtils getmInstance() {
        if (mInstance==null){
            synchronized (OkhttpUtils.class){
                if (mInstance==null){
                    mInstance=new OkhttpUtils();
                }
            }
        }
        return mInstance;
    }
    public void doGet(String url, final OkhttpCallback okhttpCallback){
        Request request = new Request.Builder().url(url).get().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.success(result);
                        }
                    });
                }
            }
        });
    }
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> p:params.entrySet()){
            formBody.add(p.getKey(),p.getValue());
        }
        Request request = new Request.Builder().url(url).post(formBody.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                int code = response.code();
                if (okhttpCallback!=null){
                    if (200==code){
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }
}

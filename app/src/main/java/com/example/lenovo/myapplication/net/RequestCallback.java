package com.example.lenovo.myapplication.net;

import com.example.lenovo.myapplication.entity.UserEntity;

public interface RequestCallback {
    void failUre(String msg);
    void successMsg(String msg);
    void success(UserEntity userEntity);
}

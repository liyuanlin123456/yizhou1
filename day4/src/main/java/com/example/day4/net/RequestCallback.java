package com.example.day4.net;

import com.example.day4.entity.UserEntity;

public interface RequestCallback {
    void onFailUre(String msg);
    void onuccess(UserEntity userEntity);
    void onuccessMsg(String msg);
}

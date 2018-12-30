package com.example.yizhou.net;

import com.example.yizhou.entity.User;

public interface RequestCallback {
    void onFailUre(String msg);
    void onSuccess(User user);
    void onSuccessMsg(String msg);
}

package com.example.yizhou.presenter;

import com.example.yizhou.contract.IShowContrace;
import com.example.yizhou.entity.User;
import com.example.yizhou.model.ShowModel;
import com.example.yizhou.net.RequestCallback;

import java.util.HashMap;

public class ShowPresenter extends IShowContrace.IShowPresenter {
    private ShowModel showModel;
    private IShowContrace.iShowView iShowView;

    public ShowPresenter(IShowContrace.iShowView iShowView) {
        this.showModel=new ShowModel();
        this.iShowView = iShowView;
    }

    @Override
    public void show(HashMap<String, String> params) {
        if (showModel!=null){
            showModel.show(params, new RequestCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iShowView!=null){
                        iShowView.onFailUre(msg);
                    }
                }

                @Override
                public void onSuccess(User user) {
                    if (iShowView!=null){
                        iShowView.onSuccess(user);
                    }
                }

                @Override
                public void onSuccessMsg(String msg) {
                    if (iShowView!=null){
                        iShowView.onSuccessMsg(msg);
                    }
                }
            });
        }
    }
    public void destroy(){

        if (iShowView!=null){
            iShowView = null;
        }

    }
}

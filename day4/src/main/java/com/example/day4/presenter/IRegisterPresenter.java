package com.example.day4.presenter;

import com.example.day4.contract.IRegisterContract;
import com.example.day4.model.RegisterModel;
import com.example.day4.utils.ValidatorUtil;

import java.util.HashMap;

public class IRegisterPresenter extends IRegisterContract.IRegisterPresenter {

    private RegisterModel registerModel;
    private IRegisterContract.IRegisterView iRegisterView;

    public IRegisterPresenter(IRegisterContract.IRegisterView iRegisterView) {
        this.registerModel=new RegisterModel();
        this.iRegisterView = iRegisterView;
    }

    @Override
    public void register(HashMap<String, String> params) {
        String mobile = params.get("mobile");
        if (!ValidatorUtil.isMobile(mobile)){
            if (iRegisterView!=null){
                iRegisterView.mobileError("手机号不可用");
            }
            return;
        }
        if (registerModel!=null){
            registerModel.register(params);
            registerModel.setRegisterCallback(new RegisterModel.RegisterCallback() {
                @Override
                public void onFailUre(String msg) {
                    if (iRegisterView!=null){
                        iRegisterView.failUre(msg);
                    }
                }

                @Override
                public void onResponse(String result) {
                    if (iRegisterView!=null){
                        iRegisterView.successMsg(result);
                    }
                }
            });
        }
    }
    public void dedstory(){
        if (iRegisterView!=null){
            iRegisterView=null;
        }
    }
}

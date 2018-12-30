package com.example.lenovo.myapplication.presenter;

import com.example.lenovo.myapplication.contract.IRegisterContract;
import com.example.lenovo.myapplication.model.RegisterModel;
import com.example.lenovo.myapplication.utils.ValidatorUtil;

import java.util.HashMap;

public class RegisterPresenter extends IRegisterContract.RegisterPresenter {
    private RegisterModel registerModel;
    private IRegisterContract.IRegisterView iRegisterView;

    public RegisterPresenter(IRegisterContract.IRegisterView iRegisterView) {
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
}

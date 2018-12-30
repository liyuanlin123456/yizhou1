package com.example.day4.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day4.R;
import com.example.day4.contract.IRegisterContract;
import com.example.day4.presenter.IRegisterPresenter;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements IRegisterContract.IRegisterView {

    private EditText ed_phone;
    private EditText ed_pwd;
    private Button btn_zc;
    private IRegisterPresenter presenter;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed_phone = findViewById(R.id.ed_phone);
        ed_pwd = findViewById(R.id.ed_pwd);
        btn_zc = findViewById(R.id.btn_zc);
        presenter = new IRegisterPresenter(this);
    }
    public void register(View view){
        mobile = ed_phone.getText().toString();
        password = ed_pwd.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",password);
        presenter.register(params);
    }

    @Override
    public void mobileError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failUre(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

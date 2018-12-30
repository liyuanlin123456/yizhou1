package com.example.day4.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.day4.R;
import com.example.day4.contract.ILoginContract;
import com.example.day4.entity.UserEntity;
import com.example.day4.presenter.ILoginPresenter;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements ILoginContract.ILoginView {

    private EditText ed_phone;
    private EditText ed_pwd;
    private Button btn_login;
    private Button btn_zc;
    private String mobile;
    private String password;
    private ILoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed_phone = findViewById(R.id.ed_phone);
        ed_pwd = findViewById(R.id.ed_pwd);
        btn_login = findViewById(R.id.btn_login);
        btn_zc = findViewById(R.id.btn_zc);
        btn_zc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        presenter = new ILoginPresenter(this);
    }
    public void login(View view){
        mobile = ed_phone.getText().toString();
        password = ed_pwd.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("mobile",mobile);
        params.put("password",password);
        if (presenter!=null){
            presenter.login(params);
        }
    }

    @Override
    public void onMobileError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailUre(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(UserEntity userEntity) {
        Toast.makeText(this,userEntity.getMsg(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}

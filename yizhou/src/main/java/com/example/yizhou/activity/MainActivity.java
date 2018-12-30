package com.example.yizhou.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.yizhou.R;
import com.example.yizhou.adapter.Adapter;
import com.example.yizhou.adapter.MyAdapter;
import com.example.yizhou.api.UserApi;
import com.example.yizhou.contract.IShowContrace;
import com.example.yizhou.entity.User;
import com.example.yizhou.net.OkhttpCallback;
import com.example.yizhou.net.OkhttpUtils;
import com.example.yizhou.presenter.ShowPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IShowContrace.iShowView {


    private Button btn_search;
    private String keywords;
    private String page="1";
    private MyAdapter myAdapter;
    private RecyclerView gv;
    private ShowPresenter presenter;
    private Adapter adapter;
    @BindView(R.id.ed_title)
    EditText ed_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定当前实例
        //ed_title = findViewById(R.id.ed_title);
        btn_search = findViewById(R.id.btn_search);
        gv = findViewById(R.id.gv);
        gv.setLayoutManager(new LinearLayoutManager(this));
        //myAdapter = new MyAdapter(this);
        adapter = new Adapter(this);
        presenter = new ShowPresenter(this);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keywords = ed_title.getText().toString();
                HashMap<String,String> params=new HashMap<>();
                params.put("keywords",keywords);
                params.put("page",page);
                presenter.show(params);
            }
        });
    }

    private void parseResult(String result) {
        User user = new Gson().fromJson(result, User.class);
        if (user!=null){
            /*myAdapter.setList(user.getData());
            gv.setAdapter(myAdapter);*/
            adapter.setList(user.getData());
            gv.setAdapter(adapter);
        }
    }

    @Override
    public void onKeywordsError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageError(String error) {
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailUre(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(User user) {
        List<User.DataBean> data = user.getData();
        adapter.setList(data);
        gv.setAdapter(adapter);
    }

    @Override
    public void onSuccessMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
        OkhttpUtils.getmInstance().cancelAllTask();
        //dsaudhuisayusy
    }
}

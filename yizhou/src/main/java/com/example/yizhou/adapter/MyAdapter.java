package com.example.yizhou.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yizhou.R;
import com.example.yizhou.entity.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<User.DataBean> list;

    public MyAdapter(Context context) {
        this.context = context;
        this.list=new ArrayList<>();
    }

    public void setList(List<User.DataBean> list) {
        if (list!=null) {
            this.list = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public User.DataBean getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.linear,parent,false);
            viewHolder=new ViewHolder(convertView);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.bindView(getItem(position));
        return convertView;
    }
    class ViewHolder{
        ImageView image;
        TextView title,price;

        public ViewHolder(View itemView) {
            this.image = itemView.findViewById(R.id.image);
            this.title = itemView.findViewById(R.id.title);
            this.price = itemView.findViewById(R.id.price);
            itemView.setTag(this);
        }

        public void bindView(User.DataBean dataBean){
            String images = dataBean.getImages();
            String[] split = images.split("!");
            Glide.with(context).load(split[0]).into(image);
            this.title.setText(dataBean.getTitle());
            this.price.setText(dataBean.getPrice()+"");
        }
    }
}

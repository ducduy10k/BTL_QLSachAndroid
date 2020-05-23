package com.example.btladroidquanlysach;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class UserCustomAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<UserClass> userClasses;

    public UserCustomAdapter(Context context, int layout, List<UserClass> userClasses) {
        this.context = context;
        this.layout = layout;
        this.userClasses = userClasses;
    }

    @Override
    public int getCount() {
        return userClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private  class  ViewHolder{
        TextView txtUsername,txtPass;
        ImageView imgUser;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.txtUsername =convertView.findViewById(R.id.txtusername);
            holder.txtPass =convertView.findViewById(R.id.txtpassword);
            holder.imgUser =convertView.findViewById(R.id.imguser);
            convertView.setTag(holder);

        }
        else {
           holder= (ViewHolder) convertView.getTag();
        }
        UserClass aClass = userClasses.get(position);
        holder.txtUsername.setText(aClass.getUsername());
        holder.txtPass.setText(aClass.getPassword());
        // chuyển byte[]- bitmap
        byte[] HinhAnh =  aClass.getImguser();
        Bitmap bitmap  = BitmapFactory.decodeByteArray(HinhAnh,0,HinhAnh.length);
        holder.imgUser.setImageBitmap(bitmap);

        holder.txtUsername.setText(aClass.getUsername());


        return convertView;
    }
}

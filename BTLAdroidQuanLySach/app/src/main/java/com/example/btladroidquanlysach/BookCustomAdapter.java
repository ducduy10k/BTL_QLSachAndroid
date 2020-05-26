package com.example.btladroidquanlysach;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookCustomAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<BookClass> bookClasses;

    public BookCustomAdapter(Context context, int layout, List<BookClass> bookClasses) {
        this.context = context;
        this.layout = layout;
        this.bookClasses = bookClasses;
    }

    @Override
    public int getCount() {
        return bookClasses.size();
    }

    @Override
    public Object getItem(int position) {
        return bookClasses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView idbook,txttacgia;
        ImageView imgbook;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder.idbook =convertView.findViewById(R.id.txtbookname);
            holder.txttacgia =convertView.findViewById(R.id.txtNhaSB);
            holder.imgbook =convertView.findViewById(R.id.imgbook);
            convertView.setTag(holder);

        }
        else {
            holder= (ViewHolder) convertView.getTag();
        }
        BookClass aClass = bookClasses.get(position);
        holder.idbook.setText(aClass.getIdbook());
        holder.txttacgia.setText(aClass.getAuthor());
        // chuyển byte[]- bitmap
        byte[] HinhAnh =  aClass.getAnh();
        Bitmap bitmap  = BitmapFactory.decodeByteArray(HinhAnh,0,HinhAnh.length);
        holder.imgbook.setImageBitmap(bitmap);

        holder.idbook.setText(aClass.getIdbook());


        return convertView;
    }
}

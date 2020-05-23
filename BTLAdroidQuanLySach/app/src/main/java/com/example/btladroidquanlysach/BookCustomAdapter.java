package com.example.btladroidquanlysach;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookCustomAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<BookClass> bookClasses;
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
        TextView txtTenSach;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return null;
    }
}

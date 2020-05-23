package com.example.btladroidquanlysach;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class SettingAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<SettingItem> settingItems;

    public SettingAdapter(Context context, int layout, List<SettingItem> settingItems) {
        this.context = context;
        this.layout = layout;
        this.settingItems = settingItems;
    }

    @Override
    public int getCount() {
        return settingItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(layout,null);
        //anh xa
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iconsetting);
        TextView textView = convertView.findViewById(R.id.namesetting);
        // gan gia tri
        SettingItem settingItem = settingItems.get(position);
        imageView.setImageResource(settingItem.getAnh());
        textView.setText(settingItem.getName());

        return convertView;
    }
}

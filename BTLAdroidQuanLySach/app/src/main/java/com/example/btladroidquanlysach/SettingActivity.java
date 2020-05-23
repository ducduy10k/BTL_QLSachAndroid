package com.example.btladroidquanlysach;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SettingActivity extends AppCompatActivity {

    ListView lstSetting;
    ArrayList<SettingItem> settingItems;
    SettingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTitle("Setting");
        lstSetting=(ListView) findViewById(R.id.lstsetting);
        settingItems = new ArrayList<>();
        settingItems.add(new SettingItem(R.drawable.language100,"Language"));
        settingItems.add(new SettingItem(R.drawable.sun96,"Brightness"));
        Collections.sort(settingItems, new Comparator<SettingItem>() {
            @Override
            public int compare(SettingItem o1, SettingItem o2) {
                if(o1.getName().indexOf(0)>o2.getName().indexOf(0))
                {
                    return 0;

                }
                else {
                    return  -1;
                }

            }
        });
        adapter = new SettingAdapter(SettingActivity.this,R.layout.dong_setting,settingItems);
        lstSetting.setAdapter(adapter);
        lstSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (settingItems.get(position).getName()=="Language")
                {
                    Intent intent = new Intent(SettingActivity.this,LanguageActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

}

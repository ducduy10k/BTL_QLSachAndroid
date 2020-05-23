package com.example.btladroidquanlysach;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class LanguageActivity extends AppCompatActivity {
    ArrayList<String > languages;
    ListView lstLanguage;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        setTitle("Language");
        lstLanguage = (ListView) findViewById(R.id.listlanguage);
        languages = new ArrayList<>();
        languages.add("Tiếng Việt");
        languages.add("English");
        languages.add("US");
        languages.add("UK");

        Collections.sort(languages);// sắp xếp theo thứ tự a-z

        adapter = new ArrayAdapter(LanguageActivity.this,android.R.layout.simple_list_item_1,languages);
        lstLanguage.setAdapter(adapter);
        lstLanguage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(LanguageActivity.this,languages.get(position).toString()+" đã thiết lập , khởi động lại máy để hoàn tất",Toast.LENGTH_LONG).show();
            }
        });



    }
}

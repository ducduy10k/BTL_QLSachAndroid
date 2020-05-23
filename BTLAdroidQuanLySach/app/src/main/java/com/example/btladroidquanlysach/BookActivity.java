package com.example.btladroidquanlysach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    ListView lstBooks;


    ArrayList<UserClass> userClasses;
    //UserAdapter userAdapter;
   // UserCustomAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        setContentView(R.layout.activity_user);
        setTitle("User Lists");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00cfaa")));

        AnhXa();

        UserActivity.db= new DBHelper(this);
        UserActivity.db.queryData("CREATE TABLE IF NOT EXISTS tblBook (ID NVARCHAR(20) PRIMARY KEY,TenSach NVARCHAR(20),TenTacGia NVARCHAR(50),NgaySB DATE,MaNSB NVARCHAR(20),MaTL NVARCHAR(20),Anh BOLB)");


    }
    public void AnhXa(){
        lstBooks = findViewById(R.id.listBooks);

    }

}

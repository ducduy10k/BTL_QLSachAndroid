package com.example.btladroidquanlysach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton btnsetting , btnBook,btnUser,btnbill,btnstatistical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // show logo
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý sách");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setLogo(R.drawable.book100);    //Icon muốn hiện thị
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00cfaa")));


        //ánh xạ
        btnsetting= findViewById(R.id.btnsettings);
        btnUser=findViewById(R.id.btnusermanage);
        btnBook=findViewById(R.id.btnsachmanage);
        btnbill=findViewById(R.id.btnbillmanage);
        btnstatistical=findViewById(R.id.btnstatistical);


        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent);
            }
        });
    }
    public  static AlertDialog.Builder XacNhanXoa(Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Thông báo");
        alertDialog.setIcon(R.mipmap.ic_launcher);
        alertDialog.setMessage("Bạn có muốn xóa không");
        return  alertDialog;
    }
    public void ActionExit(){
        finish();
    }
}

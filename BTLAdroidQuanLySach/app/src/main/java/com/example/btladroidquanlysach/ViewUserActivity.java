package com.example.btladroidquanlysach;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewUserActivity extends AppCompatActivity {

    TextView userN,pass,email,phone,fullname;
    ImageView imageView;
    Button btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Anhxa();
        Intent intent = getIntent();
        String x=intent.getStringExtra("userName");
        try {

            String sql="SELECT * FROM tblUser WHERE UserName =  '" + x + "'";

            Cursor cursor = UserActivity.db.getData(sql);
            cursor.moveToFirst();
            userN.setText(cursor.getString(0));
            pass.setText(cursor.getString(1));
            fullname.setText(cursor.getString(2));
            email.setText(cursor.getString(3));
            phone.setText(cursor.getString(4));
            byte[] HinhAnh = cursor.getBlob(5);
            //chuyển byte sang bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(HinhAnh, 0, HinhAnh.length);
            imageView.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(ViewUserActivity.this,"Lỗi !!!",Toast.LENGTH_LONG).show();
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewUserActivity.this,UserActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void Anhxa(){
        userN = findViewById(R.id.editUsername1);
        pass = findViewById(R.id.txtPassword);
        fullname = findViewById(R.id.txtFullName);
        phone = findViewById(R.id.txtPhone);
        email = findViewById(R.id.txtEmail);
        imageView = findViewById(R.id.imgViewAcc1);
        btnback = findViewById(R.id.btnupdate);


    }
}

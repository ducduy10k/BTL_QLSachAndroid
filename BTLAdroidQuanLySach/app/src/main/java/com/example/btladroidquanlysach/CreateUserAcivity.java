package com.example.btladroidquanlysach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CreateUserAcivity extends AppCompatActivity {

    EditText user,pass,fullname,email,phone;
    Button btncreate;
    ImageButton ibtnCamera,ibtnFolder;
    ImageView imageView;
    static int REQUEST_CODE_CAMERA=1011;
    static int REQUEST_CODE_FOLDER=1999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_acivity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();// ẩn title

        AnhXa();
        btncreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try {
                   // chuyển imgView sang byte[]

                   BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                   Bitmap bitmap = bitmapDrawable.getBitmap();
                   ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                   bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                   byte[] HinhAnh = byteArrayOutputStream.toByteArray();
                   //
                   Toast.makeText(CreateUserAcivity.this, "Đã chuyển thành công", Toast.LENGTH_LONG).show();
                   UserActivity.db.Insert_User(
                           user.getText().toString().trim(),
                           pass.getText().toString().trim(),
                           fullname.getText().toString().trim(),
                           email.getText().toString().trim(),
                           phone.getText().toString().trim(),
                           HinhAnh
                   );
                   Toast.makeText(CreateUserAcivity.this,"Đã thêm",Toast.LENGTH_LONG).show();
                   startActivity(new Intent(CreateUserAcivity.this,UserActivity.class));

               }
               catch (Exception e){
                   Toast.makeText(CreateUserAcivity.this,"Lỗi",Toast.LENGTH_LONG).show();

               }


            }
        });
        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
            }

        });
        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_CAMERA && resultCode ==RESULT_OK &&data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
        if (requestCode==REQUEST_CODE_FOLDER && resultCode ==RESULT_OK &&data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

    public  void AnhXa(){
        user=findViewById(R.id.editUsername);
        pass = findViewById(R.id.editPassword);
        fullname = findViewById(R.id.editFullName);
        email = findViewById(R.id.editEmail);
        phone = findViewById(R.id.editPhone);
        btncreate=findViewById(R.id.btnupCreate);
        imageView = findViewById(R.id.imgViewBook);
        ibtnCamera=findViewById(R.id.ibtnCamera);
        ibtnFolder=findViewById(R.id.ibtnForder);



    }

}

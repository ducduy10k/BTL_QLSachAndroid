package com.example.btladroidquanlysach;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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

public class EditUserActivity extends AppCompatActivity {

    EditText un,pw,fn,ea,pn;
    ImageView imgA;
    ImageButton ibtnCamera, ibtnFolder;
    Button update;
     int REQUEST_CODE_CAMERA=1011;
    int REQUEST_CODE_FOLDER=1999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Anhxa();
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

        Intent intent = getIntent();
        String x=intent.getStringExtra("userName");

        try {

            String sql="SELECT * FROM tblUser WHERE UserName =  '" + x + "'";

            Cursor cursor = UserActivity.db.getData(sql);
            cursor.moveToFirst();
            un.setText(cursor.getString(0));
            pw.setText(cursor.getString(1));
            fn.setText(cursor.getString(2));
            ea.setText(cursor.getString(3));
            pn.setText(cursor.getString(4));
            final byte[]  HinhAnh = cursor.getBlob(5);
            //chuyển byte sang bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(HinhAnh, 0, HinhAnh.length);
            imgA.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(EditUserActivity.this,"Lỗi !!!",Toast.LENGTH_LONG).show();
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              try {

                  // chuyển imgView sang byte[]

                  BitmapDrawable bitmapDrawable = (BitmapDrawable) imgA.getDrawable();
                  Bitmap bitmap = bitmapDrawable.getBitmap();
                  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                  bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                  byte[] HinhAnh = byteArrayOutputStream.toByteArray();
                  UserActivity.db.Update_User(
                          un.getText().toString().trim(),
                          pw.getText().toString().trim(),
                          fn.getText().toString().trim(),
                          ea.getText().toString().trim(),
                          pn.getText().toString().trim(),
                          HinhAnh

                  );
                  startActivity(new Intent(EditUserActivity.this, UserActivity.class));
              }
              catch (Exception e){
                  Toast.makeText(EditUserActivity.this,"Lỗi !!!",Toast.LENGTH_LONG).show();
              }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE_CAMERA && resultCode ==RESULT_OK &&data!=null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgA.setImageBitmap(bitmap);
        }
        if (requestCode==REQUEST_CODE_FOLDER && resultCode ==RESULT_OK &&data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgA.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public void Anhxa(){
        un=findViewById(R.id.editUsername1);
        pw=findViewById(R.id.editPassword1);
        fn=findViewById(R.id.editFullName1);
        ea=findViewById(R.id.editEmail1);
        pn=findViewById(R.id.editPhone1);
        imgA= findViewById(R.id.imgViewAcc1);
        ibtnCamera =findViewById(R.id.ibtnCamera1);
        ibtnFolder = findViewById(R.id.ibtnForder1);
        update=findViewById(R.id.btnupCreate);
    }

}

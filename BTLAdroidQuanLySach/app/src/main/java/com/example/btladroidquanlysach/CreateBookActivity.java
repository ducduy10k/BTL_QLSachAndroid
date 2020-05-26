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

public class CreateBookActivity extends AppCompatActivity {

    EditText idbook,title,author,publishing,price;
    Button btnCreate;
    ImageButton ibtnCamera,ibtnFolder;
    ImageView imageView;
    static int REQUEST_CODE_CAMERA=1011;
    static int REQUEST_CODE_FOLDER=1999;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_book);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    AnhXa();

        btnCreate.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(CreateBookActivity.this, "Đã chuyển thành công", Toast.LENGTH_LONG).show();
                    BookActivity.db =new DBHelper(CreateBookActivity.this);
                    BookActivity.db.Insert_Book(
                            idbook.getText().toString().trim(),
                            title.getText().toString().trim(),
                            author.getText().toString().trim(),
                            publishing.getText().toString().trim(),
                            Integer.parseInt(price.getText().toString().trim()),
                            HinhAnh
                    );
                    Toast.makeText(CreateBookActivity.this,"Đã thêm",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateBookActivity.this,BookActivity.class));
                }
                catch (Exception e){
                    Toast.makeText(CreateBookActivity.this,"Lỗi",Toast.LENGTH_LONG).show();
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

    public void AnhXa(){
        idbook=findViewById(R.id.editIdBook);
        title=findViewById(R.id.editBookTitile);
        author=findViewById(R.id.editIdBook);
        publishing=findViewById(R.id.editPublingshing);
        price=findViewById(R.id.editprice);
        btnCreate =findViewById(R.id.btnCreateBook);
        imageView=findViewById(R.id.imgViewBook);
        ibtnCamera= findViewById(R.id.ibtnCamera);
        ibtnFolder= findViewById(R.id.ibtnForder);
    }


}

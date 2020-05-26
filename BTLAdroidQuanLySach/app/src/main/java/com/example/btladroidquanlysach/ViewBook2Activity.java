package com.example.btladroidquanlysach;

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

public class ViewBook2Activity extends AppCompatActivity {
    TextView txtIDbook,txtTitleBook,txtAuthor,txtPublishing,txtPrice;
    Button btnBack;
    ImageView imgbooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_book2);
        Anhxa();
        Intent intent= new Intent();
        String x=intent.getStringExtra("txtIDbook");
        try {

            String sql="SELECT * FROM tblUser WHERE UserName =  '" + x + "'";

            Cursor cursor = UserActivity.db.getData(sql);
            cursor.moveToFirst();
            txtIDbook.setText(cursor.getString(0));
            txtTitleBook.setText(cursor.getString(1));
            txtAuthor.setText(cursor.getString(2));
            txtPublishing.setText(cursor.getString(3));
            txtPrice.setText(cursor.getString(4));
            byte[] HinhAnh = cursor.getBlob(5);
            //chuyển byte sang bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(HinhAnh, 0, HinhAnh.length);
            imgbooks.setImageBitmap(bitmap);
        }
        catch (Exception e)
        {
            Toast.makeText(ViewBook2Activity.this,"Lỗi !!!",Toast.LENGTH_LONG).show();
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewBook2Activity.this,BookActivity.class);
                startActivity(intent1);
            }
        });
    }
    public void Anhxa(){
        txtIDbook = findViewById(R.id.txtIDBook);
        txtTitleBook = findViewById(R.id.txtTitleBook);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtPublishing = findViewById(R.id.txtPublishing);
        txtPrice = findViewById(R.id.txtPrice);
        imgbooks = findViewById(R.id.imgbooks);
        btnBack = findViewById(R.id.btnBack);


    }
}

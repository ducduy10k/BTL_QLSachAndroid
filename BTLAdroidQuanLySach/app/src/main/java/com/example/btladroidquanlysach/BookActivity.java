package com.example.btladroidquanlysach;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    ListView lstBooks;
    ImageButton btnadd;

    ArrayList<BookClass> bookClasses;
    BookCustomAdapter bookAdapter;
     public static DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        AnhXa();


        db = new DBHelper(this);

        db.queryData("CREATE TABLE IF NOT EXISTS tblBook1 (idbook NVARCHAR(20) PRIMARY KEY,title NVARCHAR(20),publishing NVARCHAR(50),author NVARCHAR(50),price INT,Anh BOLB)");

       // do du lieu vao listViewCursorAdapter
       bookClasses = new ArrayList<>();

        bookAdapter = new BookCustomAdapter(BookActivity.this,R.layout.dong_book,bookClasses);

        lstBooks.setAdapter(bookAdapter);
        // get data
        try {


            Cursor cursor = db.getAllRecond("tblBook1");
            while (cursor.moveToNext()) {
                bookClasses.add(new BookClass(cursor.getString(0), cursor.getString(1), cursor.getBlob(5)));
            }
            bookAdapter.notifyDataSetChanged();
        }
        catch (Exception e)
        {

        }
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookActivity.this,CreateBookActivity.class);
                startActivity(intent);
            }
        });
        lstBooks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final String idbook = bookClasses.get(position).getIdbook().toString().trim();
                PopupMenu popupMenu = new PopupMenu(BookActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menupopEdit:{
                                String idbook = bookClasses.get(position).getIdbook().toString().trim();
                                Intent intent = new Intent(BookActivity.this,ViewBook2Activity.class);
                                intent.putExtra("idbook",idbook);
                                startActivity(intent);
                            }
                            break;
                            case R.id.menupopDelete:{
                                String s =idbook;
                                AlertDialog.Builder alert =MainActivity.XacNhanXoa(BookActivity.this);
                                alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        db.queryData("DELETE FROM tblBook1 WHERE idbook = '"+ idbook+"'");
                                        Toast.makeText(BookActivity.this,"Xóa thành công .",Toast.LENGTH_LONG).show();

                                        bookAdapter = new BookCustomAdapter(BookActivity.this,R.layout.dong_book,bookClasses);

                                        lstBooks.setAdapter(bookAdapter);
                                        // get data

                                                    bookClasses.clear();

                                            Cursor cursor = db.getAllRecond("tblBook1");
                                            while (cursor.moveToNext()) {
                                                bookClasses.add(new BookClass(cursor.getString(0), cursor.getString(1), cursor.getBlob(5)));
                                            }
                                            bookAdapter.notifyDataSetChanged();


                                    }
                                });
                                alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                alert.show();
                            }
                             break;

                            case  R.id.menupopupView:{
                                String idbook = bookClasses.get(position).getIdbook().toString().trim();
                                Intent intent = new Intent(BookActivity.this,ViewBook2Activity.class);
                                intent.putExtra("Idbook",idbook);
                                startActivity(intent);
                            }
                        }
                        return  false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sua_xoa_record,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case  R.id.menuexit:{
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            };
            break;
            case R.id.backMain:{
                //   ActionExit();
                Intent intent = new Intent(BookActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }



        return super.onOptionsItemSelected(item);
    }

    public  void AnhXa(){
        //ánh xạ
        lstBooks=findViewById(R.id.lstBook);
        btnadd = findViewById(R.id.btnaddbook);
    }
}

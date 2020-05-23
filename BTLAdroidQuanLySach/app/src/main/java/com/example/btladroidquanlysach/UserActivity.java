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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
ImageButton btnadd;
ListView lstUser;


ArrayList<UserClass> userClasses;
//UserAdapter userAdapter;
    UserCustomAdapter userAdapter;
public  static  DBHelper db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("User Lists");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00cfaa")));

        AnhXa();

        db= new DBHelper(this);

        db.queryData("CREATE TABLE IF NOT EXISTS tblUser (UserName NVARCHAR(20) PRIMARY KEY,Password NVARCHAR(20),FullName NVARCHAR(20),EmailAddress NVARCHAR(20),Phone NVARCHAR(20),Anh BOLB)");


      //  DoDuLieuVaoListViewCursorAdapter();
        userClasses = new ArrayList<>();
        userClasses.clear();
        userAdapter = new UserCustomAdapter(UserActivity.this,R.layout.dong_user,userClasses);
        lstUser.setAdapter(userAdapter);

        //get data
        Cursor cursor = db.getAllRecond("tblUser");
        while ( cursor.moveToNext()){
            userClasses.add(new UserClass(cursor.getString(0),cursor.getString(1),cursor.getBlob(5)));
        }
        userAdapter.notifyDataSetChanged();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // Toast.makeText(UserActivity.this,"Ánh xa xong",Toast.LENGTH_LONG).show();
                Intent  intent= new Intent(UserActivity.this,CreateUserAcivity.class);
                startActivity(intent);

            }
        });
        //
//        lstUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String Idname = userClasses.get(position).getUsername().toString().trim();
//
//          //      Toast.makeText(UserActivity.this,"Loading ...",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(UserActivity.this,ViewUserActivity.class);
//                intent.putExtra("userName",Idname);
//                startActivity(intent);
//
//            }
//        });
        lstUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,final int position, long id) {

                final String Idname = userClasses.get(position).getUsername().toString().trim();
                PopupMenu popupMenu = new PopupMenu(UserActivity.this,view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menupopEdit:{

                                String Idname = userClasses.get(position).getUsername().toString().trim();

                                //      Toast.makeText(UserActivity.this,"Loading ...",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UserActivity.this,EditUserActivity.class);
                                intent.putExtra("userName",Idname);
                                startActivity(intent);


                            }
                            break;
                            case  R.id.menupopDelete:{
                                String s= Idname;
                                      AlertDialog.Builder alert= MainActivity.XacNhanXoa(UserActivity.this);
                                        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                db.queryData("DELETE FROM tblUser WHERE UserName = '"+ Idname+"'");
                                                Toast.makeText(UserActivity.this,"Xóa thành công .",Toast.LENGTH_LONG).show();
                                                userAdapter.notifyDataSetChanged();
                                                userClasses.clear();
                                                userAdapter = new UserCustomAdapter(UserActivity.this,R.layout.dong_user,userClasses);
                                                lstUser.setAdapter(userAdapter);

                                                //get data
                                                Cursor cursor = db.getAllRecond("tblUser");
                                                while ( cursor.moveToNext()){
                                                    userClasses.add(new UserClass(cursor.getString(0),cursor.getString(1),cursor.getBlob(5)));
                                                }
                                                userAdapter.notifyDataSetChanged();


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
                            case R.id.menupopupView:{
                                String Idname = userClasses.get(position).getUsername().toString().trim();

                                //      Toast.makeText(UserActivity.this,"Loading ...",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(UserActivity.this,ViewUserActivity.class);
                                intent.putExtra("userName",Idname);
                                startActivity(intent);
                            }

                        }
                        return false;

                    }
                });
                popupMenu.show();
                return false;
            }
        });


        //




    }
    // tạo menu

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
                Intent intent = new Intent(UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }



        return super.onOptionsItemSelected(item);
    }

    public  void AnhXa(){
        //ánh xạ
        lstUser=findViewById(R.id.lstUser);
        btnadd = findViewById(R.id.btnadduser);
    }

    public void ActionExit(){
        finish();
    }


//    public void DoDuLieuVaoListViewCursorAdapter(){
//        Cursor cursor = db.getAllRecond("tblUser");
//
//        userAdapter = new UserAdapter(UserActivity.this,R.layout.dong_user,cursor,0);
//
//       lstUser.setAdapter(userAdapter);
//
//    }

}

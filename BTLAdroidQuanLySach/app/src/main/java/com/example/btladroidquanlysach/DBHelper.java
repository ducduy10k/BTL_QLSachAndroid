package com.example.btladroidquanlysach;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DBName="QuanLySach.db";
    private static  final int VERSION=1;

    private SQLiteDatabase myDB;

    public DBHelper(Context context){
        super(context,DBName,null,VERSION);
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String sql = "CREATE TABLE IF NOT EXISTS NhaXB (MaNSB nvarchar(20) PRIMARY KEY, TenNSB nvarchar(50)   ) ";
            queryData(sql);
            sql = "INSERT INTO NhaXB VALUES('KD01','Kim đồng')" +
                    "INSERT INTO NhaXB VALUES('TT01','Tuổi trẻ')";
            queryData(sql);
            // tao bang TheLoai

            sql = "CREATE TABLE IF NOT EXISTS TheLoai (MaTL nvarchar(20) PRIMARY KEY, TenTL nvarchar(50)   ) ";
            queryData(sql);
            sql = "INSERT INTO NhaXB VALUES('TT01','Truyện tranh')" +
                    "INSERT INTO NhaXB VALUES('VH01','Văn học')";
            queryData(sql);

        }
        catch (Exception e)
        {

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    // try vấn không trả kết quả  : create , insert , update , delete
    public  void queryData(String sql){
        myDB=getWritableDatabase();
        myDB.execSQL(sql);
    }
    // try vấn trả kết quả : select
    public  Cursor getData(String sql){
        myDB= getReadableDatabase();
       return myDB.rawQuery(sql,null);
    }


    public void openDB(){
        myDB=getWritableDatabase();
    }
    public void closeDB(){
        if (myDB!=null&&myDB.isOpen()){
            myDB.close();
        }
    }


    public Cursor getAllRecond(String tableName){
        String query="SELECT * FROM " + tableName;
        return myDB.rawQuery(query,null);
    }
    // sử lý trên tblUser
    public  void Insert_User(String UserName,String Password,String Fullname,String EmailAddress,String Phone,byte[] Hinh){
        myDB = getWritableDatabase();
        String sql = "INSERT INTO  tblUser VALUES (?,?,?,?,?,?)";
        SQLiteStatement statement = myDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,UserName);
        statement.bindString(2,Password);
        statement.bindString(3,Fullname);
        statement.bindString(4,EmailAddress);
        statement.bindString(5,Phone);
        statement.bindBlob(6,Hinh);
        statement.executeInsert();
    }
    public void Update_User(String UserName,String Password,String Fullname,String EmailAddress,String Phone,byte[] Hinh){
        myDB = getWritableDatabase();
        String sql = "UPDATE tblUser  SET Password = '"+Password+"',FullName = '"+Fullname+"', EmailAddress = '"+EmailAddress+"',Phone = '"+Phone+"',Anh=?  WHERE UserName =  '" + UserName  + "'" ;
        SQLiteStatement statement= myDB.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,Hinh);
        statement.executeUpdateDelete();
    }


}

package com.example.btladroidquanlysach;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

// Cursor Adapter
public class UserAdapter extends ResourceCursorAdapter {


    public UserAdapter(Context context, int layout, Cursor c, int flags) {
        super(context, layout, c, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView txtID=(TextView) view.findViewById(R.id.txtusername);
        txtID.setText(cursor.getString(cursor.getColumnIndex("UserName")));

        TextView txtHoTen=(TextView) view.findViewById(R.id.txtpassword);
        txtHoTen.setText(cursor.getString(cursor.getColumnIndex("Password")));

        ImageView imgAnh=(ImageView) view.findViewById(R.id.imguser);
        // chuyển byte[]- bitmap
        byte[] HinhAnh =  cursor.getBlob(cursor.getColumnIndex("Anh"));
        Bitmap bitmap  = BitmapFactory.decodeByteArray(HinhAnh,0,HinhAnh.length);
        imgAnh.setImageBitmap(bitmap);
    }
}

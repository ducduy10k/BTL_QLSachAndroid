package com.example.btladroidquanlysach;

import androidx.appcompat.app.AppCompatActivity;

public class SettingItem{
    private int anh ;
    private String Name;

    public SettingItem(int anh, String name) {
        this.anh = anh;
        Name = name;
    }

    public int getAnh() {
        return anh;
    }

    public void setAnh(int anh) {
        this.anh = anh;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}


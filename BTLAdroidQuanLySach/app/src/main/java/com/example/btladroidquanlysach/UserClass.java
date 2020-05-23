package com.example.btladroidquanlysach;

public class UserClass {
    private String Username;
    private  String Password;
    private  String Name;
    private String Emailaddress;
    private String Phone;
    private byte[] Imguser;
    private boolean status;

    public byte[] getImguser() {
        return Imguser;
    }

    public void setImguser(byte[] imguser) {
        Imguser = imguser;
    }

    public UserClass(String username, String password, byte[] imguser) {
        Username = username;
        Password = password;
        Imguser = imguser;
    }

    public UserClass(String username,String password, String name,  String emailaddress, String phone, byte[] imguser, boolean status) {
        Name = name;
        Password = password;
        Username = username;
        Emailaddress = emailaddress;
        Phone = phone;
        this.status = status;
        this.Imguser=imguser;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmailaddress() {
        return Emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        Emailaddress = emailaddress;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    //502
}

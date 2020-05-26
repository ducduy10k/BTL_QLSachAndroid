package com.example.btladroidquanlysach;

public class BookClass  {
    private String idbook;
    private String title;
    private String publishing;
    private  String author;
    private  String price;
    private byte[] Anh;

    public BookClass(String idbook, String title, String publishing, String author, String price, byte[] anh) {
        this.idbook = idbook;
        this.title = title;
        this.publishing = publishing;
        this.author = author;
        this.price = price;
        Anh = anh;
    }

    public String getIdbook() {
        return idbook;
    }

    public void setIdbook(String idbook) {
        this.idbook = idbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public byte[] getAnh() {
        return Anh;
    }

    public void setAnh(byte[] anh) {
        Anh = anh;
    }

    public BookClass(String idbook, String author, byte[] anh) {
        this.idbook = idbook;
        this.author = author;
        Anh = anh;
    }
}

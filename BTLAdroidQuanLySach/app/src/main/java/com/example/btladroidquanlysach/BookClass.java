package com.example.btladroidquanlysach;

public class BookClass  {
    private String ID;
    private String TenSach;
    private String NgaySB;
    private  String MaNSB;
    private  String MaTL;
    private byte[] Anh;

    public BookClass(String ID, String tenSach, String ngaySB, String maNSB, String maTL, byte[] anh) {
        this.ID = ID;
        TenSach = tenSach;
        NgaySB = ngaySB;
        MaNSB = maNSB;
        MaTL = maTL;
        Anh = anh;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getNgaySB() {
        return NgaySB;
    }

    public void setNgaySB(String ngaySB) {
        NgaySB = ngaySB;
    }

    public String getMaNSB() {
        return MaNSB;
    }

    public void setMaNSB(String maNSB) {
        MaNSB = maNSB;
    }

    public String getMaTL() {
        return MaTL;
    }

    public void setMaTL(String maTL) {
        MaTL = maTL;
    }

    public byte[] getAnh() {
        return Anh;
    }

    public void setAnh(byte[] anh) {
        Anh = anh;
    }
}

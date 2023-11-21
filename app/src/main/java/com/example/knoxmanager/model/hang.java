package com.example.knoxmanager.model;

public class hang {
    private int maHang,maNhaSx;
    private String tenSp,gia;

    public hang() {
    }

    public hang(int maHang, int maNhaSx, String tenSp, String gia) {
        this.maHang = maHang;
        this.maNhaSx = maNhaSx;
        this.tenSp = tenSp;
        this.gia = gia;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public int getMaNhaSx() {
        return maNhaSx;
    }

    public void setMaNhaSx(int maLoai) {
        this.maNhaSx = maLoai;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}

package com.example.knoxmanager.model;

public class hang {
    private int maHang,maNhaSx;
    private String tenSp;
    private int gia;

    public hang() {
    }

    public hang(int maHang, int maNhaSx, String tenSp, int gia) {
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}

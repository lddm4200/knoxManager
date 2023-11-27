package com.example.knoxmanager.model;

public class NguoiDung {
    private String maNguoiDung,matKhau,hoTen;
    private int phanQuyen;

    public NguoiDung() {
    }

    public NguoiDung(String maNguoiDung, String matKhau, String hoTen, int phanQuyen) {
        this.maNguoiDung = maNguoiDung;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.phanQuyen = phanQuyen;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(int phanQuyen) {
        this.phanQuyen = phanQuyen;
    }
}

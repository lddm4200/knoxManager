package com.example.knoxmanager.model;

public class PhieuTheoDoi {
    private int maPhieu;
    private String maNguoiDung;
    private int maKH;
    private int maHang;
    private int giaMua;
    private int trangThai;
    private String ngayDat;
    private int soLuong;
    private int tongTien;

    public PhieuTheoDoi() {
    }

    public PhieuTheoDoi(int maPhieu, String maNguoiDung, int maKH, int maHang, int giaMua, int trangThai, String ngayDat, int soLuong, int tongTien) {
        this.maPhieu = maPhieu;
        this.maNguoiDung = maNguoiDung;
        this.maKH = maKH;
        this.maHang = maHang;
        this.giaMua = giaMua;
        this.trangThai = trangThai;
        this.ngayDat = ngayDat;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }

    public int getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(int maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public int getMaHang() {
        return maHang;
    }

    public void setMaHang(int maHang) {
        this.maHang = maHang;
    }

    public int getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(int giaMua) {
        this.giaMua = giaMua;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(String ngayDat) {
        this.ngayDat = ngayDat;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}

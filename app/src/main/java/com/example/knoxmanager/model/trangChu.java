package com.example.knoxmanager.model;

public class trangChu {

    public static final int TYPE_HANG = 1;
    public static final int TYPE_NHASX = 2;
    public static final int TYPE_PHIEU = 3;
    public static final int TYPE_TAIKHOAN = 4;
    public static final int TYPE_TTKHACHHANG = 5;

    private int image;
    private String name, soLuong;
    private int type;

    public trangChu(int image, String name, String soLuong, int type) {
        this.image = image;
        this.name = name;
        this.soLuong = soLuong;
        this.type = type;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

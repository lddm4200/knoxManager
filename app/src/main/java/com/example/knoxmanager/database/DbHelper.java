package com.example.knoxmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name="knox";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //bang tai khoan
        String tb_nguoiDung = "CREATE TABLE NGUOIDUNG(TAIKHOAN TEXT PRIMARY KEY, MATKHAU TEXT,HOTEN TEXT)";
        db.execSQL(tb_nguoiDung);

        String tb_nhasx = "create table nhaSx(maNhaSx INTEGER PRIMARY KEY AUTOINCREMENT,tenNhasx TEXT not null)";
        db.execSQL(tb_nhasx);

        String tb_TTKhachHang = "create table khachHang(maKhachHang INTEGER PRIMARY KEY AUTOINCREMENT,tenKhachHang TEXT NOT NULL,sdt TEXT NOT NULL,gioiTinh TEXT NOT NULL,diaChi TEXT NOT NULL)";
        db.execSQL(tb_TTKhachHang);

        String tb_hang = "create table hang(maHang INTEGER PRIMARY KEY AUTOINCREMENT,tenSp TEXT NOT NULL,maNhaSx INTEGER REFERENCES nhaSx(maNhaSx),gia TEXT NOT NULL)";
        db.execSQL(tb_hang);


//        String tb_phieuTheoDoi = "create table phieuTheoDoi(maPhieu INTEGER PRIMARY KEY AUTOINCREMENT,TAIKHOAN TEXT REFERENCES NGUOIDUNG(TAIKHOAN),maKhachHang INTEGER)";
//        db.execSQL(tb_phieuTheoDoi);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            onCreate(db);
        }
    }
}

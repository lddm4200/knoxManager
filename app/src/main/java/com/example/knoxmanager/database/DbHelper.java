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
        String tb_nguoiDung = "CREATE TABLE NGUOIDUNG(maNguoiDung TEXT PRIMARY KEY, matKhau TEXT,hoTen TEXT,phanQuyen INTEGER)";
        db.execSQL(tb_nguoiDung);

        String tb_nhasx = "create table nhaSx(maNhaSx INTEGER PRIMARY KEY AUTOINCREMENT,tenNhasx TEXT not null)";
        db.execSQL(tb_nhasx);

        String tb_TTKhachHang = "create table khachHang(maKhachHang INTEGER PRIMARY KEY AUTOINCREMENT,tenKhachHang TEXT NOT NULL,sdt TEXT NOT NULL,gioiTinh TEXT NOT NULL,diaChi TEXT NOT NULL)";
        db.execSQL(tb_TTKhachHang);

        String tb_hang = "create table hang(maHang INTEGER PRIMARY KEY AUTOINCREMENT,tenSp TEXT NOT NULL,maNhaSx INTEGER REFERENCES nhaSx(maNhaSx),gia TEXT NOT NULL)";
        db.execSQL(tb_hang);

        String tb_thongBao = "CREATE TABLE THONGBAO (maThongBao INTEGER PRIMARY KEY AUTOINCREMENT,maNguoiDung TEXT,tieuDe TEXT,noiDung TEXT,ngayDang TEXT,FOREIGN KEY (maNguoiDung) REFERENCES NGUOIDUNG (maNguoiDung))";
        db.execSQL(tb_thongBao);


//        String tb_phieuTheoDoi = "create table phieuTheoDoi(maPhieu INTEGER PRIMARY KEY AUTOINCREMENT,TAIKHOAN TEXT REFERENCES NGUOIDUNG(TAIKHOAN),maKhachHang INTEGER)";
//        db.execSQL(tb_phieuTheoDoi);

        // them bang
        db.execSQL("INSERT INTO NGUOIDUNG VALUES('admin','123456','Đào Duy Minh Long',0),('tk01','123456','Trần Duy Quang',1),('tk02','123456','Sằm Nam Khánh',1)");
        db.execSQL("INSERT INTO nhaSx VALUES(1,'evisu'),(2,'gucci'),(3,'Nike'),(4,'Adidas'),(5,'Docle'),(6,'Roway'),(7,'Teelab'),(8,'PoloGraph'),(9,'Internity'),(10,'Outerity')");
        db.execSQL("INSERT INTO khachHang VALUES(1,'Phạm Minh Hiếu','0987654321','nam','khu ba,Hoàng cương'),(2,'Vũ Thụ Vân Anh','0987654322','Nữ','THái Bình'),(3,'Đào Duy Minh Linh','0987654323','nam','Thái Bình')");
        db.execSQL("INSERT INTO THONGBAO VALUES(1,'admin','Tuyển nhân viên','Tuyển nhân viên số lượng lớn ưu tiên độ tuôi 20-32','01-12-2023')");
//        db.execSQL("INSERT INTO hang VALUES(1,'ao',1,'222')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            db.execSQL("DROP TABLE IF EXISTS nhaSx");
            db.execSQL("DROP TABLE IF EXISTS khachHang");
            db.execSQL("DROP TABLE IF EXISTS hang");
            db.execSQL("DROP TABLE IF EXISTS THONGBAO");
            onCreate(db);
        }
    }
}

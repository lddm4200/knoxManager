package com.example.knoxmanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String Db_name="knox";

    public DbHelper(@Nullable Context context) {
        super(context, Db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //bang tai khoan
        String tb_nguoiDung = "CREATE TABLE NGUOIDUNG(TAIKHOAN TEXT PRIMARY KEY, MATKHAU TEXT,HOTEN TEXT)";
        db.execSQL(tb_nguoiDung);


        String tk = "INSERT INTO NGUOIDUNG VALUES('long','123','dao duy minh long')";
        db.execSQL(tk);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if(i != i1){
            db.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            onCreate(db);
        }
    }
}

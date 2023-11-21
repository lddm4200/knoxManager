package com.example.knoxmanager.dao;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.NguoiDung;

import java.util.ArrayList;

public class NguoiDungDao {
    private final DbHelper dbHelper;

    public NguoiDungDao(Context context) {
        dbHelper = new DbHelper(context);
    }
    public ArrayList<NguoiDung> selectAll(){
        ArrayList<NguoiDung> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        try{
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG",null);
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                while (!cursor.isAfterLast()){
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setTaiKhoan(cursor.getString(0));
                    nguoiDung.setMatKhau(cursor.getString(1));
                    list.add(nguoiDung);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e) {
            Log.i(TAG, "Lỗi!", e);
        }
        return list;

    }
    public boolean checkLogin(String taiKhoan, String matKhau) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE TAIKHOAN = ? AND MATKHAU = ?",
                        new String[]{taiKhoan, matKhau});
        int row = cursor.getCount();
        return (row > 0);
    }
    public boolean checkusername(String taiKhoan) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE TAIKHOAN = ?",
                        new String[]{taiKhoan});
        int row = cursor.getCount();
        return (row > 0);
    }

    public boolean add(NguoiDung nguoiDung) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TAIKHOAN", nguoiDung.getTaiKhoan());
        values.put("MATKHAU", nguoiDung.getMatKhau());
        long row = sqLiteDatabase.insert("NGUOIDUNG", null, values);
        return (row > 0);
    }
}

package com.example.knoxmanager.dao;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.NguoiDung;

import java.util.ArrayList;
import java.util.List;

public class NguoiDungDao {
    private SQLiteDatabase db;

    public NguoiDungDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(NguoiDung tv){
        ContentValues values = new ContentValues();
        values.put("maNguoiDung",tv.getMaNguoiDung());
        values.put("matKhau",tv.getMatKhau());
        values.put("hoTen",tv.getHoTen());
        values.put("phanQuyen",tv.getPhanQuyen());
        return db.insert("NGUOIDUNG",null,values);
    }



    public long update(NguoiDung tv){
        ContentValues values = new ContentValues();
        values.put("maNguoiDung",tv.getMaNguoiDung());
        values.put("matKhau",tv.getMatKhau());
        values.put("hoTen",tv.getHoTen());
        values.put("phanQuyen",tv.getPhanQuyen());
        return db.update("NGUOIDUNG", values, "maNguoiDung = ?", new String[]{String.valueOf(tv.getMaNguoiDung())});
    }

    public long delete(String id){
        return db.delete("NGUOIDUNG", "maNguoiDung = ?", new String[]{String.valueOf(id)});
    }

    public List<NguoiDung> getAll() {
        String sql = "SELECT * FROM NGUOIDUNG";
        return getData(sql);
    }

    public List<NguoiDung> getAdmin() {
        String sql = "SELECT * FROM NGUOIDUNG WHERE phanQuyen = 0" ;
        return getData(sql);
    }
    public String getIDTao(String id) {
        Cursor cursor = db.rawQuery("SELECT hoTen FROM NGUOIDUNG WHERE maNguoiDung =?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String hoten = cursor.getString(0);
            cursor.close();
            return hoten;
        }
        cursor.close();
        return "Không tìm thấy";
    }
    public NguoiDung getID(String id) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE maNguoiDung=?";
        List<NguoiDung> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0); // Truy cập vào phần tử đầu tiên nếu danh sách không rỗng
        }
        return null;
    }

    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM NGUOIDUNG WHERE maNguoiDung=? AND matKhau=?";
        List<NguoiDung> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    public boolean checkUser(String username) {
        boolean usernameExists = false;
        Cursor cursor = db.rawQuery("SELECT * FROM NGUOIDUNG WHERE maNguoiDung = ?", new String[]{username});
        if (cursor.moveToFirst()) {
            usernameExists = true;
        }
        return usernameExists;
    }

    @SuppressLint("Range")
    private List<NguoiDung> getData(String sql, String... selectionArgs) {
        List<NguoiDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NguoiDung tv = new NguoiDung();
            tv.setMaNguoiDung(cursor.getString(cursor.getColumnIndex("maNguoiDung")));
            tv.setMatKhau(cursor.getString(cursor.getColumnIndex("matKhau")));
            tv.setHoTen(cursor.getString(cursor.getColumnIndex("hoTen")));
            tv.setPhanQuyen(cursor.getInt(cursor.getColumnIndex("phanQuyen")));
            list.add(tv);
        }
        return list;
    }
}

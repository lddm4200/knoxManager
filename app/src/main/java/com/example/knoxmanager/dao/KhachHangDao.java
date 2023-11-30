package com.example.knoxmanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDao {
    private SQLiteDatabase db;

    public KhachHangDao(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenKhachHang", obj.getTenKhachHang());
        values.put("sdt", obj.getSdt());
        values.put("gioiTinh", obj.getGioiTinh());
        values.put("diaChi", obj.getDiaChi());
        return db.insert("khachHang", null, values);
    }
    public long update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("tenKhachHang", obj.getTenKhachHang());
        values.put("sdt", obj.getSdt());
        values.put("gioiTinh", obj.getGioiTinh());
        values.put("diaChi", obj.getDiaChi());
        return db.update("khachHang", values, "maKhachHang = ?", new String[]{String.valueOf(obj.getMaKhachHang())});
    }
    public long delete(int id) {
        return db.delete("khachHang", "maKhachHang = ?", new String[]{String.valueOf(id)});
    }
    public List<KhachHang> getAll() {
        String sql = "SELECT * FROM khachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM khachHang WHERE maKhachHang=?";
        List<KhachHang> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            KhachHang obj = new KhachHang();
            obj.setMaKhachHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKhachHang"))));
            obj.setTenKhachHang(cursor.getString(cursor.getColumnIndex("tenKhachHang")));
            obj.setSdt(cursor.getString(cursor.getColumnIndex("sdt")));
            obj.setGioiTinh(cursor.getString(cursor.getColumnIndex("gioiTinh")));
            obj.setDiaChi(cursor.getString(cursor.getColumnIndex("diaChi")));

            Log.i("//==", obj.toString());
            list.add(obj);
        }
        return list;
    }
}

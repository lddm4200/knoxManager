package com.example.knoxmanager.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.hang;

import java.util.ArrayList;
import java.util.List;

public class HangDao {
    private SQLiteDatabase db;
    public HangDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(hang obj) {
        ContentValues values = new ContentValues();
        values.put("tenSp", obj.getTenSp());
        values.put("maNhaSx", obj.getMaNhaSx());
        values.put("gia", obj.getGia());
        return db.insert("hang", null, values);
    }

    public long update(hang obj) {
        ContentValues values = new ContentValues();
        values.put("tenSp", obj.getTenSp());
        values.put("maNhaSx", obj.getMaNhaSx());
        values.put("gia", obj.getGia());
        return db.update("hang", values, "maHang = ?", new String[]{String.valueOf(obj.getMaHang())});
    }

    public long delete(String id) {
        return db.delete("hang", "maHang = ?", new String[]{String.valueOf(id)});
    }

    public List<hang> getAll() {
        String sql = "SELECT * FROM hang";
        List<hang> list = getData(sql);
        return list;
    }

    public String getID(String id) {
        Cursor cursor = db.rawQuery("SELECT tenSp FROM hang WHERE maHang =?", new String[]{id});
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            String tenSp = cursor.getString(0);
            cursor.close();
            return tenSp;
        }
        cursor.close();
        return "Không tìm thấy";
    }
    public int getIDmoney(int id) {
        Cursor cursor = db.rawQuery("SELECT gia FROM hang WHERE maHang =?", new String[]{String.valueOf(id)});
        int giaMua=-1;
        if (cursor.moveToFirst()) {
            giaMua= cursor.getInt(0);
            cursor.close();
        }
        db.close();
        return giaMua;
    }

    @SuppressLint("Range")
    private List<hang> getData(String sql, String... selectionArgs) {
        List<hang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                hang obj = new hang();
                obj.setMaHang(cursor.getInt(0));
                obj.setTenSp(cursor.getString(1));
                obj.setMaNhaSx(cursor.getInt(2));
                obj.setGia(cursor.getInt(3));
                list.add(obj);
            }
        }
        return list;
    }
}

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

public class hangDao {
    private SQLiteDatabase db;
    public hangDao(Context context) {
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

    public hang getID(String id) {
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<hang> list = getData(sql, id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<hang> getData(String sql, String... selectionArgs) {
        List<hang> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.getCount() != 0){
            while (cursor.moveToNext()) {
                hang obj = new hang();
                obj.setMaHang(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHang"))));
                obj.setTenSp(cursor.getString(cursor.getColumnIndex("tenSp")));
                obj.setMaNhaSx(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNhaSx"))));
                obj.setGia(cursor.getString(cursor.getColumnIndex("gia")));

                list.add(obj);
            }
        }
        return list;
    }
}

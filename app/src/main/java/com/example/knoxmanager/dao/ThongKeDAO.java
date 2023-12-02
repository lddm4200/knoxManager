package com.example.knoxmanager.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.Top;

import java.util.ArrayList;

public class ThongKeDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ThongKeDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Top> getTop() {
        ArrayList<Top> list = new ArrayList<>();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT maHang, SUM(soLuong) AS TONGSL FROM PHIEUTHEODOI GROUP BY maHang ORDER BY TONGSL DESC LIMIT 10", null);
        while (cursor.moveToNext()) {
            Top top = new Top();
            @SuppressLint("Range") String maHang = cursor.getString(cursor.getColumnIndex("maHang"));
            @SuppressLint("Range") int tsoluong = cursor.getInt(cursor.getColumnIndex("TONGSL"));
            top.setTenSP(maHang);
            top.setSoLuong(tsoluong);
            list.add(top);
        }
        cursor.close();
        return list;
    }

}

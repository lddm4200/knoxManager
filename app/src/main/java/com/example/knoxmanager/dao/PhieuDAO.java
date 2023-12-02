package com.example.knoxmanager.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.PhieuTheoDoi;

import java.util.ArrayList;

public class PhieuDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;

    public PhieuDAO(Context context) {
        dbHelper = new DbHelper(context);
    }

    public ArrayList<PhieuTheoDoi> getAll() {
        ArrayList<PhieuTheoDoi> list = new ArrayList<>();
        sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PHIEUTHEODOI", null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                PhieuTheoDoi phieuTheoDoi = new PhieuTheoDoi();
                phieuTheoDoi.setMaPhieu(cursor.getInt(0));
                phieuTheoDoi.setMaNguoiDung(cursor.getString(1));
                phieuTheoDoi.setMaKH(cursor.getInt(2));
                phieuTheoDoi.setMaHang(cursor.getInt(3));
                phieuTheoDoi.setGiaMua(cursor.getInt(4));
                phieuTheoDoi.setTrangThai(cursor.getInt(5));
                phieuTheoDoi.setNgayDat(cursor.getString(6));
                phieuTheoDoi.setSoLuong(cursor.getInt(7));
                phieuTheoDoi.setTongTien(8);
                list.add(phieuTheoDoi);
            }
        }
        cursor.close();
        return list;
    }
}

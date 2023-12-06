package com.example.knoxmanager.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.Top;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThongKeDAO {
    private final DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public ThongKeDAO(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
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

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "SELECT SUM(tongTien) as doanhThu FROM PHIEUTHEODOI WHERE ngayDat BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<Integer>();
        Cursor cursor = sqLiteDatabase.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});
        while (cursor.moveToNext()) {
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndex("doanhThu"))));

            } catch (Exception e) {
                list.add(0);
            }
        }
        return list.get(0);
    }

//    @SuppressLint("Range")
//    public int getDoanhThu(String tuNgay, String denNgay){
//        String sqlDoanhThu = "SELECT SUM(tongTien) as doanhThu FROM PHIEUTHEODOI WHERE ngayDat BETWEEN ? AND ?";
//        List<Integer> list = new ArrayList<>();
//        Cursor c = sqLiteDatabase.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
//        while (c.moveToNext()){
//            try {
//                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
//            }catch (Exception e){
//                list.add(0);
//            }
//        }
//        return list.get(0);
//    }

//    @SuppressLint("Range")
//    public int getDoanhThuTheoThang(){
//        Date date = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        date.setDate(01);
//        String firt_date = format.format(date);
//        String last_date = format.format(new Date());
//
//        String sqlDoanhThu = "SELECT SUM(tongTien) as doanhThu FROM PHIEUTHEODOI WHERE ngayDat BETWEEN ? AND ?";
//        List<Integer> list = new ArrayList<>();
//        Cursor c = sqLiteDatabase.rawQuery(sqlDoanhThu,new String[]{firt_date,last_date});
//        while (c.moveToNext()){
//            try {
//                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
//            }catch (Exception e){
//                list.add(0);
//            }
//        }
//        return list.get(0);
//    }
}

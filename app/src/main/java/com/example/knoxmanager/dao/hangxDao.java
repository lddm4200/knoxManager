package com.example.knoxmanager.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;

import com.example.knoxmanager.database.DbHelper;
import com.example.knoxmanager.model.hangx;

import java.util.ArrayList;
import java.util.List;

public class hangxDao {
    private SQLiteDatabase db;
    public hangxDao(Context context) {
        DbHelper data = new DbHelper(context);
        db = data.getWritableDatabase();
    }
    public long insert(hangx ls){
        ContentValues values = new ContentValues();
        values.put("tenNhasx",ls.getTenNhasx());
        return db.insert("nhaSx",null,values);
    }

    public long update(hangx ls){
        ContentValues values = new ContentValues();
        values.put("tenNhasx",ls.getTenNhasx());
        return db.update("nhaSx",values,"maNhaSx=?",new String[]{String.valueOf(ls.getMaNhaSx())});
    }

    public int delete(String id){
        return db.delete("nhaSx","maNhaSx=?",new String[]{id});
    }
    public List<hangx> getAll(){
        String sql = "SELECT * FROM nhaSx";
        return getData(sql);
    }

    public hangx getID(String id){
        String sql = "SELECT * FROM nhaSx WHERE maNhaSx=?";
        List<hangx> list = getData(sql,id);
        return list.get(0);
    }
    @SuppressLint("Range")
    private List<hangx> getData(String sql, String...selectionArgs){
        List<hangx> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql,selectionArgs);

        while (c.moveToNext()){
            hangx ls = new hangx();
            ls.setMaNhaSx(Integer.parseInt(c.getString(c.getColumnIndex("maNhaSx"))));
            ls.setTenNhasx(c.getString(c.getColumnIndex("tenNhasx")));
            list.add(ls);
        }

        return list;
    }
}

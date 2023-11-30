package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.KhachHangAdrapter;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.model.KhachHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLThngTinKhachHang extends AppCompatActivity {
    private RecyclerView rcvThanhVien;
    private FloatingActionButton floatAddThanhVien;

    private KhachHangAdrapter adapter;

    private KhachHangDao khDAO;
    ArrayList<KhachHang> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlthng_tin_khach_hang);
        rcvThanhVien = findViewById(R.id.Fragment_khachHang_RecycelView);
        floatAddThanhVien =findViewById(R.id.Fragment_khachHang_FloatBTN);
        khDAO = new KhachHangDao(this);
        loadData();

    }
    public void loadData() {
        list.clear();
        list.addAll(khDAO.getAll());
        rcvThanhVien.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KhachHangAdrapter(QLThngTinKhachHang.this, list);
        rcvThanhVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
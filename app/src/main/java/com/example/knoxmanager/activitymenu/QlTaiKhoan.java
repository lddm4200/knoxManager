package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.NguoiDungAdrapter;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.logIn;
import com.example.knoxmanager.model.NguoiDung;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QlTaiKhoan extends AppCompatActivity {
    private RecyclerView rcvThanhVien;
    private FloatingActionButton floatAddThanhVien;

    private NguoiDungAdrapter adapter;

    private NguoiDungDao ndDAO;
    logIn dangNhap = new logIn();


    ArrayList<NguoiDung> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ql_tai_khoan);
        rcvThanhVien = findViewById(R.id.Fragment_ThanhVien_RecycelView);
        floatAddThanhVien = findViewById(R.id.Fragment_ThanhVien_FloatBTN);
        ndDAO = new NguoiDungDao(this);
        ImageButton ibtnBackTk = findViewById(R.id.ibtnBackTk);
        loadData();

        floatAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap.openDialog(QlTaiKhoan.this,1,list);
                loadData();
            }
        });

        ibtnBackTk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void loadData() {
        list.clear();
        list.addAll(ndDAO.getAll());
        rcvThanhVien.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NguoiDungAdrapter(this, list);
        rcvThanhVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
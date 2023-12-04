package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.HoaDonAdrapter;
import com.example.knoxmanager.adrapter.PhieuTheoDoiAdapter;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.PhieuTheoDoi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QLHoaDon extends AppCompatActivity {
    private ArrayList<PhieuTheoDoi> list = new ArrayList<>();
    PhieuDAO phieuDAO;
    KhachHangDao khachHangDao;
    HangDao hangDao;
    NguoiDungDao nguoiDungDao;
    HoaDonAdrapter phieuMuonAdapter;
    FloatingActionButton fltAdd;
    RecyclerView recyclerView;
    ImageButton ibtnBackHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        recyclerView=findViewById(R.id.rcvHoaDon);
        ibtnBackHoaDon=findViewById(R.id.ibtnBackHoaDon);

        phieuDAO = new PhieuDAO(this);
        PhieuTheoDoi phieuTheoDoi = new PhieuTheoDoi();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = phieuDAO.getAll();
        phieuMuonAdapter = new HoaDonAdrapter(this, list);


        ibtnBackHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        int level = phieuTheoDoi.getTrangThai();
        if (level==0){
            recyclerView.setAdapter(phieuMuonAdapter);
        }
    }
}
package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText edTen, edsdt, edGioiTinh, edDiaChi;
    TextView btnLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlthng_tin_khach_hang);
        rcvThanhVien = findViewById(R.id.Fragment_khachHang_RecycelView);
        floatAddThanhVien = findViewById(R.id.Fragment_khachHang_FloatBTN);
        khDAO = new KhachHangDao(this);
        ImageButton btnBack = findViewById(R.id.ibtnBackkh);

        loadData();
//        list.clear();
//        list.addAll(khDAO.getAll());
//        rcvThanhVien.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new KhachHangAdrapter(QLThngTinKhachHang.this, list);
//        rcvThanhVien.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        floatAddThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(QLThngTinKhachHang.this, 1, list);
                list.clear();
                list.addAll(khDAO.getAll());
                rcvThanhVien.setLayoutManager(new LinearLayoutManager(QLThngTinKhachHang.this));
                adapter = new KhachHangAdrapter(QLThngTinKhachHang.this, list);
                rcvThanhVien.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

    }

    public void loadData() {
        list.clear();
        list.addAll(khDAO.getAll());
        rcvThanhVien.setLayoutManager(new LinearLayoutManager(this));
        adapter = new KhachHangAdrapter(QLThngTinKhachHang.this, list);
        rcvThanhVien.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void openDialog(Context context, int type, ArrayList<KhachHang> list) {

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.dialog_khach_hang, null);
        builder.setView(view); // gan view vao hop thoai
        Dialog dialog = builder.create();
        dialog.show();

        KhachHangDao tvDao = new KhachHangDao(context);

        // anh xa
        edTen = dialog.findViewById(R.id.edtTenKhDialog);
        edsdt = dialog.findViewById(R.id.edtSdtKhDialog);
        edGioiTinh = dialog.findViewById(R.id.edtGioiTinhKhDialog);
        edDiaChi = dialog.findViewById(R.id.edtDiaChiKhDialog);
        btnLuu = dialog.findViewById(R.id.btn_dialogKh);
        //
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = edTen.getText().toString();
                String sdt = edsdt.getText().toString();
                String pass = edGioiTinh.getText().toString();
                String confirm = edDiaChi.getText().toString();

                if (hoten.trim().isEmpty() || sdt.trim().isEmpty() || pass.trim().isEmpty() || confirm.trim().isEmpty()) {
                    Toast.makeText(context, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else if (!validateSDT(sdt) || sdt.length() < 10) {
                    Toast.makeText(context, "Số điện thoại chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    KhachHang tv = new KhachHang(type, hoten, sdt, pass, confirm);
                    tvDao.insert(tv);
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    if (type == 1) {
                        list.clear();
                        list.addAll(tvDao.getAll());
                    }
                    dialog.dismiss();
                }
            }
        });

    }

    public boolean validateSDT(String sdt) {
        return sdt.matches("\\d++");
    }
}
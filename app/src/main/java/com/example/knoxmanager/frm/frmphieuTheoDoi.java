package com.example.knoxmanager.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.PhieuMuonAdapter;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.Hang;
import com.example.knoxmanager.model.KhachHang;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.PhieuTheoDoi;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class frmphieuTheoDoi extends Fragment {

    public frmphieuTheoDoi() {
        // Required empty public constructor
    }

    private ArrayList<PhieuTheoDoi> list = new ArrayList<>();
    PhieuDAO phieuDAO;
    KhachHangDao khachHangDao;
    HangDao hangDao;
    NguoiDungDao nguoiDungDao;
    PhieuMuonAdapter phieuMuonAdapter;
    FloatingActionButton fltAdd;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_theo_doi, container, false);
        //
        recyclerView = view.findViewById(R.id.rcvPM);
        fltAdd = view.findViewById(R.id.fltAddPM);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //
        phieuDAO = new PhieuDAO(getActivity());
        PhieuTheoDoi phieuTheoDoi = new PhieuTheoDoi();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = phieuDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), list);
        recyclerView.setAdapter(phieuMuonAdapter);
        fltAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.dialog_pm, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                Spinner spnKhachHang = view1.findViewById(R.id.spnTenKhachHang);
                Spinner spnSanPham = view1.findViewById(R.id.spnTenSanPham);
                EditText edtSoLuong = view1.findViewById(R.id.edtSoLuong);
                Button btnHuy = view1.findViewById(R.id.btnHuyPM);
                Button btnLuu = view1.findViewById(R.id.btnLuuPM);
                RadioButton rdoChua = view1.findViewById(R.id.rdoChuaGiao);
                RadioButton rdoDaGiao = view1.findViewById(R.id.rdoDaGiao);
                RadioButton rdoBiHuy = view1.findViewById(R.id.rdoDaHuy);
                //
                KhachHang khachHang = new KhachHang();
                Hang hang = new Hang();
                khachHangDao = new KhachHangDao(getActivity());
                hangDao = new HangDao(getActivity());
                //
                ArrayList<KhachHang> dsKH = (ArrayList<KhachHang>) khachHangDao.getAll();
                ArrayList<String> newdsKH = new ArrayList<>();
                int index = 0;
                for (KhachHang x : dsKH) {
                    newdsKH.add(x.getTenKhachHang());
                    if (x.getMaKhachHang() == khachHang.getMaKhachHang()) {
                        index = list.indexOf(x);
                    }
                }
                spnKhachHang.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_gallery_item, newdsKH));
                spnKhachHang.setSelection(index);
                //
                ArrayList<Hang> dsSP = (ArrayList<Hang>) hangDao.getAll();
                ArrayList<String> newdsSP = new ArrayList<>();
                int index3 = 0;
                for (Hang x : dsSP) {
                    newdsSP.add(x.getTenSp());
                    if (x.getMaHang() == hang.getMaHang()) {
                        index3 = list.indexOf(x);
                    }
                }
                spnSanPham.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_gallery_item, newdsSP));
                spnSanPham.setSelection(index3);
                //
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = getActivity().getIntent();
                        String user = i.getStringExtra("user");
                        nguoiDungDao = new NguoiDungDao(getActivity());
                        NguoiDung nguoiDung = nguoiDungDao.getID(user);
                        String userName = nguoiDung.getMaNguoiDung();
                        //
                        int maSP = dsSP.get(spnSanPham.getSelectedItemPosition()).getMaHang();
                        int maKH = dsKH.get(spnKhachHang.getSelectedItemPosition()).getMaKhachHang();
                        String ngay = sdf.format(new Date());
                        String sl = edtSoLuong.getText().toString();
                        if(sl.isEmpty()){
                            Toast.makeText(getActivity(), "Không được bỏ trống!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(sl)<=0){
                            Toast.makeText(getActivity(), "Số lượng phải lớn hơn 0!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        phieuTheoDoi.setMaHang(maSP);
                        phieuTheoDoi.setMaKH(maKH);
                        phieuTheoDoi.setNgayDat(ngay);
                        phieuTheoDoi.setMaNguoiDung(userName);
                        phieuTheoDoi.setSoLuong(Integer.parseInt(sl));
                        int trangThai = rdoChua.isChecked() ? 1 : (rdoDaGiao.isChecked() ? 0 : -1);
                        phieuTheoDoi.setTrangThai(trangThai);
                        if(phieuDAO.insert(phieuTheoDoi)!=-1){
                            list.clear();
                            list.addAll(phieuDAO.getAll());
                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            phieuMuonAdapter.notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(getActivity(), "Thêm thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return view;
    }

}
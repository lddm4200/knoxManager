package com.example.knoxmanager.frm;

import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.photoAdrapter;
import com.example.knoxmanager.adrapter.trangChuAdrapter;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.photo;
import com.example.knoxmanager.model.trangChu;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class frmtrangChu extends Fragment {


    public frmtrangChu() {
        // Required empty public constructor
    }
private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private photoAdrapter photoAdrapter;
    private RecyclerView rcvTrangChu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

    viewPager=view.findViewById(R.id.ViewPager);
    circleIndicator = view.findViewById(R.id.CircleIndicator);

    photoAdrapter = new photoAdrapter(getActivity(),photoList() );
    viewPager.setAdapter(photoAdrapter);

    circleIndicator.setViewPager(viewPager);
    photoAdrapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

    rcvTrangChu=view.findViewById(R.id.rcvTrangChu);
    GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        rcvTrangChu.setLayoutManager(gridLayoutManager);
        trangChuAdrapter adrapter = new trangChuAdrapter(getList());
        rcvTrangChu.setAdapter(adrapter);
        return view;
    }

    private List<trangChu> getList(){
        List<trangChu> list = new ArrayList<>();
        list.add(new trangChu(R.drawable.quan_ao,"Quản lý quần áo","so luong: 2",trangChu.TYPE_HANG));
        list.add(new trangChu(R.drawable.quan_ao,"Quản lý hãng","so luong: 2",trangChu.TYPE_NHASX));
        list.add(new trangChu(R.drawable.imgphieu,"Quản lý phiếu theo dõi","so luong: 0",trangChu.TYPE_PHIEU));
        list.add(new trangChu(R.drawable.imgtk,"Quản lý tài khoản","so luong: 1",trangChu.TYPE_TAIKHOAN));
        list.add(new trangChu(R.drawable.imgtk,"Quản lý TT khách hàng","so luong: 0",trangChu.TYPE_TTKHACHHANG));

        return list;
    }

    private List<photo> photoList(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.baner1));
        list.add(new photo(R.drawable.baner2));
        list.add(new photo(R.drawable.baner3));
        list.add(new photo(R.drawable.baner4));
        list.add(new photo(R.drawable.baner5));

        return list;
    }
}
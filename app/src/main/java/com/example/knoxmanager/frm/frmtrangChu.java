package com.example.knoxmanager.frm;

import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;


public class frmtrangChu extends Fragment {


    public frmtrangChu() {
        // Required empty public constructor
    }
    private TextView ten;
    NguoiDungDao nguoiDungDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        ten.findViewById(R.id.txtTen);
//        nguoiDungDao = new NguoiDungDao(getActivity());
//        Intent i = getIntent();
//        String user = i.getStringExtra("TEN");
//        NguoiDung nguoiDung=  nguoiDungDao.getID(user);
//        String username = nguoiDung.getTen();
//        ten.setText(username);
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }
}
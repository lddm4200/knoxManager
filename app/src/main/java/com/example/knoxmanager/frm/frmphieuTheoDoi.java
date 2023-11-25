package com.example.knoxmanager.frm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knoxmanager.R;

public class frmphieuTheoDoi extends Fragment {

    public frmphieuTheoDoi() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_phieu_theo_doi, container, false);
    }
}
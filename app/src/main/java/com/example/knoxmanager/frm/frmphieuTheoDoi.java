package com.example.knoxmanager.frm;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.PhieuMuonAdapter;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.PhieuTheoDoi;

import java.util.ArrayList;

public class frmphieuTheoDoi extends Fragment {

    public frmphieuTheoDoi() {
        // Required empty public constructor
    }

    private ArrayList<PhieuTheoDoi> list = new ArrayList<>();
    PhieuDAO phieuDAO;
    PhieuMuonAdapter phieuMuonAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_phieu_theo_doi, container, false);
        recyclerView = view.findViewById(R.id.rcvPM);
        phieuDAO = new PhieuDAO(getActivity());
        PhieuTheoDoi phieuTheoDoi = new PhieuTheoDoi();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = phieuDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(), list);
        recyclerView.setAdapter(phieuMuonAdapter);
        return view;
    }
}
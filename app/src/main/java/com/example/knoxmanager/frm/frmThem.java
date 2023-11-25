package com.example.knoxmanager.frm;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.knoxmanager.R;
import com.example.knoxmanager.logIn;

public class frmThem extends Fragment {

    public frmThem() {
        // Required empty public constructor
    }
    private Button btnDangXuat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnDangXuat=view.findViewById(R.id.btnDangXuat);

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), logIn.class));
            }
        });

        return view;
    }
}
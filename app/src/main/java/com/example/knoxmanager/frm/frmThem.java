package com.example.knoxmanager.frm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.activitymenu.QlTaiKhoan;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.logIn;
import com.example.knoxmanager.model.NguoiDung;

public class frmThem extends Fragment {

    public frmThem() {
        // Required empty public constructor
    }
    private Button btnDangXuat,btnQLNV,btnThongBao;
    TextView ndUser,ndcv;
    NguoiDungDao ndDao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnDangXuat=view.findViewById(R.id.btnDangXuat);
        btnQLNV=view.findViewById(R.id.btnQLNV);
        ndUser=view.findViewById(R.id.tenTk_frrmMenu);
        ndcv=view.findViewById(R.id.chucVu_frrmMenu);
        btnThongBao=view.findViewById(R.id.btnThongBao);

        Intent i = getActivity().getIntent();
        String user = i.getStringExtra("user");
        ndDao = new NguoiDungDao(getActivity());
        NguoiDung nguoiDung = ndDao.getID(user);
        String userName = nguoiDung.getHoTen();
        ndUser.setText(userName);
        int level = nguoiDung.getPhanQuyen();
        if (level==0){
            ndcv.setText("(Quản Lý)");
        }else{
            ndcv.setText("(Nhân Viên)");
            btnQLNV.setVisibility(View.GONE);
            btnThongBao.setVisibility(View.GONE);
        }

        btnQLNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(getActivity(), QlTaiKhoan.class));
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setTitle("Cảnh báo!");
                builder1.setMessage("Bạn có chắc chắn muốn thoat không?");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(), logIn.class));
                    }
                });
                builder1.setNegativeButton("No",null);
                builder1.show();

            }
        });

        return view;
    }
}
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

import com.example.knoxmanager.R;
import com.example.knoxmanager.activitymenu.QLThngTinKhachHang;
import com.example.knoxmanager.activitymenu.QLThongBao;
import com.example.knoxmanager.activitymenu.QlTaiKhoan;
//import com.example.knoxmanager.activitymenu.QLThongBao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.logIn;
import com.example.knoxmanager.model.NguoiDung;

public class frmThem extends Fragment {

    public frmThem() {
        // Required empty public constructor
    }
    private Button btnQlTTKh,btnDangXuat,btnQLNV,btnThongBao;
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
        btnQlTTKh=view.findViewById(R.id.btnQLTTKH);

        btnQlTTKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QLThngTinKhachHang.class));
            }
        });

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

        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QLThongBao.class));
            }
        });

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn đăng xuất?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), logIn.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return view;
    }
}
package com.example.knoxmanager.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.hangxadrapter;
import com.example.knoxmanager.dao.HangxDao;
import com.example.knoxmanager.model.Hangx;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frmNhasx extends Fragment {
    ListView lvLoaiSach;
    ArrayList<Hangx> list;
    static HangxDao dao;
    hangxadrapter adapter;
    Hangx item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMahang, edTenhang;
    TextView btnSave, btnCancel;
    public frmNhasx() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hangx,container,false);
        lvLoaiSach = v.findViewById(R.id.Fragment_hangx_RecycelView);
        fab = v.findViewById(R.id.Fragment_LoaiSach_FloatBTN);
        dao = new HangxDao(getActivity());

        list = (ArrayList<Hangx>) dao.getAll();
        adapter = new hangxadrapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });

        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });

        return v;
    }

    void capNhatLv() {
        list = (ArrayList<Hangx>) dao.getAll();
        adapter = new hangxadrapter(getActivity(), this, list);
        lvLoaiSach.setAdapter(adapter);
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_hangx);
        edMahang = dialog.findViewById(R.id.edMahangx);
        edTenhang = dialog.findViewById(R.id.editText_tenhangx);
        btnSave = dialog.findViewById(R.id.btn_edithangx);

        edMahang.setEnabled(false);
        if (type != 0) {
            edMahang.setText(String.valueOf(item.getMaNhaSx()));
            edTenhang.setText(item.getTenNhasx());
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Hangx();
                item.setTenNhasx(edTenhang.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (dao.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaNhaSx(Integer.parseInt(edMahang.getText().toString()));
                        if (dao.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    public int validate() {
        int check = 1;
        if (edTenhang.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;

        }
        return check;

    }
}
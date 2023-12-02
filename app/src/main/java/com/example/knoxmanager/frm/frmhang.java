package com.example.knoxmanager.frm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.hangAdrapter;
import com.example.knoxmanager.adrapter.hangxspinner;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.HangxDao;
import com.example.knoxmanager.model.Hang;
import com.example.knoxmanager.model.hangx;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class frmhang extends Fragment {
    ListView lvSach;
    HangDao sachDAO;
    hangAdrapter adapter;
    Hang item;
    ArrayList<Hang> list = new ArrayList<>();


    FloatingActionButton fab;
    Dialog dialog;
    EditText edMahang, edTenhang, edGia;
    Spinner spinner;
    TextView btnSave;

    hangxspinner spinnerAdapter;
    ArrayList<hangx> listHangx = new ArrayList<>();
    HangxDao loaiSachDAO;
    hangx hangx;
    int maNhaSx, position;

    private SearchView searchView;


    public frmhang() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hang, container, false);
        lvSach = v.findViewById(R.id.lvHang);
        sachDAO = new HangDao(getActivity());

//        capNhatLv();
        fab = v.findViewById(R.id.addHang);
        list = (ArrayList<Hang>) sachDAO.getAll();
        adapter = new hangAdrapter(getContext(), this, list);
        lvSach.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<Hang>) sachDAO.getAll();
        adapter = new hangAdrapter(getContext(), this, list);
        lvSach.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.delete(Id);
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

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.diglog_hang);
        edMahang = dialog.findViewById(R.id.edMaHang);
        edTenhang = dialog.findViewById(R.id.edTenHang);
        spinner = dialog.findViewById(R.id.spnhaSx);
        edGia = dialog.findViewById(R.id.edgia);
        btnSave = dialog.findViewById(R.id.btnSavehang);

        listHangx = new ArrayList<>();
        loaiSachDAO = new HangxDao(context);
        listHangx = (ArrayList<hangx>) loaiSachDAO.getAll();

        spinnerAdapter = new hangxspinner(context, listHangx);
        spinner.setAdapter(spinnerAdapter);
        // lay maLoaiSach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNhaSx = listHangx.get(position).getMaNhaSx();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        edMahang.setEnabled(false);
        if (type != 0) {
            edMahang.setText(String.valueOf(item.getMaHang()));
            edTenhang.setText(item.getTenSp());
            edGia.setText(String.valueOf(item.getGia()));

            for (int i = 0; i < listHangx.size(); i++)
                if (item.getMaNhaSx() == (listHangx.get(i).getMaNhaSx())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spinner.setSelection(position);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new Hang();
                item.setTenSp(edTenhang.getText().toString());
                item.setMaNhaSx(maNhaSx);
                item.setGia(Integer.parseInt(edGia.getText().toString()));



                if (validate() > 0) {
                    if (type == 0) {
                        if (sachDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMaHang(Integer.parseInt(edMahang.getText().toString()));
                        if (sachDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (edTenhang.getText().length() == 0 || edGia.getText().length() == 0 ) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public boolean iSo ( String so){
        return so.matches("\\d+");
    }
    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
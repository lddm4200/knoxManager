package com.example.knoxmanager.adrapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NguoiDungAdrapter extends RecyclerView.Adapter<NguoiDungAdrapter.viewholder> {
//
    private NguoiDungDao ndDAO;
    private final Context context;
    private final ArrayList<NguoiDung> list;

    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public NguoiDungAdrapter(Context context, ArrayList<NguoiDung> list) {
        this.context = context;
        this.list = list;
        ndDAO=new NguoiDungDao(context);
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_nguoi_dung,null);

        viewholder vholder = new viewholder(view);
        vholder.setOnItemClickListener(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        if (position >= 0 && position < list.size()){
            NguoiDung tv = list.get(position);



            if (tv.getPhanQuyen() == 0){
                holder.txtPhanQuyen.setText("Chức vụ: Quản lý");
            }else {
                holder.txtPhanQuyen.setText("Chức vụ: Nhân viên");
            }
            holder.txtTenThanhVien.setText("Họ tên: " +tv.getHoTen());

            holder.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int i) {
                    opendialog(tv);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        private OnItemClickListener listener;

        private TextView txtPhanQuyen,txtTenThanhVien;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtTenThanhVien = itemView.findViewById(R.id.qpNguoiDung);
            txtPhanQuyen = itemView.findViewById(R.id.hoTenTkNguoiDung);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        public void setOnItemClickListener(OnItemClickListener mlistener) {
            listener = mlistener;
        }
    }

    public void opendialog(NguoiDung tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.thong_tin_nguoi_dung, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextView txtMaThanhVien = view.findViewById(R.id.txtMaThanhVien);
        TextView txtHoVaTen = view.findViewById(R.id.txtHoVaTen);
        TextView txtMatKhau = view.findViewById(R.id.txtMatKhau);
        TextView txtChucVu = view.findViewById(R.id.txtChucVu);
        Button btnUpdateTV = view.findViewById(R.id.btnUpdateTV);
        Button btnDeleteTV = view.findViewById(R.id.btnDeleteTV);


        txtMaThanhVien.setText("Mã thành viên: "+tv.getMaNguoiDung());
        txtHoVaTen.setText("Họ và tên: "+tv.getHoTen());
        txtMatKhau.setText("Mật khẩu: "+tv.getMatKhau());
        if (tv.getPhanQuyen() == 0){
            txtChucVu.setText("Chức vụ: Quản lý");
        }else {
            txtChucVu.setText("Chức vụ: Nhân viên");
        }

        btnDeleteTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setTitle("Cảnh báo!");
                builder1.setMessage("Bạn có chắc chắn muốn xóa không?");
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (ndDAO.delete(tv.getMaNguoiDung()) != -1){
                            list.clear();
                            list.addAll(ndDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder1.setNegativeButton("No",null);
                builder1.show();
            }
        });
        btnUpdateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogUpdate(tv);
                dialog.dismiss();
            }
        });
    }

    public void dialogUpdate(NguoiDung tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_nguoi_dung, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        TextInputEditText txtMaNguoiDungAdd = view.findViewById(R.id.edMaNguoiDung);
        TextInputEditText txtHoTenAdd = view.findViewById(R.id.editText_tenNguoiDung);
        TextInputEditText txtMatKhauAdd = view.findViewById(R.id.editText_MkNguoiDung);
        Spinner spinnerOptions = view.findViewById(R.id.spnChucVu);
        TextView btnLuu = view.findViewById(R.id.btn_dialogluuNguoiDung);

        txtMaNguoiDungAdd.setText(tv.getMaNguoiDung());
        txtHoTenAdd.setText(tv.getHoTen());
        txtMatKhauAdd.setText(tv.getMatKhau());

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if (tv.getPhanQuyen() == 1){
            adapter.add("Nhân Viên");
            adapter.add("Quản Lý");
        }else {
            adapter.add("Quản Lý");
            adapter.add("Nhân Viên");
        }
        //nnn

        ArrayList<Integer> chucvu = new ArrayList<>();
        spinnerOptions.setAdapter(adapter);

        spinnerOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                if (selectedOption.equals("Nhân Viên")) {
                    chucvu.clear();
                    chucvu.add(1);
                } else if (selectedOption.equals("Quản Lý")) {
                    chucvu.clear();
                    chucvu.add(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Xử lý khi không có tùy chọn nào được chọn

            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = txtMaNguoiDungAdd.getText().toString();
                String hoten = txtHoTenAdd.getText().toString();
                String pass = txtMatKhauAdd.getText().toString();

                if (user.trim().isEmpty() || hoten.trim().isEmpty() || pass.trim().isEmpty()) {
                    Toast.makeText(context, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setMaNguoiDung(user);
                    tv.setHoTen(hoten);
                    tv.setMatKhau(pass);
                    tv.setPhanQuyen(chucvu.get(0));
                    if (ndDAO.update(tv) == 1){
                        Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(context, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }



}

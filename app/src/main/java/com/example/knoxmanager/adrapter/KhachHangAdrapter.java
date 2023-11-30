package com.example.knoxmanager.adrapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.model.KhachHang;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class KhachHangAdrapter extends RecyclerView.Adapter<KhachHangAdrapter.viewholder> {
    private Context context;
    private ArrayList<KhachHang> list;
    private KhachHangDao khDAO;

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_khach_hang,null);

        // 5.  Khai báo và truyền đối tượng OnItemClickListener vào ViewHolder
        viewholder vholder = new viewholder(view);
        vholder.setlongOnItemClickListener(vholder.listener);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        KhachHang tv = list.get(position);
//        holder.txtMaKh.setText(list.get(position).getMaKhachHang());
        holder.txtTenKh.setText("Họ tên: "+tv.getTenKhachHang());
        holder.txtSoDienThoai.setText("SDT: "+tv.getSdt());
        holder.txtGioiTinh.setText("Giới Tính: "+tv.getGioiTinh());
        holder.txtDiaChi.setText("Địa chỉ: "+tv.getDiaChi());

        holder.setlongOnItemClickListener(new OnLongClickListener() {
            @Override
            public void onlongItemClick(int i) {
                dialogUpdate(tv);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list.size() != 0){
            return list.size();
        }
        return 0;
    }

    public interface OnLongClickListener {
        void onlongItemClick(int i);
    }
    public KhachHangAdrapter(Context context, ArrayList<KhachHang> list) {
        this.context = context;
        this.list = list;
        khDAO = new KhachHangDao(context);
    }

    public static class viewholder extends RecyclerView.ViewHolder {
        // B2 : khai báo một biến thành viên để lưu trữ đối tượng OnItemClickListener
        private OnLongClickListener listener;

        private TextView txtMaKh,txtTenKh,txtSoDienThoai,txtGioiTinh,txtDiaChi;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            txtMaKh = itemView.findViewById(R.id.txtMaKh);
            txtTenKh = itemView.findViewById(R.id.txtTenKh);
            txtSoDienThoai = itemView.findViewById(R.id.txtSdtKh);
            txtGioiTinh = itemView.findViewById(R.id.txtGioiTinhKh);
            txtDiaChi = itemView.findViewById(R.id.txtDiaChiKh);

            // 3 : thiết lập setOnLongClickListener cho itemView.
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onlongItemClick(position);
                            return true;
                        }
                        }
                        return false;

                    }
            });
        }

        //  4. Thêm phương thức setOnClickListener để truyền đối tượng OnItemClickListener từ Adapter vào ViewHolder
        public void setlongOnItemClickListener(OnLongClickListener mlistener) {
            listener = mlistener;
        }
    }

    public void dialogUpdate(KhachHang tv){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_khach_hang, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText txtMaKhnAdd = view.findViewById(R.id.edtMaKhDialog);
        EditText txtHoTenAdd = view.findViewById(R.id.edtTenKhDialog);
        EditText txtSoDienThoaiAdd = view.findViewById(R.id.edtSdtKhDialog);
        EditText txtgioiTinhAdd = view.findViewById(R.id.edtGioiTinhKhDialog);
        EditText txtDiaChiAdd = view.findViewById(R.id.edtDiaChiKhDialog);
        TextView btnLuu = view.findViewById(R.id.btn_dialogKh);


        txtHoTenAdd.setText(tv.getTenKhachHang());
        txtSoDienThoaiAdd.setText(tv.getSdt());
        txtgioiTinhAdd.setText(tv.getGioiTinh());
        txtDiaChiAdd.setText(tv.getDiaChi());



        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten = txtHoTenAdd.getText().toString();
                String sdt = txtSoDienThoaiAdd.getText().toString();
                String gioiTinh = txtgioiTinhAdd.getText().toString();
                String diaChi = txtDiaChiAdd.getText().toString();

                if ( hoten.trim().isEmpty() || sdt.trim().isEmpty() || gioiTinh.trim().isEmpty()|| diaChi.trim().isEmpty()) {
                    Toast.makeText(context, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                }else if (!validateSDT(sdt) || sdt.length() < 10){
                    Toast.makeText(context, "Số điện thoại chưa đúng", Toast.LENGTH_SHORT).show();
                } else {
                    tv.setTenKhachHang(hoten);
                    tv.setSdt(sdt);
                    tv.setGioiTinh(gioiTinh);
                    tv.setDiaChi(diaChi);
                    if (khDAO.update(tv) == 1){
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
    public boolean validateSDT(String sdt){
        return sdt.matches("\\d++");
    }
    }






package com.example.knoxmanager.adrapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.Hang;
import com.example.knoxmanager.model.KhachHang;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.PhieuTheoDoi;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.viewHolder> {
    private final Context context;
    private final ArrayList<PhieuTheoDoi> list;
    PhieuDAO phieuDAO;
    KhachHangDao khachHangDao;
    HangDao hangDao;
    NguoiDungDao nguoiDungDao;

    public PhieuMuonAdapter(Context context, ArrayList<PhieuTheoDoi> list) {
        this.context = context;
        this.list = list;
        phieuDAO = new PhieuDAO(context);
        khachHangDao = new KhachHangDao(context);
        hangDao = new HangDao(context);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_pm, null);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PhieuTheoDoi phieuTheoDoi = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        holder.txtMaPhieu.setText("Mã phiếu: "+list.get(position).getMaPhieu());
        String tenNguoiTao = String.valueOf(nguoiDungDao.getIDTao(phieuTheoDoi.getMaNguoiDung()));
        holder.txtNguoiTao.setText("Người tạo: "+tenNguoiTao);
        String tenKH = khachHangDao.getID(String.valueOf(phieuTheoDoi.getMaKH()));
        holder.txtKhachHang.setText("Khách hàng: "+tenKH);
        String tenSP = hangDao.getID(String.valueOf(phieuTheoDoi.getMaHang()));
        holder.txtTenSP.setText("Sản phẩm: "+tenSP);
        int gia = hangDao.getIDmoney(phieuTheoDoi.getMaHang());
        holder.txtGiaMua.setText("Giá mua: "+decimalFormat.format(gia)+" VNĐ");
        if(list.get(position).getTrangThai()==0){
            holder.txtTrangThai.setText("Đã giao");
            holder.txtTrangThai.setTextColor(Color.parseColor("#1ED300"));
        }
        else if(list.get(position).getTrangThai()==1){
            holder.txtTrangThai.setText("Chưa giao");
            holder.txtTrangThai.setTextColor(Color.parseColor("#D327CC"));
        }
        else{
            holder.txtTrangThai.setText("Đã hủy");
            holder.txtTrangThai.setTextColor(Color.parseColor("#FF0000"));
        }
        holder.txtNgayDat.setText("Ngày đặt: "+list.get(position).getNgayDat());
        holder.txtSoLuong.setText("Số lượng: "+list.get(position).getSoLuong());
        holder.txtTongTien.setText("Tổng tiền: "+decimalFormat.format(gia*list.get(position).getSoLuong())+" VNĐ");
        holder.btnXoaPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa phiếu này?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String maPhieu = String.valueOf(phieuTheoDoi.getMaPhieu());
                        long delete = phieuDAO.delete(maPhieu);
                        if (delete > 0) {
                            list.clear();
                            list.addAll(phieuDAO.getAll());
                            notifyDataSetChanged();
                            Toast.makeText(context, "Đã xóa " + holder.txtMaPhieu.getText().toString() + "", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                Dialog dialog = builder.create();
                dialog.show();
            }

        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_pm,null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                //
                Spinner spnKhachHang = view1.findViewById(R.id.spnTenKhachHang);
                Spinner spnSanPham = view1.findViewById(R.id.spnTenSanPham);
                EditText edtSoLuong = view1.findViewById(R.id.edtSoLuong);
                Button btnHuy = view1.findViewById(R.id.btnHuyPM);
                Button btnLuu = view1.findViewById(R.id.btnLuuPM);
                RadioButton rdoChua = view1.findViewById(R.id.rdoChuaGiao);
                RadioButton rdoDaGiao = view1.findViewById(R.id.rdoDaGiao);
                RadioButton rdoBiHuy = view1.findViewById(R.id.rdoDaHuy);
                //
                KhachHang khachHang = new KhachHang();
                Hang hang = new Hang();
                khachHangDao = new KhachHangDao(context);
                hangDao = new HangDao(context);
                //
                ArrayList<KhachHang> dsKH = (ArrayList<KhachHang>) khachHangDao.getAll();
                ArrayList<String> newdsKH = new ArrayList<>();
                int index = 0;
                for (KhachHang x : dsKH) {
                    newdsKH.add(x.getTenKhachHang());
                    if (x.getMaKhachHang() == khachHang.getMaKhachHang()) {
                        index = list.indexOf(x);
                    }
                }
                spnKhachHang.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_gallery_item, newdsKH));
                spnKhachHang.setSelection(index);
                //
                ArrayList<Hang> dsSP = (ArrayList<Hang>) hangDao.getAll();
                ArrayList<String> newdsSP = new ArrayList<>();
                int index3 = 0;
                for (Hang x : dsSP) {
                    newdsSP.add(x.getTenSp());
                    if (x.getMaHang() == hang.getMaHang()) {
                        index3 = list.indexOf(x);
                    }
                }
                spnSanPham.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_gallery_item, newdsSP));
                spnSanPham.setSelection(index3);
                //
                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btnLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //
                        int maSP = dsSP.get(spnSanPham.getSelectedItemPosition()).getMaHang();
                        int maKH = dsKH.get(spnKhachHang.getSelectedItemPosition()).getMaKhachHang();
                        String sl = edtSoLuong.getText().toString();
                        if(sl.isEmpty()){
                            Toast.makeText(context, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (Integer.parseInt(sl)<=0){
                            Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        phieuTheoDoi.setMaHang(maSP);
                        phieuTheoDoi.setMaKH(maKH);
                        phieuTheoDoi.setSoLuong(Integer.parseInt(sl));
                        int trangThai = rdoChua.isChecked() ? 1 : (rdoDaGiao.isChecked() ? 0 : -1);
                        phieuTheoDoi.setTrangThai(trangThai);
                        if(phieuDAO.update(phieuTheoDoi)!=-1){
                            list.clear();
                            list.addAll(phieuDAO.getAll());
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }
                        else {
                            Toast.makeText(context, "Update thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPhieu, txtNguoiTao, txtKhachHang, txtTenSP, txtGiaMua, txtTrangThai, txtNgayDat, txtSoLuong, txtTongTien;
        Button btnXoaPM;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieu = itemView.findViewById(R.id.txtMaPhieu);
            txtNguoiTao = itemView.findViewById(R.id.txtNguoiTao);
            txtKhachHang = itemView.findViewById(R.id.txtKhachHang);
            txtTenSP = itemView.findViewById(R.id.txtTenSP);
            txtGiaMua = itemView.findViewById(R.id.txtGiaMua);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThai);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            btnXoaPM = itemView.findViewById(R.id.btnXoaPM);
        }
    }
}

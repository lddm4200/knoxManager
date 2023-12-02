package com.example.knoxmanager.adrapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.PhieuTheoDoi;

import java.util.ArrayList;

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
        holder.txtMaPhieu.setText("Mã phiếu: "+list.get(position).getMaPhieu());
        String tenNguoiTao = String.valueOf(nguoiDungDao.getIDTao(phieuTheoDoi.getMaNguoiDung()));
        holder.txtNguoiTao.setText("Người tạo: "+tenNguoiTao);
        String tenKH = khachHangDao.getID(String.valueOf(phieuTheoDoi.getMaKH()));
        holder.txtKhachHang.setText("Khách hàng: "+tenKH);
        String tenSP = hangDao.getID(String.valueOf(phieuTheoDoi.getMaHang()));
        holder.txtTenSP.setText("Sản phẩm: "+tenSP);
        int gia = hangDao.getIDmoney(phieuTheoDoi.getMaHang());
        holder.txtGiaMua.setText("Giá mua: "+gia+" VNĐ");
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
        holder.txtTongTien.setText("Tổng tiền: "+gia*list.get(position).getSoLuong()+" VNĐ");

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

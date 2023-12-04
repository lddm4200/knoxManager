package com.example.knoxmanager.adrapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.HangDao;
import com.example.knoxmanager.dao.KhachHangDao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.PhieuDAO;
import com.example.knoxmanager.model.PhieuTheoDoi;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HoaDonAdrapter extends RecyclerView.Adapter<HoaDonAdrapter.viewHolder>{

    private final Context context;
    private final ArrayList<PhieuTheoDoi> list;
    PhieuDAO phieuDAO;
    KhachHangDao khachHangDao;
    HangDao hangDao;
    NguoiDungDao nguoiDungDao;

    public HoaDonAdrapter(Context context, ArrayList<PhieuTheoDoi> list) {
        this.context = context;
        this.list = list;
        phieuDAO = new PhieuDAO(context);
        khachHangDao = new KhachHangDao(context);
        hangDao = new HangDao(context);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @NonNull
    @Override
    public HoaDonAdrapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_hoa_don, null);
        return new HoaDonAdrapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        PhieuTheoDoi phieuTheoDoi = list.get(position);
        DecimalFormat decimalFormat = new DecimalFormat("###,###");
        holder.txtMaPhieu.setText("Mã Hóa Đơn: " + list.get(position).getMaPhieu());
        String tenNguoiTao = String.valueOf(nguoiDungDao.getIDTao(phieuTheoDoi.getMaNguoiDung()));
        holder.txtNguoiTao.setText("Người tạo: " + tenNguoiTao);
        String tenKH = khachHangDao.getID(String.valueOf(phieuTheoDoi.getMaKH()));
        holder.txtKhachHang.setText("Khách hàng: " + tenKH);
        String tenSP = hangDao.getID(String.valueOf(phieuTheoDoi.getMaHang()));
        holder.txtTenSP.setText("Sản phẩm: " + tenSP);
        int gia = hangDao.getIDmoney(phieuTheoDoi.getMaHang());
//        holder.txtGiaMua.setText("Giá mua: " + decimalFormat.format(gia) + " VNĐ");

        holder.txtNgayDat.setText("Ngày đặt: " + list.get(position).getNgayDat());
        holder.txtSoLuong.setText("Số lượng: " + list.get(position).getSoLuong());
        holder.txtTongTien.setText("Tổng tiền: " + decimalFormat.format(gia * list.get(position).getSoLuong()) + " VNĐ");

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView txtMaPhieu, txtNguoiTao, txtKhachHang, txtTenSP, txtGiaMua, txtTrangThai, txtNgayDat, txtSoLuong, txtTongTien;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaPhieu = itemView.findViewById(R.id.maHoaDon);
            txtNguoiTao = itemView.findViewById(R.id.nguoiTaoHD);
            txtKhachHang = itemView.findViewById(R.id.khachHangHD);
            txtTenSP = itemView.findViewById(R.id.sanPhamHD);
            txtNgayDat = itemView.findViewById(R.id.ngayTaoHD);
            txtSoLuong = itemView.findViewById(R.id.SoLuongHD);
            txtTongTien = itemView.findViewById(R.id.tongTienHD);
        }
    }

}

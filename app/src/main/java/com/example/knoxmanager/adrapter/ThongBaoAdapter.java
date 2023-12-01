package com.example.knoxmanager.adrapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knoxmanager.R;
import com.example.knoxmanager.activitymenu.QLThongBao;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.ThongBao;

import java.util.ArrayList;

public class ThongBaoAdapter extends ArrayAdapter<ThongBao> {

    private Context context;
    QLThongBao qlThongBao;
    private ArrayList<ThongBao> list;

    private boolean hideButtonRemoveTB;

    NguoiDungDao ndDao;

    TextView txtTieuDe,txtNoiDung,txtHoTenNguoiDung,txtNgay;


    ImageView imgDeleteThongBao;

    public ThongBaoAdapter(@NonNull Context context,  QLThongBao qlThongBao, ArrayList<ThongBao> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.qlThongBao = qlThongBao;
    }
    public ThongBaoAdapter(@NonNull Context context, ArrayList<ThongBao> list, boolean hideButtonRemoveTB) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.hideButtonRemoveTB = hideButtonRemoveTB;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thong_bao,parent,false);
        }
        final ThongBao item = list.get(position);
        ndDao = new NguoiDungDao(context);
        if (item != null){
            txtTieuDe = view.findViewById(R.id.tieuDe_thongBao);
            txtTieuDe.setText(item.getTieuDe());
            txtNoiDung = view.findViewById(R.id.noiDUng_thongBao);
            txtNoiDung.setText(item.getNoiDung());

            NguoiDung nguoiDung = ndDao.getID(item.getMaNguoiDung());

            txtHoTenNguoiDung = view.findViewById(R.id.ngTao_thongBao);
            txtHoTenNguoiDung.setText("Người đăng: "+ nguoiDung.getHoTen());

            txtNgay=view.findViewById(R.id.ngayTao_thongBao);
            txtNgay.setText(item.getNgayDang());
            imgDeleteThongBao = view.findViewById(R.id.deleteThongBao);
            if (hideButtonRemoveTB){
                imgDeleteThongBao.setVisibility(View.INVISIBLE);
            }

        }

        imgDeleteThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    qlThongBao.xoa(String.valueOf(item.getMaThongBao()));
            }
        });
        return view;
    }
}

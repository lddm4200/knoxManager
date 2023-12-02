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
import com.example.knoxmanager.dao.HangxDao;
import com.example.knoxmanager.frm.frmhang;
import com.example.knoxmanager.model.Hang;
import com.example.knoxmanager.model.hangx;

import java.util.List;

public class hangAdrapter  extends ArrayAdapter<Hang> {
    private Context context;
    frmhang fragment;
    List<Hang> list;

    TextView tvMaHang, tvTenHang, tvNhaSx,tvGia;
    ImageView imgDel;

    public hangAdrapter(@NonNull Context context,frmhang fragment, List<Hang> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_view_hang, null);
        }
        final Hang item = list.get(position);
        if (item != null) {
            HangxDao hangxDao1 = new HangxDao(context);
            hangx hangx1 = hangxDao1.getID(String.valueOf(item.getMaNhaSx()));
            tvMaHang = v.findViewById(R.id.txtHang);
            tvMaHang.setText("Mã Hàng: SP" + item.getMaHang());

            tvTenHang = v.findViewById(R.id.txtTenHang);
            tvTenHang.setText("Tên Hàng: " + item.getTenSp());

            tvNhaSx = v.findViewById(R.id.txtNhaSx);
            tvNhaSx.setText("Hãng: " + hangx1.getTenNhasx());

            tvGia = v.findViewById(R.id.txtGia);
            tvGia.setText("giá: "+item.getGia()+" VND");

            imgDel = v.findViewById(R.id.delete_hang);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMaHang()));

            }
        });
        return v;
    }
}

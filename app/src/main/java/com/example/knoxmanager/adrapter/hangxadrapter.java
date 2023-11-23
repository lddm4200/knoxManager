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
import com.example.knoxmanager.frm.frmNhasx;
import com.example.knoxmanager.model.hangx;

import java.util.ArrayList;
import java.util.List;

public class hangxadrapter extends ArrayAdapter<hangx> {
    private Context context;
    frmNhasx fragment;
    TextView tvMaNhaSx, tvTenNhaSx;
    ImageView imgDel;
    private List<hangx> list = new ArrayList<>();

    public hangxadrapter(@NonNull Context context, frmNhasx fragment, ArrayList<hangx> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
        this.fragment = fragment;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_view_hangx, null);
        }
        final hangx item = list.get(position);
        if (item != null) {
            tvMaNhaSx = v.findViewById(R.id.txtMaHangX);
            tvMaNhaSx.setText("Mã Hãng: " + item.getMaNhaSx());
            tvTenNhaSx = v.findViewById(R.id.txtTenHangX);
            tvTenNhaSx.setText("Tên Hãng: " + item.getTenNhasx());

            imgDel = v.findViewById(R.id.deleteHangx);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // gọi phương thức xóa
                fragment.xoa(String.valueOf(item.getMaNhaSx()));
            }
        });
        return v;
    }
}

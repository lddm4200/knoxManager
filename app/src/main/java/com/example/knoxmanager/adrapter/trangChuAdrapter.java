package com.example.knoxmanager.adrapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.model.trangChu;

import java.util.List;

public class trangChuAdrapter extends RecyclerView.Adapter<trangChuAdrapter.viewTrangChu>{

    private List<trangChu> trangChus;

    public trangChuAdrapter(List<trangChu> trangChus) {
        this.trangChus = trangChus;
    }

    @NonNull
    @Override
    public viewTrangChu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trangchu,parent,false);
        return new viewTrangChu(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewTrangChu holder, int position) {
        trangChu trangChu = trangChus.get(position);
        if(trangChu== null){
            return;
        }
        holder.imgTrangChu.setImageResource(trangChu.getImage());
        holder.name.setText(trangChu.getName());
        holder.soLuong.setText(trangChu.getSoLuong());
    }

    @Override
    public int getItemCount() {
        if(trangChus!=null){
            return trangChus.size();
        }
        return 0;
    }

    public class viewTrangChu extends RecyclerView.ViewHolder{
        private ImageView imgTrangChu;
        private TextView name,soLuong;
        public viewTrangChu(@NonNull View itemView) {
            super(itemView);
            imgTrangChu=itemView.findViewById(R.id.imgTrangChu);
            name = itemView.findViewById(R.id.QlTrangChu);
            soLuong = itemView.findViewById(R.id.soLuongTangChu);

        }
    }
}

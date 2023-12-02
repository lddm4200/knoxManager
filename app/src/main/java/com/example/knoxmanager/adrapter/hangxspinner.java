package com.example.knoxmanager.adrapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.knoxmanager.R;
import com.example.knoxmanager.model.Hangx;

import java.util.ArrayList;

public class hangxspinner extends ArrayAdapter<Hangx> {
    private Context context;
    ArrayList<Hangx> list;
    TextView tvMahangx, tvTenhangx;
    public hangxspinner(@NonNull Context context, ArrayList<Hangx> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.spinner_hangx, null);

        }
        final Hangx item = list.get(position);
        if (item != null) {

            tvTenhangx = v.findViewById(R.id.txt_tenhangxsp);
            tvTenhangx.setText(item.getTenNhasx());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.spinner_hangx, null);

        }
        final Hangx item = list.get(position);
        if (item != null) {

            tvTenhangx = v.findViewById(R.id.txt_tenhangxsp);
            tvTenhangx.setText(item.getTenNhasx());
        }
        return v;
    }

}

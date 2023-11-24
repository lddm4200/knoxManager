package com.example.knoxmanager.frm;

import android.content.Intent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.photoAdrapter;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.photo;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;


public class frmtrangChu extends Fragment {


    public frmtrangChu() {
        // Required empty public constructor
    }
private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private photoAdrapter photoAdrapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_trang_chu, container, false);

    viewPager=view.findViewById(R.id.ViewPager);
    circleIndicator = view.findViewById(R.id.CircleIndicator);

    photoAdrapter = new photoAdrapter(getActivity(),photoList() );
    viewPager.setAdapter(photoAdrapter);

    circleIndicator.setViewPager(viewPager);
    photoAdrapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
        return view;
    }
    private List<photo> photoList(){
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.baner1));
        list.add(new photo(R.drawable.baner2));
        list.add(new photo(R.drawable.baner3));
        list.add(new photo(R.drawable.baner4));
        list.add(new photo(R.drawable.baner5));

        return list;
    }
}
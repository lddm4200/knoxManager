package com.example.knoxmanager.adrapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.knoxmanager.R;
import com.example.knoxmanager.model.photo;

import java.util.List;

public class photoAdrapter extends PagerAdapter {
private Context context;
private List<photo> photos;

    public photoAdrapter(Context context, List<photo> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_pjoto,container,false);
        ImageView imgphoto = view.findViewById(R.id.img_photo);

        photo photo = photos.get(position);
        if(photo !=null){
            Glide.with(context).load(photo.getResourceId()).into(imgphoto);

        }

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        if (photos!=null){
            return photos.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}

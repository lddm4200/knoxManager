package com.example.knoxmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class logIn extends AppCompatActivity {
private ImageView vit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        vit=findViewById(R.id.vit);
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.img);
            vit.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
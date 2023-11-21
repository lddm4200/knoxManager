package com.example.knoxmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class sigIn extends AppCompatActivity {
    private ImageView vit;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);
        vit=findViewById(R.id.vit);
        back=findViewById(R.id.backSigin);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.img);
            vit.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
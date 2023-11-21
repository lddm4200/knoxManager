package com.example.knoxmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class logIn extends AppCompatActivity {
private ImageView vit;
private TextView butumDK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        vit=findViewById(R.id.vit);
        butumDK=findViewById(R.id.btnDKlogin);

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.img);
            vit.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        butumDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(logIn.this,sigIn.class));
            }
        });
    }
}
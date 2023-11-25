package com.example.knoxmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.dao.NguoiDungDao;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class logIn extends AppCompatActivity {
private ImageView vit;
private TextView butumDK;
private Button button;
    private NguoiDungDao nguoiDungDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        vit=findViewById(R.id.vit);
        butumDK=findViewById(R.id.btnDKlogin);
        button=findViewById(R.id.btnDnLogin);
        EditText edttk=findViewById(R.id.edtTkDangNhap);
        EditText edtmk=findViewById(R.id.edtMkDangNhap);
        nguoiDungDao = new NguoiDungDao(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String taiKhoan = edttk.getText().toString();
                String matKhau = edtmk.getText().toString();

                if(nguoiDungDao.checkLogin(taiKhoan,matKhau)){
                    Toast.makeText(logIn.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(logIn.this, bottomMenu.class));
                }else {
                    Toast.makeText(logIn.this,"Đăng nhập khong thành công",Toast.LENGTH_SHORT).show();
                }


            }
        });

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.vit);
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
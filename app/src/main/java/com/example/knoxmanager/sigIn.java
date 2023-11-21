package com.example.knoxmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;

public class sigIn extends AppCompatActivity {
    private ImageView vit;
    private ImageView back;

    private NguoiDungDao nguoiDungDao;
    private NguoiDung nguoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sig_in);
        vit=findViewById(R.id.vit);
        back=findViewById(R.id.backSigin);
        EditText edtTaiKhoan = findViewById(R.id.edtTkDangKy);
        EditText edtMatKhau = findViewById(R.id.edtMkDk);
        EditText edtRe_MatKhau = findViewById(R.id.edtReMkDk);
        EditText edtTen = findViewById(R.id.edtTenDKy);
        TextView btnDangKy = findViewById(R.id.btnDksigin);
        nguoiDungDao = new NguoiDungDao(this);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taiKhoan = edtTaiKhoan.getText().toString();
                String matKhau = edtMatKhau.getText().toString();
                String re_password = edtRe_MatKhau.getText().toString();
                nguoiDung = new NguoiDung(taiKhoan, matKhau);

                if(!matKhau.equals(re_password)){
                    Toast.makeText(sigIn.this,"Pass word với re_password không giống",Toast.LENGTH_SHORT).show();
                }else if(nguoiDungDao.checkusername(taiKhoan)){
                    Toast.makeText(sigIn.this,"Tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
                }else{
                    if(nguoiDungDao.add(nguoiDung)){
                        Toast.makeText(sigIn.this,"Dăng ký thành công",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(sigIn.this,"Dăng ký không thành công",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

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
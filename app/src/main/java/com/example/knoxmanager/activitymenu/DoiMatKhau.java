package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.logIn;
import com.example.knoxmanager.model.NguoiDung;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DoiMatKhau extends AppCompatActivity {
    EditText edtDoiMKCu, edtDoiMKMoi, edtNhapLaiMKMoi;
    Button btnDoiMK;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edtDoiMKCu = findViewById(R.id.edtOldMK);
        edtDoiMKMoi = findViewById(R.id.edtNewMK);
        edtNhapLaiMKMoi = findViewById(R.id.edtReNewMK);
        btnDoiMK = findViewById(R.id.btnDoiMatKhauX);
        btnBack = findViewById(R.id.ibtnBackDMK);
        NguoiDungDao nguoiDungDao = new NguoiDungDao(this);
        //
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
            }
        });
        btnDoiMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME","");
                if(validate()>0){
                    NguoiDung nguoiDung = nguoiDungDao.getID(user);
                    nguoiDung.setMatKhau(edtDoiMKMoi.getText().toString());
                    nguoiDungDao.update(nguoiDung);
                    if(nguoiDungDao.update(nguoiDung)>0){
                        Toast.makeText(DoiMatKhau.this , "Thay đổi mật khẩu thành công!\nVui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                        finish();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(DoiMatKhau.this, logIn.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        },1000);
                    }
                    else {
                        Toast.makeText(DoiMatKhau.this, "Thay đổi mật khẩu thất bại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    public int validate(){
        int check =1;
        if(edtDoiMKCu.getText().length()==0||edtDoiMKMoi.getText().length()==0||edtNhapLaiMKMoi.getText().length()==0){
            Toast.makeText(this, "Phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        else {
            SharedPreferences sharedPreferences = this.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("PASSWORD","");
            String passNew = edtDoiMKMoi.getText().toString();
            String rePass = edtNhapLaiMKMoi.getText().toString();
            if(!passOld.equals(edtDoiMKCu.getText().toString())){
                Toast.makeText(this, "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check=-1;
            }
            if(!passNew.equals(rePass)){
                Toast.makeText(this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }

}
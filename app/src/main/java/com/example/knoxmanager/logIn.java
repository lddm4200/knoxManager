package com.example.knoxmanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.model.NguoiDung;

import java.io.IOException;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;

public class logIn extends AppCompatActivity {
private ImageView vit,vitdk;
private TextView butumDK;
ImageButton ibtnBack;
private Button button;
    CheckBox chkRemember;
    String strUser,strPass;
    EditText edttk,edtmk;
    private NguoiDungDao nguoiDungDao;
    Button btnluu;


    private NguoiDung nguoiDung;
    EditText edtTaiKhoan,edtMatKhau,edtRe_MatKhau,edtTen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        vit=findViewById(R.id.vit);
        butumDK=findViewById(R.id.btnDKlogin);
        button=findViewById(R.id.btnDnLogin);
        edttk=findViewById(R.id.edtTkDangNhap);
        edtmk=findViewById(R.id.edtMkDangNhap);
        chkRemember = findViewById(R.id.avtivity_login_CKB);
        nguoiDungDao = new NguoiDungDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);
        int level = pref.getInt("LEVEL",1);

        edttk.setText(user);
        edtmk.setText(pass);
        chkRemember.setChecked(rem);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkLogin();


            }
        });

        ArrayList<NguoiDung> list = new ArrayList<>();


        butumDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(logIn.this,0,list);
            }
        });

        try {
            GifDrawable gifDrawable = new GifDrawable(getResources(), R.drawable.vit);
            vit.setImageDrawable(gifDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void rememberUser(String u, String p, boolean status,int level) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
            edit.putInt("LEVEL",level);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }
    public void openDialog(Context context,int type,ArrayList<NguoiDung> list) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.activity_sig_in, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        NguoiDungDao tvDao = new NguoiDungDao(context);

        // anh xa
        edtTaiKhoan = dialog.findViewById(R.id.edtTkDangKy);
        edtTen = dialog.findViewById(R.id.edtTenDKy);
        edtMatKhau = dialog.findViewById(R.id.edtReMkDk);
        edtRe_MatKhau = dialog.findViewById(R.id.edtReMkDk);
        btnluu = dialog.findViewById(R.id.btnDksigin);
        ibtnBack = dialog.findViewById(R.id.ibtnBack);

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtTaiKhoan.getText().toString();
                String hoten = edtTen.getText().toString();
                String pass = edtMatKhau.getText().toString();
                String confirm = edtRe_MatKhau.getText().toString();
                if (user.trim().isEmpty() || hoten.trim().isEmpty()|| pass.trim().isEmpty() || confirm.trim().isEmpty()) {
                    Toast.makeText(context, "Không được để trống thông tin", Toast.LENGTH_SHORT).show();
                } else if (tvDao.checkUser(user)) {
                    Toast.makeText(context, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(confirm)) {
                    Toast.makeText(context, "Mật khẩu chưa khớp", Toast.LENGTH_SHORT).show();
                } else {
                    NguoiDung tv = new NguoiDung(user, pass, hoten, 1);
                    tvDao.insert(tv);
                    Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    if (type == 1) {
                        list.clear();
                        list.addAll(tvDao.getAll());
                    }
                    dialog.dismiss();
                }
            }
        });
    }

    public void checkLogin() {
        strUser = edttk.getText().toString();
        strPass = edtmk.getText().toString();
        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (nguoiDungDao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRemember.isChecked(),nguoiDungDao.getID(strUser).getPhanQuyen());
                Intent intent = new Intent(getApplicationContext(), bottomMenu.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
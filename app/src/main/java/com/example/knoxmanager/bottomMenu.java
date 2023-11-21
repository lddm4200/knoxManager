package com.example.knoxmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.knoxmanager.frm.frmNhasx;
import com.example.knoxmanager.frm.frmThem;
import com.example.knoxmanager.frm.frmhang;
import com.example.knoxmanager.frm.frmphieuTheoDoi;
import com.example.knoxmanager.frm.frmtrangChu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class bottomMenu extends AppCompatActivity {
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_menu);

        navigationView=findViewById(R.id.bottom_nav);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.trangChu){
                    frmtrangChu frmTrangchu= new frmtrangChu();
                    rep(frmTrangchu);
                }
                else if(item.getItemId()==R.id.hang){
                    frmhang frmhang= new frmhang();
                    rep(frmhang);
                }
                else if(item.getItemId()==R.id.nhaSx){
                    frmNhasx frmNhasx = new frmNhasx();
                    rep(frmNhasx);
                }
                else if(item.getItemId()==R.id.phieu){
                    frmphieuTheoDoi frmphieuTheoDoi = new frmphieuTheoDoi();
                    rep(frmphieuTheoDoi);
                }
                else if(item.getItemId()==R.id.them){
                    frmThem them= new frmThem();
                    rep(them);
                }


                return true;
            }
        });

    }
    public  void rep(Fragment frg){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.frmlauout,frg).commit();
    }
}
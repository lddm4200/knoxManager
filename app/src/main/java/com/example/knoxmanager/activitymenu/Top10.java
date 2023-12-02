package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.Top10Adapter;
import com.example.knoxmanager.dao.ThongKeDAO;
import com.example.knoxmanager.model.Top;

import java.util.ArrayList;

public class Top10 extends AppCompatActivity {
    RecyclerView recyclerView;
    ThongKeDAO thongKeDAO;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        //
        recyclerView = findViewById(R.id.rcvTop10);
        thongKeDAO = new ThongKeDAO(this);
        btnBack=findViewById(R.id.ibtnBackTop10);
        //
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //
        ArrayList<Top> list = thongKeDAO.getTop();
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);

        Top10Adapter addapter = new Top10Adapter(this,list);
        recyclerView.setAdapter(addapter);
    }
}
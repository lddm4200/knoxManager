package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.adrapter.ThongBaoAdapter;
import com.example.knoxmanager.dao.NguoiDungDao;
import com.example.knoxmanager.dao.ThongBaoDao;
import com.example.knoxmanager.model.NguoiDung;
import com.example.knoxmanager.model.ThongBao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QLThongBao extends AppCompatActivity {
    ListView lvThongBao;

    ArrayList<ThongBao> list;

    ArrayList<NguoiDung> listnd;

    NguoiDungDao ndDAO;

    static ThongBaoDao tbDAO;

    ThongBaoAdapter adapter;

    ThongBao item;
    LinearLayout imgAddThongBao;


    EditText edTieuDeAdd;
    ImageButton btnBack;
    EditText edNoiDungAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);
        lvThongBao = findViewById(R.id.lvThongBao);
        imgAddThongBao = findViewById(R.id.llThem);
        btnBack = findViewById(R.id.ibtnBackTB);
        tbDAO = new ThongBaoDao(this);
        ndDAO = new NguoiDungDao(this);

        loadData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        imgAddThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(QLThongBao.this,0,new ThongBao());
            }
        });

        lvThongBao.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                item = list.get(position);
                openDialog(QLThongBao.this,1,item);
                return false;
            }
        });

    }

    public void loadData() {
        list = (ArrayList<ThongBao>) tbDAO.getAll();
        adapter = new ThongBaoAdapter(this,this,list);
        lvThongBao.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tbDAO.delete(Id);
                loadData();
                dialog.cancel();
                Toast.makeText(QLThongBao.this, "Đã xóa", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(QLThongBao.this, "Không xóa", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void openDialog(final Context context, final int type, ThongBao tb){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thong_bao, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        edTieuDeAdd = view.findViewById(R.id.edtTieuDeTbDialog);
        edNoiDungAdd = view.findViewById(R.id.edtNoiDungTbDialog);
        TextView btnLuuThongBaoAdd = view.findViewById(R.id.btn_dialogluuThongBaos);

        if(type != 0){
            edTieuDeAdd.setText(item.getTieuDe());
            edNoiDungAdd.setText(item.getNoiDung());

        }



        btnLuuThongBaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                item = new ThongBao();
                item.setNoiDung(edNoiDungAdd.getText().toString());
                item.setTieuDe(edTieuDeAdd.getText().toString());
                item.setMaThongBao(tb.getMaThongBao());
                item.setNgayDang( new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
                List<NguoiDung> list = ndDAO.getAdmin();;
                item.setMaNguoiDung(list.get(0).getMaNguoiDung());
                if (validate() > 0){
                    if (type == 0){
                        if (tbDAO.insert(item) > 0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        if (tbDAO.update(item) > 0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                    loadData();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(edTieuDeAdd.getText().length() == 0 || edNoiDungAdd.getText().length() == 0){
            Toast.makeText(QLThongBao.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
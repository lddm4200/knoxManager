package com.example.knoxmanager.activitymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.knoxmanager.R;
import com.example.knoxmanager.dao.ThongKeDAO;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DoanhThu extends AppCompatActivity {
    private EditText frist_date,last_date;
    private Button btn_doah_thu;
    private TextView text_doanh_thu;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doanh_thu);
        frist_date = findViewById(R.id.edtTuNgay);
        last_date = findViewById(R.id.edtDenNgay);
        btn_doah_thu = findViewById(R.id.btnDoanhThuvv);
        text_doanh_thu = findViewById(R.id.TogDoangThu);

        frist_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(DoanhThu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int thg = i1 + 1;
                        if(thg < 10 ){
                            if(i2 < 10){
                                frist_date.setText(""+i+"/0"+thg+"/0"+i2+"");
                            }else{
                                frist_date.setText(""+i+"/0"+thg+"/"+i2+"");
                            }
                        }else{
                            if(i2 < 10){
                                frist_date.setText(""+i+"/"+thg+"/0"+i2+"");
                            }else{
                                frist_date.setText(""+i+"/"+thg+"/"+i2+"");
                            }
                        }

                    }
                },year,month,day);
                dialog.show();
            }
        });

        last_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(DoanhThu.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int thg = i1 + 1;
                        if(thg < 10 ){
                            if(i2 < 10){
                                last_date.setText(""+i+"/0"+thg+"/0"+i2+"");
                            }else{
                                last_date.setText(""+i+"/0"+thg+"/"+i2+"");
                            }
                        }else{
                            if(i2 < 10){
                                last_date.setText(""+i+"/"+thg+"/0"+i2+"");
                            }else{
                                last_date.setText(""+i+"/"+thg+"/"+i2+"");
                            }
                        }

                    }
                },year,month,day);
                dialog.show();
            }
        });

        btn_doah_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day1 = frist_date.getText().toString();
                String day2 = last_date.getText().toString();

                if(day1.length() <=0 || day2.length() <=0){
                    Toast.makeText(DoanhThu.this, "Không được để chống", Toast.LENGTH_SHORT).show();
                }else{
                    String x[] = day1.split("/");
                    String y[] = day2.split("/");

                    String count1 = "";
                    String count2 = "";
                    int tuNgay = Integer.valueOf(count1.concat(x[0]).concat(x[1]).concat(x[2]));
                    int denNgay = Integer.valueOf(count2.concat(y[0]).concat(y[1]).concat(y[2]));

                    if(tuNgay > denNgay){
                        Toast.makeText(DoanhThu.this, "Từ ngày phải nhỏ hơn đến ngày", Toast.LENGTH_SHORT).show();
                    }else{
                        ThongKeDAO thongKeDao = new ThongKeDAO(DoanhThu.this);
                        int doanh_thu = thongKeDao.getDoanhThu(day1,day2);
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        String tien = decimalFormat.format(doanh_thu);
                        text_doanh_thu.setText("Doanh thu : " + tien + " Đ");
                    }
                }
            }
        });

    }
}
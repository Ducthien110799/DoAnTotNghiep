package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;

import java.text.DecimalFormat;

public class ThanksActivity extends AppCompatActivity {
    TextView txtmadonhang, txttongtienthanhcong, txttenkhachhang;
    Button btnTieptucmuasamthanhcong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
        Anhxa();
        GetThongTinDonHang();
        ButtonTiepTuc();



    }

    private void ButtonTiepTuc() {
        btnTieptucmuasamthanhcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.manggiohang.clear();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void GetThongTinDonHang() {
        Intent intent= getIntent();
        String madonhang= intent.getStringExtra("madonhang");
        txtmadonhang.setText("DH"+madonhang);

        String tenkh= intent.getStringExtra("tenkhachhang");
        txttenkhachhang.setText(tenkh);

        long tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txttongtienthanhcong.setText(decimalFormat.format(tongtien) +" VND");
    }

    private void Anhxa() {
        txtmadonhang= findViewById(R.id.textviewMadonhang);
        txttenkhachhang = findViewById(R.id.textviewtenkhachhang);
        txttongtienthanhcong = findViewById(R.id.textviewtongtienthanhcong);
        btnTieptucmuasamthanhcong = findViewById(R.id.buttontieptucmuahangthanhcong);
    }
}
package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.model.KhachHang;
import com.cntt.doantotnghiep.model.Loaisp;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.cntt.doantotnghiep.activity.LoginActivity.khachHang;

public class TaikhoanActivity extends AppCompatActivity {

    Toolbar toolbartaikhoan;
    TextView tvidtk, tvtentk, tvemailtk, tvdiachitk, tvsodienthoaitk;
    public String idtk, tentk, emailtk, diachitk, sdttk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        AnhXa();
        ReadThongtinKhachhang();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            ActionTollbar();

        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Kiểm tra lại kết nối !");
            finish();
        }
    }

    public void ReadThongtinKhachhang() {
        tvidtk.setText("ID: KH"+khachHang.getIdkh().toString());
        tvtentk.setText("Tên: "+khachHang.getTenkh().toString());
        tvemailtk.setText("Email: "+khachHang.getEmailkh().toString());
        tvdiachitk.setText("Địa chỉ: "+khachHang.getDiachikh().toString());
        tvsodienthoaitk.setText("Số điện thoại: "+khachHang.getSodienthoaikh().toString());
    }

    private void AnhXa() {
        toolbartaikhoan= findViewById(R.id.toolbarTaikhoan);
        tvidtk = findViewById(R.id.tvIdtaikhoan);
        tvtentk = findViewById(R.id.tvTentaikhoan);
        tvemailtk = findViewById(R.id.tvEmailtaikhoan);
        tvdiachitk = findViewById(R.id.tvDiachitaikhoan);
        tvsodienthoaitk = findViewById(R.id.tvSodienthoaitaikhoan);
        idtk=tentk=emailtk=sdttk=diachitk="";
    }

    private void ActionTollbar() {
        setSupportActionBar(toolbartaikhoan);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbartaikhoan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    // Hiện giỏ hàng trên toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // click on Giỏ hàng trên toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.timkiem:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("abc",0);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
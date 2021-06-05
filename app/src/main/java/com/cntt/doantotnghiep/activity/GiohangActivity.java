package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.adapter.GiohangAdapter;
import com.cntt.doantotnghiep.model.Giohang;
import com.cntt.doantotnghiep.ultil.CheckConnection;

import java.text.DecimalFormat;

import static com.cntt.doantotnghiep.activity.LoginActivity.khachHang;

public class GiohangActivity extends AppCompatActivity {

    ListView lvgiohang;
    public  static TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan;
    Toolbar toolbargiohang;
    public  static GiohangAdapter giohangAdapter;

    long tongtien = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giohang);
        Anhxa();
        ActionTollbar();

        CheckData();
        EventUltil();
        CatchOnButton();
//        Intent intent = new Intent(getApplicationContext(), ThanksActivity.class);
//        intent.putExtra("tongtien", tongtien);
    }

    public void CatchOnButton() {
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ((MainActivity.manggiohang.size() > 0) && !(khachHang.getIdkh().toString().equals(null))){
                        Intent intent= new Intent(getApplicationContext(), ThongtinkhActivity.class);
                        startActivity(intent);
                    }else {
                        CheckConnection.ShowToast_Short(getApplicationContext(), "Không có sản phẩm nào để thanh toán !");
                    }
                }catch (Exception e){
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Vui lòng đăng nhập để thanh toán !");
                    Intent intent= new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }



    public static void EventUltil() {
        long tongtien = 0;
        for (int i = 0; i < MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txttongtien.setText(decimalFormat.format(tongtien) +" VND");
    }

    // Hiện giỏ hàng trên toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.iconmenu, menu);
        MenuItem itemGioHang = menu.findItem(R.id.menugiohang);
        MenuItem itemTimKiem= menu.findItem(R.id.timkiem);
        itemGioHang.setVisible(false);
        itemTimKiem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    // click on Giỏ hàng trên toolba
    private void CheckData() {
        if (MainActivity.manggiohang.size() <=0 ){
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.VISIBLE);// hiện thông báo
            lvgiohang.setVisibility(View.INVISIBLE);// ẩn listview
        }else {
            giohangAdapter.notifyDataSetChanged();
            txtthongbao.setVisibility(View.INVISIBLE);// ẩn thông báo
            lvgiohang.setVisibility(View.VISIBLE);// hiện listview
        }
    }

    private void ActionTollbar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang= findViewById(R.id.listviewgiohang);
        txtthongbao= findViewById(R.id.textviewthongbao);
        txttongtien = findViewById(R.id.textviewtongtien);
        btnthanhtoan= findViewById(R.id.buttonthanhtoan);
        toolbargiohang= findViewById(R.id.toolbargiohang);

        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
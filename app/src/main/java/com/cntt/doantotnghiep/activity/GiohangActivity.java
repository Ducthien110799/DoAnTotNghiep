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

public class GiohangActivity extends AppCompatActivity {

    ListView lvgiohang;
    public  static TextView txtthongbao;
    static TextView txttongtien;
    Button btnthanhtoan, btntieptucmua;
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

    private void CatchOnButton() {
        btntieptucmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0){
                    Intent intent= new Intent(getApplicationContext(), ThongtinkhActivity.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Không có sản phẩm nào để thanh toán !");
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
        return super.onCreateOptionsMenu(menu);
    }

    // click on Giỏ hàng trên toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

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
        btntieptucmua = findViewById(R.id.buttontieptucmuahang);
        toolbargiohang= findViewById(R.id.toolbargiohang);

        giohangAdapter = new GiohangAdapter(GiohangActivity.this, MainActivity.manggiohang);
        lvgiohang.setAdapter(giohangAdapter);
    }
}
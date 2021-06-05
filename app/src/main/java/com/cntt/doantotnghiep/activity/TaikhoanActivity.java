package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.adapter.AothunAdapter;
import com.cntt.doantotnghiep.adapter.DonhangAdapter;
import com.cntt.doantotnghiep.adapter.SearchAdapter;
import com.cntt.doantotnghiep.model.Donhang;
import com.cntt.doantotnghiep.model.KhachHang;
import com.cntt.doantotnghiep.model.Loaisp;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cntt.doantotnghiep.activity.LoginActivity.khachHang;

public class TaikhoanActivity extends AppCompatActivity {

    Toolbar toolbartaikhoan;
    TextView tvidtk, tvtentk, tvemailtk, tvdiachitk, tvsodienthoaitk, txtChuaxacnhan, txtThanhcong;
    public String idtk, tentk, emailtk, diachitk, sdttk;

    RecyclerView rcvdh;
    DonhangAdapter donhangAdapter;
    ArrayList<Donhang> mangdonhang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taikhoan);
        AnhXa();
        ReadThongtinKhachhang();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            ActionTollbar();
            txtChuaxacnhan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GetDonhangChoxacnhan();
                }
            });
            txtThanhcong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GetDonhangDaxacnhan();
                }
            });
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
        txtChuaxacnhan= findViewById(R.id.tv_donhangchoxacnhan);
        txtThanhcong = findViewById(R.id.tv_donhangthanhcong);

        rcvdh= findViewById(R.id.lv_donhang);
        idtk=tentk=emailtk=sdttk=diachitk="";

        mangdonhang = new ArrayList<>();

        donhangAdapter= new DonhangAdapter(getApplicationContext(), mangdonhang);
        rcvdh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rcvdh.setAdapter(donhangAdapter);
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

    private void GetDonhangDaxacnhan() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.URLDonhangdaxn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String iddh= "";
                String tensp= "";
                int soluong = 0;
                double giatien= 0;
                String ngaydathang = "";
                String xacnhandh= "";
                mangdonhang.clear();
                donhangAdapter.notifyDataSetChanged();
                if (response != null)
                {

                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i = 0; i<response.length(); i++){
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            // duyệt tuần tự theo json
                            iddh= jsonObject.getString("iddh");
                            tensp= jsonObject.getString("tensp");
                            soluong= jsonObject.getInt("soluong");
                            giatien= jsonObject.getDouble("giatien");
                            ngaydathang= jsonObject.getString("ngaydathang");
                            xacnhandh= jsonObject.getString("xacnhandh");


                            //đưa dữ liệu vào mảng
                            mangdonhang.add(new Donhang(iddh, tensp, soluong,giatien,ngaydathang,xacnhandh));

                            donhangAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Không có sản phẩm đang đặt hàng!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //Gửi request lên server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idtk",khachHang.getIdkh());// đẩy dữ liệu lên server
                return param;
            }
        };
        //thực hiện request
        requestQueue.add(stringRequest);
    }

    private void GetDonhangChoxacnhan() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.URLDonhangchuaxn, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String iddh= "";
                String tensp= "";
                int soluong = 0;
                double giatien= 0;
                String ngaydathang = "";
                String xacnhandh= "";
                mangdonhang.clear();
                donhangAdapter.notifyDataSetChanged();
                if (response != null)
                {
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i = 0; i<response.length(); i++){
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            // duyệt tuần tự theo json
                            iddh= jsonObject.getString("iddh");
                            tensp= jsonObject.getString("tensp");
                            soluong= jsonObject.getInt("soluong");
                            giatien= jsonObject.getDouble("giatien");
                            ngaydathang= jsonObject.getString("ngaydathang");
                            xacnhandh= jsonObject.getString("xacnhandh");
                            
                            //đưa dữ liệu vào mảng
                            mangdonhang.add(new Donhang(iddh, tensp, soluong,giatien,ngaydathang,xacnhandh));
                            donhangAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{

                    CheckConnection.ShowToast_Short(getApplicationContext(),"Không có sản phẩm chờ xác nhận!");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            //Gửi request lên server
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> param = new HashMap<String, String>();
                param.put("idtk",khachHang.getIdkh());// đẩy dữ liệu lên server
                return param;
            }
        };
        //thực hiện request
        requestQueue.add(stringRequest);
    }

}
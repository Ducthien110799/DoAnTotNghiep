package com.cntt.doantotnghiep.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import static com.cntt.doantotnghiep.activity.LoginActivity.khachHang;

public class ThongtinkhActivity extends AppCompatActivity {

    Toolbar toolbarthogntinkh;
    EditText edttenkhachhang, edtemail, edtsdt, edtdiachi;
    Button btnxacnhan;
    Spinner spinnerphivanchuyen, spinnerthanhtoan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkh);
        Anhxa();
        ActionTollbar();
//        loadthongtinKH();
        ReadDataThongtinkhachhang();
        CatchSpinerPhiVanChuyen();
        CatSpinnerThanhtoan();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            CatchButtonXacNhan();

        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
        }
    }

    private void ReadDataThongtinkhachhang() {
        edttenkhachhang.setText(khachHang.getTenkh().toString());
        edtdiachi.setText(khachHang.getDiachikh().toString());
        edtsdt.setText(khachHang.getSodienthoaikh().toString());
        edtemail.setText(khachHang.getEmailkh().toString());
    }

    public void doSaveThongtinKH(View view)  {
        // The created file can only be accessed by the calling application
        // (or all applications sharing the same user ID).
        SharedPreferences sharedPreferences= this.getSharedPreferences("gameSetting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("ten", edttenkhachhang.getText().toString().trim());
        editor.putString("diachi", edtdiachi.getText().toString().trim());
        editor.putString("sodienthoai", edtsdt.getText().toString().trim());
        editor.putString("email", edtemail.getText().toString().trim());

        // Save.
        editor.apply();
    }

    private void CatSpinnerThanhtoan() {
        String[] ptthanhtoan= new String[]{"Thanh toán khi nhận hàng"};
        ArrayAdapter<String> arrayadapterSize= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, ptthanhtoan);
        spinnerthanhtoan.setAdapter(arrayadapterSize);
    }

    private void CatchSpinerPhiVanChuyen() {
        String[] size= new String[]{"Giao hàng tiêu chuẩn (19,000 Đ)","Giao hàng nhanh (30,000 Đ)"};
        ArrayAdapter<String> arrayadapterSize= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, size);
        spinnerphivanchuyen.setAdapter(arrayadapterSize);
    }



    private void CatchButtonXacNhan() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                doSaveThongtinKH(v);

                String diachi = edtdiachi.getText().toString().trim();
                String ten = edttenkhachhang.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String phivanchuyen = spinnerphivanchuyen.getSelectedItem().toString();
                String thanhtoan = spinnerthanhtoan.getSelectedItem().toString();
                LocalDateTime ngaydathang = LocalDateTime.now();


                Random r = new Random();
                int id = r.nextInt(100);

                final int iddh = id;

                Intent intent= new Intent(getApplicationContext(), ThanksActivity.class);

                if (ten.length() > 0 && sdt.length()>0 && email.length() >0){
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse( String madonhang) {
                            if (Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request= new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        intent.putExtra("madonhang", String.valueOf(iddh));
                                        intent.putExtra("tenkhach", ten);
                                        intent.putExtra("ship", phivanchuyen);
                                        startActivity(intent);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray= new JSONArray();
                                        for (int i =0; i<MainActivity.manggiohang.size(); i++){
                                            JSONObject jsonObject= new JSONObject();
                                            try {
                                                jsonObject.put("iddh", iddh);
                                                jsonObject.put("idsp",  MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("soluongctdh", MainActivity.manggiohang.get(i).getSoluongsp());
                                                jsonObject.put("giactdh", MainActivity.manggiohang.get(i).getGiasp());

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            // tạo mảng json để gửi lên server
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String > hashMap= new HashMap<String, String>();
                                        hashMap.put("json", jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        //gửi dữ liệu lên server
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String > hashMap= new HashMap<String, String>();
                            hashMap.put("iddh", String.valueOf(iddh));
                            hashMap.put("idkh", khachHang.getIdkh().toString().trim());
                            hashMap.put("hinhthucthanhtoan", thanhtoan);
                            hashMap.put("diachigiaohang", diachi);
                            hashMap.put("phivanchuyen", phivanchuyen);
                            hashMap.put("ngaydathang", DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ngaydathang));
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);


                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Vui lòng nhận đủ dữ liệu");

                }
            };

        });
    };

    private void Anhxa() {
        toolbarthogntinkh = findViewById(R.id.toolbarthongtinkh);
        edttenkhachhang = findViewById(R.id.edittexttenkhachhang);
        edtsdt = findViewById(R.id.edittextsdt);
        edtdiachi = findViewById(R.id.edittextdiachi);
        edtemail = findViewById(R.id.edittextemail);
        btnxacnhan = findViewById(R.id.buttonxacnhan);
        spinnerphivanchuyen= findViewById(R.id.spinnerphivanchuyen);
        spinnerthanhtoan = findViewById(R.id.spinnerthanhtooan);
    }

    private void ActionTollbar() {
        setSupportActionBar(toolbarthogntinkh);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarthogntinkh.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
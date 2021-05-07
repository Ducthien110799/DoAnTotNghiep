package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

import java.util.HashMap;
import java.util.Map;

public class ThongtinkhActivity extends AppCompatActivity {

    Toolbar toolbarthogntinkh;
    EditText edttenkhachhang, edtemail, edtsdt, edtdiachi;
    Button btnxacnhan;
    Spinner spinnerTinh, spinnerphivanchuyen, spinnerthanhtoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtinkh);
        Anhxa();
        ActionTollbar();
        CatchSpinnerTinh();
        CatchSpinerPhiVanChuyen();
        CatSpinnerThanhtoan();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            CatchButtonXacNhan();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kết nối !");
        }
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

    private void CatchSpinnerTinh() {
            String[] size= new String[]{"An Giang","Bà Rịa - Vũng Tàu","Bắc Giang","Bắc Kạn","Bạc Liêu","Bắc Ninh","Bến Tre","Bình Định","Bình Dương","Bình Phước","Bình Thuận",
                    "Cà Mau","Cao Bằng","Đắk Lắk","Đắk Nông","Điện Biên","Đồng Nai","Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Tĩnh","Hải Dương","Hậu Giang",
                    "Hòa Bình","Hưng Yên","Khánh Hòa","Kiên Giang","Kon Tum","Lai Châu","Lâm Đồng","Lạng Sơn","Lào Cai","Long An","Nam Định","Nghệ An","Ninh Bình",
                    "Ninh Thuận","Phú Thọ","Quảng Bình","Quảng Nam","Quảng Ngãi","Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh","Thái Bình","Thái Nguyên",
                    "Thanh Hóa","Thừa Thiên Huế","Tiền Giang","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái","Phú Yên","Cần Thơ","Đà Nẵng","Hải Phòng",
                    "Hà Nội","TP HCM"};
            ArrayAdapter<String> arrayadapterSize= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, size);
            spinnerTinh.setAdapter(arrayadapterSize);
    }

    private void CatchButtonXacNhan() {
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tinh= spinnerTinh.getSelectedItem().toString();
                String nha = edtdiachi.getText().toString();
                String diachi = nha +","+tinh;
                String ten = edttenkhachhang.getText().toString().trim();
                String sdt = edtsdt.getText().toString().trim();
                String email = edtemail.getText().toString().trim();
                String phivanchuyen = spinnerphivanchuyen.getSelectedItem().toString();
                String thanhtoan = spinnerthanhtoan.getSelectedItem().toString();

                Intent intent= new Intent(getApplicationContext(), ThanksActivity.class);

                if (ten.length() > 0 && sdt.length()>0 && email.length() >0){
                    RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {

                            if (Integer.parseInt(madonhang) > 0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request= new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        intent.putExtra("madonhang", madonhang);
                                        intent.putExtra("tenkhachhang", ten);
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
                                                jsonObject.put("madonhang", madonhang);
                                                jsonObject.put("masanpham", MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham", MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham", MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("sizesanpham", MainActivity.manggiohang.get(i).getSizesp());
                                                jsonObject.put("soluongsanpham", MainActivity.manggiohang.get(i).getSoluongsp());
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
                            hashMap.put("tenkhachhang", ten);
                            hashMap.put("diachi", diachi);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email", email);
                            hashMap.put("phivanchuyen", phivanchuyen);
                            hashMap.put("phuongthucthanhtoan", thanhtoan);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);

                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Vui lòng nhận đủ dữ liệu");
                }
            }
        });
    }

    private void Anhxa() {
        toolbarthogntinkh = findViewById(R.id.toolbarthongtinkh);
        edttenkhachhang = findViewById(R.id.edittexttenkhachhang);
        edtsdt = findViewById(R.id.edittextsdt);
        edtdiachi = findViewById(R.id.edittextdiachi);
        edtemail = findViewById(R.id.edittextemail);
        btnxacnhan = findViewById(R.id.buttonxacnhan);
        spinnerTinh = findViewById(R.id.spinnerTinh);
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
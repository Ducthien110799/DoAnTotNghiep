package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextView tvDangNhap;
    private EditText edtTenRegister, edtEmailRegister, edtPassRegister, edtValidPassRegister, edtDiaChi, edtSoDienThoai;
    Button btnRegister;
    private String name, email, pass, validPass, diachi, sodienthoai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AnhXa();
        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


    }

    private void Register() {
        name = edtTenRegister.getText().toString().trim();
        email = edtEmailRegister.getText().toString().trim();
        pass = edtPassRegister.getText().toString().trim();
        validPass = edtValidPassRegister.getText().toString().trim();
        diachi= edtDiaChi.getText().toString().trim();
        sodienthoai= edtSoDienThoai.getText().toString().trim();

        try {
            if (name.length() == 0 || email.length() == 0 || pass.length() == 0 || validPass.length() == 0 || diachi.length() == 0 || sodienthoai.length() == 0) {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Vui lòng nhận đủ dữ liệu");
            } else if (!pass.equals(validPass)) {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Mật khẩu không trùng khớp !");
            } else if (!name.matches("^[^/0-9]{5,32}")) {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Tên không chưa số !");
            } else if (!email.matches("^[\\w-\\.]+@(gmail.com|gmail.com.vn)$")){
                CheckConnection.ShowToast_Short(getApplicationContext(), "Không phải là email !");
            } else if (!pass.matches("^[a-zA-Z0-9.!@#$?]{6,32}")) {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Mật khẩu từ 6-32 kí tự");
            }else if (!sodienthoai.matches("(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}")) {
                CheckConnection.ShowToast_Short(getApplicationContext(), "Không phải là số điện thoại ở Việt Nam !");
            } else {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.URLRegister, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.contains("1")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.putExtra("emailkh", email);
                            intent.putExtra("passkh", pass);
                            startActivity(intent);
                        } else if (response.contains("0")) {
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Email/Password Invalid");
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> data = new HashMap<>();
                        data.put("tenkh", name);
                        data.put("matkhaukh", pass);
                        data.put("emailkh", email);
                        data.put("diachi", diachi);
                        data.put("sodienthoai", sodienthoai);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                CheckConnection.ShowToast_Short(getApplicationContext(), "Đăng kí thành công !");
            }
        }catch (Exception e){
            CheckConnection.ShowToast_Short(getApplicationContext(), "Đăng kí không thành công ");
        }

    }

    private void AnhXa() {
        tvDangNhap = findViewById(R.id.tvDangNhap);
        edtTenRegister= findViewById(R.id.edtTenDangNhap);
        edtEmailRegister = findViewById(R.id.emailRegister);
        edtPassRegister = findViewById(R.id.passRegister);
        edtValidPassRegister = findViewById(R.id.validPassRegister);
        btnRegister = findViewById(R.id.btnregister);
        edtDiaChi= findViewById(R.id.diaChiRegister);
        edtSoDienThoai = findViewById(R.id.soDienThoaiRegister);


        name = email= pass= validPass=diachi=sodienthoai ="";
    }
}
package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.cntt.doantotnghiep.model.KhachHang;
import com.cntt.doantotnghiep.model.Loaisp;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView tvRegister;
    Button btnLogin;
    public static KhachHang khachHang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        getLogin();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginGet();
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void getLogin() {
        Intent intent= getIntent();
        String emailLogin= intent.getStringExtra("emailkh");
        String passLogin= intent.getStringExtra("passkh");

        edtEmail.setText(emailLogin);
        edtPassword.setText(passLogin);
    }
    private void LoginGet() {
        int islogin = 1;
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.URLLogin + String.valueOf(email)+"&password="+String.valueOf(password);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if (response != null) {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            // duyệt tuần tự theo json
                            String idkh = jsonObject.getString("idkh");
                            String tenkh = jsonObject.getString("tenkh");
                            String matkhau = jsonObject.getString("matkhaukh");
                            String emailkh = jsonObject.getString("emailkh");
                            String diachikh = jsonObject.getString("diachikh");
                            String sdtkh = jsonObject.getString("sodienthoai");

                            //đưa dữ liệu vào mảng
                            khachHang = new KhachHang(idkh, tenkh, emailkh, diachikh, sdtkh);
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Đăng nhập thành công !");
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.putExtra("islogin", islogin);
                            startActivity(intent);
                            finish();
                        }
                    }

                } catch (Exception e) {
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Tài khoản không xác thực !");
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
                param.put("email",email);
                param.put("password", password);
                return param;
            }
        };

        //thực hiện request
        requestQueue.add(stringRequest);
    }

    private void Anhxa() {

        edtEmail =(EditText) findViewById(R.id.emailLogin);
        edtPassword = findViewById(R.id.passLogin);
        tvRegister = findViewById(R.id.tvDangKi);
        btnLogin = findViewById(R.id.btnLogin);

    }
}
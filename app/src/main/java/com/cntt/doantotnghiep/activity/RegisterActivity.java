package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;

public class RegisterActivity extends AppCompatActivity {

    TextView txtLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin.findViewById(R.id.txtdangnhap);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
            }
        });
    }
}
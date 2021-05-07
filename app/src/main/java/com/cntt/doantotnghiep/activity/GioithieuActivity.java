package com.cntt.doantotnghiep.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;


import com.cntt.doantotnghiep.R;

public class GioithieuActivity extends AppCompatActivity {

    Toolbar toolbargioithieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu);
        toolbargioithieu= findViewById(R.id.toolbargioithieu);
        ActionTollbar();

    }
    private void ActionTollbar() {
        setSupportActionBar(toolbargioithieu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargioithieu.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
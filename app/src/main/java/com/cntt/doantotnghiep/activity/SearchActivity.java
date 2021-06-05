package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.adapter.LoaispAdapter;
import com.cntt.doantotnghiep.adapter.SanphamAdapter;
import com.cntt.doantotnghiep.adapter.SearchAdapter;
import com.cntt.doantotnghiep.model.Loaisp;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter searchAdapter;
    private SearchView searchView;

    Toolbar toolbarTim;
    ViewFlipper viewFlipper;
    ImageView account;
    ArrayList<Sanpham> mangsanpham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Anhxa();
        ActionTollbar();
        GetDuLieuSPMoinhat();
    }

    private void GetDuLieuSPMoinhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.URLAllsanpham, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response !=null){
                    int ID= 0;
                    String Tensanpham="";
                    int Giasanpham=0;
                    String Hinhanhsanpham="";
                    String Motasanpham="";
                    int IDsanpham = 0;

                    //duyện từng json object
                    for (int i =0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            //khai báo tên để lấy
                            ID = jsonObject.getInt("idsp");
                            Tensanpham= jsonObject.getString("tensp");
                            Giasanpham= jsonObject.getInt("giasp");
                            Hinhanhsanpham= jsonObject.getString("hinhanhsp");
                            Motasanpham= jsonObject.getString("motasp");
                            IDsanpham= jsonObject.getInt("idloaisp");

                            //thêm dữ liệu vào mảng vừa đọc
                            mangsanpham.add(new Sanpham(ID, Tensanpham, Giasanpham,null, Hinhanhsanpham, Motasanpham,0,IDsanpham));

                            searchAdapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //Thực hiện request
        requestQueue.add(jsonArrayRequest);
    }

    private void Anhxa() {

        toolbarTim = findViewById(R.id.toolbarTim);
        mangsanpham= new ArrayList<>();

        recyclerView= findViewById(R.id.rcv_search);
        searchAdapter= new SearchAdapter(getApplicationContext(), mangsanpham);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        recyclerView.setAdapter(searchAdapter);


//        recyclerView.setAdapter(searchAdapter);
//
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//        recyclerView.setAdapter(searchAdapter);

    }

    private void ActionTollbar() {
        setSupportActionBar(toolbarTim);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarTim.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//     Hiện giỏ hàng trên toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timkiem, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

}
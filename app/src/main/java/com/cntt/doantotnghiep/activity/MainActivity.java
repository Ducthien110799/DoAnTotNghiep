package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.adapter.LoaispAdapter;
import com.cntt.doantotnghiep.adapter.SanphamAdapter;
import com.cntt.doantotnghiep.model.Giohang;
import com.cntt.doantotnghiep.model.Loaisp;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.cntt.doantotnghiep.activity.LoginActivity.khachHang;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    ImageView account;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationView;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<Loaisp> mangloaisp;
    LoaispAdapter loaispAdapter;
    int id= 0;
    String tenloaisp= "";
    String hinhanhloaisp="";
    ArrayList<Sanpham> mangsanpham;
    SanphamAdapter sanphamAdapter;
    public static ArrayList<Giohang> manggiohang; //Mảng toàn cục để lưu giỏ hàng
    LoginActivity loginActivity= new LoginActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            ActionBar();
            CatchAccount();
            ActionViewFLiper();
            GetDuLieuLoaisp();
            GetDuLieuSPMoinhat();
            CatchOnItemListView();

        }else{
            CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại kể nối !");
            finish();
        }
    }

    private void CatchAccount() {
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= getIntent();
                int isLoginedTaiKhoan= intent.getIntExtra("islogin",0);
                int isLogined= intent.getIntExtra("abc",0);

                if(isLoginedTaiKhoan == 1){
                    Intent intent1= new Intent(MainActivity.this, TaikhoanActivity.class);
                    startActivity(intent1);
                }
                else if (isLogined==0){
                    Intent intent2= new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent2);
                }
            }
        });
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
                break;
            case R.id.timkiem:
                Intent intentSearch = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intentSearch);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //bắt sự kiện cho từng item, lấy theo position
                switch (position){
                    case 0:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){


                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, AoThunPhongActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, AoThunPoloActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, AoThunSweaterActivity.class);
                            intent.putExtra("idloaisanpham", mangloaisp.get(position).getId());
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, GioithieuActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        //kiểm tra internet để load lại dữ liệu
                        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this, LienheActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối !");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    private void GetDuLieuSPMoinhat() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.Duongdansanphammoinhat, new Response.Listener<JSONArray>() {
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

                            sanphamAdapter.notifyDataSetChanged();


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

    private void GetDuLieuLoaisp() {
        //Lấy nội dung của đường dẫn URL
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.DuongdanLoaisp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //nếu có dữ liệu trả về thì đọc dữ liệu, tránh gây lỗi cho app
                if (response != null){
                    //đọc từng dòng dữ liệu
                    for (int i=0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            id= jsonObject.getInt("idloaisp");
                            tenloaisp= jsonObject.getString("tenloaisp");
                            hinhanhloaisp= jsonObject.getString("hinhanhloaisp");

                            //thêm dữ liệu vừa đọc vào mảng
                            mangloaisp.add(new Loaisp(id, tenloaisp, hinhanhloaisp));

                            //update lại adapter
                            loaispAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    //add item liên hệ
                    mangloaisp.add(4, new Loaisp(0, "Thông tin","https://img.icons8.com/bubbles/50/000000/info.png"));
                    //add item giới thiệu
                    mangloaisp.add(5, new Loaisp(0,"Liên hệ","https://img.icons8.com/fluent/48/000000/add-contact-to-company.png"));

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(), error.toString());
            }
        });

        //gửi request
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionViewFLiper() {
        //thêm mảng hình ảnh
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://storage.googleapis.com/cdn.nhanh.vn/store/7136/bn/sb_1612426893_288.jpg");
        mangquangcao.add("https://4men.com.vn/images/thumbs/2020/06/aquaholic-summer-collection-news-571.png");
        mangquangcao.add("https://4men.com.vn/images/2020/05/20200503_156fab7a34dbc0d65c4dd02236406b95_1588495479.png");
        mangquangcao.add("https://4men.com.vn/images/2020/07/20200714_252bfdce9b068bff9d6c82358efdb1e4_1594716191.png");
        mangquangcao.add("https://4men.com.vn/images/thumbs/2020/12/-news-596.jpg");

        //gán hình ảnh vào imageview
        for (int i=0; i<mangquangcao.size(); i++){
            ImageView imageView= new ImageView(getApplicationContext());
            Picasso.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);

        //animation khởi tạo
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        //thực thi cho chạy slide
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.rounded);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void Anhxa() {
        toolbar= findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);
        recyclerViewmanhinhchinh= findViewById(R.id.recyclerview);
        navigationView= findViewById(R.id.navigationview);
        listViewmanhinhchinh= findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawerlayout);
        account = findViewById(R.id.account);
        mangloaisp= new ArrayList<>();
        mangsanpham= new ArrayList<>();

        //add dòng trang chủ
        mangloaisp.add(0, new Loaisp(0,"Trang chủ", "https://img.icons8.com/bubbles/100/000000/home.png"));

        loaispAdapter= new LoaispAdapter(mangloaisp, getApplicationContext());
        listViewmanhinhchinh.setAdapter(loaispAdapter);

        //sản phẩm mới nhất
        sanphamAdapter= new SanphamAdapter(getApplicationContext(), mangsanpham);
        recyclerViewmanhinhchinh.setHasFixedSize(true);
        recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        recyclerViewmanhinhchinh.setAdapter(sanphamAdapter);

        //để tránh mảng bị tạo lại mất giỏ hàng
        if (manggiohang != null){

        }else{
            manggiohang = new ArrayList<>();
        }
    }
}
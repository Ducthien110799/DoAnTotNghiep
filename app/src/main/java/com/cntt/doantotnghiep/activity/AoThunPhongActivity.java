package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.adapter.AothunAdapter;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.cntt.doantotnghiep.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AoThunPhongActivity extends AppCompatActivity {

    Toolbar toolbardt;
    ListView lvdt;
    AothunAdapter aothunAdapter;
    ArrayList<Sanpham> mangaothun;
    Button btnTimkiem;
    EditText edtTimkiem;
    
    int iddt = 0; //Biến toàn cục để lấy id loại sản phẩm
    int page = 1;

    View footerview;

    boolean isLoanding = false; //biến để tránh trường hợp load liên tục, đợi load xong rồi mới load tiếp

    mHandler mHandler;
    boolean limitdata = false; //Biến xác nhận hết dữ liệu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ao_thun_phong);
        Anhxa();
        if (CheckConnection.isNetworkAvailable(getApplicationContext())){
            GetIdLoaisp();
            ActionTollbar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(), "Kiểm tra lại kết nối !");
            finish();
        }
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

    private void LoadMoreData() {

        // click chuyển sang acivity chi tiết
        lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent= new Intent(getApplicationContext(), ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham", mangaothun.get(position));
                startActivity(intent);
            }
        });

        lvdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstItem, int visibleItem, int totalItem) {
                //load dữ liệu khi vuốt xuống cuối màn hình
                if (firstItem + visibleItem == totalItem && totalItem != 0 && isLoanding == false && limitdata == false){
                    isLoanding = true;

                    //cho thread chạy
                    ThreadData threadData= new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdanaothunphong+String.valueOf(Page);
        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id= 0;
                String Tendt= "";
                int Giadt = 0;
                String Hinhanhdt= "";
                String Mota = "";
                int Idspdt= 0;

                if (response != null && response.length() != 2) //Khi object trả về =2 thì nó hết giá trị rồi
                {
                    lvdt.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        for (int i = 0; i<response.length(); i++){
                            JSONObject jsonObject= jsonArray.getJSONObject(i);
                            // duyệt tuần tự theo json
                            id= jsonObject.getInt("idsp");
                            Tendt= jsonObject.getString("tensp");
                            Mota= jsonObject.getString("motasp");
                            Hinhanhdt= jsonObject.getString("hinhanhsp");
                            Giadt= jsonObject.getInt("giasp");
                            Idspdt= jsonObject.getInt("idloaisp");

                            //đưa dữ liệu vào mảng
                            mangaothun.add(new Sanpham(id, Tendt, Giadt,null, Hinhanhdt, Mota,0, Idspdt));

                            aothunAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitdata = true;
                    lvdt.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết sản phẩm !");
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
                param.put("idloaisanpham",String.valueOf(iddt));// đẩy dữ liệu lên server
                return param;
            }
        };

        //thực hiện request
        requestQueue.add(stringRequest);
    }

    private void ActionTollbar() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdLoaisp() {
        iddt = getIntent().getIntExtra("idloaisanpham",-1); //nhận dữ liệu gửi qua
        Log.d("giatriloaisanpham", iddt+"");
    }

    private void Anhxa() {
        toolbardt= findViewById(R.id.toolbardienthoai);
        lvdt= findViewById(R.id.listviewdienthoai);
        mangaothun= new ArrayList<>();
        aothunAdapter = new AothunAdapter(getApplicationContext(), mangaothun);
        lvdt.setAdapter(aothunAdapter);

        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        //gán progressbar vào
        footerview= inflater.inflate(R.layout.progressbar, null);
        mHandler= new mHandler();
    }

    //Host cung cấp luồng phụ chạy song song luồng chính, giúp load dữ liệu nhanh hơn
    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvdt.addFooterView(footerview); //load dữ liệu trước
                    break;
                case 1:
                    page++; // tăng page để load page tiếp theo
                    GetData(page);
                    isLoanding= false;
                    break;
            }
            super.handleMessage(msg);
        }
    }

    //Luồng phụ
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0); // gửi dữ liệu lên host
            try {
                Thread.sleep(3000); //nghỉ load trong 3s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Message message= mHandler.obtainMessage(1); //phương thức để kết nối giữ thread và handler
            mHandler.sendMessage(message);
            super.run();
        }
    }
}
package com.cntt.doantotnghiep.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.model.Giohang;
import com.cntt.doantotnghiep.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarchitiet;
    ImageView imgChitiet;
    TextView txtTen, txtGia, txtMota;
    Spinner spinner, spinnerSize;
    Button btnMua;

    int id = 0;
    String Tenchitiet = "";
    int Giachitiet = 0;
    String Hinhanhchitiet = "";
    String Motachitiet = "";
    int Idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        Anhxa();
        ActionTollbar();
        GetInfomation();
        CatchEventSpinner();
        CatchEventSpinnerSize();
        EventButton();
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
        }
        return super.onOptionsItemSelected(item);
    }

    private void EventButton() {
        btnMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.manggiohang.size() > 0){
                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
                    String size = spinnerSize.getSelectedItem().toString();

                    boolean exists = false;

                    // khi giỏ hàng đã có sản phẩm
                    for (int i = 0;i <MainActivity.manggiohang.size(); i++){
                        // thêm số lượng cộng dồn khi đã có sản phẩm trong giỏ hàng
                        if (MainActivity.manggiohang.get(i).getIdsp() == id && MainActivity.manggiohang.get(i).getSizesp().equals(size)){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);

                            //tính tiền tạm thời
                            MainActivity.manggiohang.get(i).setGiasp(Giachitiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }

                    // khi giỏ hàng chưa có sản phẩm
                    if (exists == false){
                        //lấy dữ liệu trong spinner
                        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                        String sizesp = spinnerSize.getSelectedItem().toString();

                        long Giamoi = soluong * Giachitiet; // tính tiền
                        //thêm vào mảng giỏ hàng
                        MainActivity.manggiohang.add(new Giohang(id,Tenchitiet,Giamoi,sizesp,Hinhanhchitiet, soluong));
                    }
                }else{
                    //lấy dữ liệu trong spinner
                    int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
                    String sizesp = spinnerSize.getSelectedItem().toString();
                    long Giamoi = soluong * Giachitiet; // tính tiền
                    //thêm vào mảng giỏ hàng
                    MainActivity.manggiohang.add(new Giohang(id,Tenchitiet,Giamoi,sizesp,Hinhanhchitiet, soluong));

                }
                Intent intent = new Intent(getApplicationContext(), GiohangActivity.class);
                startActivity(intent);
            }
        });
    }
    // hàm tạo giá trị cho spinner
    private void CatchEventSpinner() {
        Integer[] soluong= new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayadapter= new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, soluong);
        spinner.setAdapter(arrayadapter);
    }
    private void CatchEventSpinnerSize() {
        String[] size= new String[]{"S","M","L","XL"};
        ArrayAdapter<String> arrayadapterSize= new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, size);
        spinnerSize.setAdapter(arrayadapterSize);
    }

    private void GetInfomation() {

        //nhận dữ liệu từ ds gửi qua
        Sanpham sanpham= (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id= sanpham.getID();
        Tenchitiet = sanpham.getTensanpham();
        Giachitiet = sanpham.getGiasanpham();
        Hinhanhchitiet= sanpham.getHinhanhsanpham();
        Motachitiet= sanpham.getMotasanpham();
        Idsanpham= sanpham.getIDSanpham();

        //gán giá trị vừa nhận vào các view
        txtTen.setText(Tenchitiet);

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtGia.setText(decimalFormat.format(Giachitiet)+" VND");

        txtMota.setText(Motachitiet);
        Picasso.with(getApplicationContext()).load(Hinhanhchitiet).into(imgChitiet);
    }

    private void Anhxa() {
        toolbarchitiet = findViewById(R.id.toolbarchitietsanpham);
        imgChitiet = findViewById(R.id.imageviewchitietsanpham);
        txtTen = findViewById(R.id.textviewtenchitietsanpham);
        txtGia = findViewById(R.id.textviewgiachitietsanpham);
        txtMota = findViewById(R.id.textviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        spinnerSize = findViewById(R.id.spinnerSize);
        btnMua = findViewById(R.id.buttonThem);
    }
    private void ActionTollbar() {
        setSupportActionBar(toolbarchitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
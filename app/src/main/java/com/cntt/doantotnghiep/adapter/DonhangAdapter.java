package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.activity.ChiTietSanPhamActivity;
import com.cntt.doantotnghiep.model.Donhang;
import com.cntt.doantotnghiep.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DonhangAdapter extends RecyclerView.Adapter<DonhangAdapter.ItemHolder> {

    Context context;
    ArrayList<Donhang> arraylistsanpham;

    public DonhangAdapter(Context context, ArrayList<Donhang> arraylistsanpham) {
        this.context = context;
        this.arraylistsanpham = arraylistsanpham;
    }

    @NonNull
    @Override
    public DonhangAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_donhang, null);
        DonhangAdapter.ItemHolder itemHolder= new DonhangAdapter.ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DonhangAdapter.ItemHolder holder, int position) {
        //lấy vị trí sản phẩm
        Donhang donhang= arraylistsanpham.get(position);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");

        holder.txtIddh.setText("IDDH: DH"+donhang.getIddh());
        holder.txtTensp.setText("Sản phẩm: "+donhang.getTensamnpham()+"(Free Size)");
        holder.txtSoluong.setText("SL: "+Integer.toString(donhang.getSoluong()));
        holder.txtGiatien.setText("Giá tiền: "+decimalFormat.format(donhang.getGiatien())+" VNĐ");
        holder.txtNgaydathang.setText("Ngày đặt: "+donhang.getNgaydathang());

        if (donhang.getXacnhandh().equals("true")){
            holder.txtXacnhan.setText("Đặt hàng thành công !");
        }else if (donhang.getXacnhandh().equals("false")){
            holder.txtXacnhan.setText("Đơn hàng chờ xác nhận");
        }
    }
    @Override
    public int getItemCount() {
       return arraylistsanpham.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView txtIddh, txtTensp, txtSoluong, txtGiatien, txtNgaydathang, txtXacnhan;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            txtIddh= itemView.findViewById(R.id.tv_iddh);
            txtTensp = itemView.findViewById(R.id.tv_tensp);
            txtSoluong = itemView.findViewById(R.id.tv_soluong);
            txtGiatien = itemView.findViewById(R.id.tv_giatien);
            txtNgaydathang = itemView.findViewById(R.id.tv_ngaydathang);
            txtXacnhan = itemView.findViewById(R.id.tv_xacnhan);
        }
    }
}

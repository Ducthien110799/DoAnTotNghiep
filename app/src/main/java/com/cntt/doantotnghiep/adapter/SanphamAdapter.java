package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.activity.ChiTietSanPhamActivity;
import com.cntt.doantotnghiep.model.Sanpham;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class SanphamAdapter extends RecyclerView.Adapter<SanphamAdapter.ItemHolder> {

    Context context;
    ArrayList<Sanpham> arraylistsanpham;

    public SanphamAdapter(Context context, ArrayList<Sanpham> arraylistsanpham) {
        this.context = context;
        this.arraylistsanpham = arraylistsanpham;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat, null);
        ItemHolder itemHolder= new ItemHolder(v);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
       //lấy vị trí sản phẩm
       Sanpham sanpham= arraylistsanpham.get(position);

       holder.txttensanpham.setMaxLines(1);
       holder.txttensanpham.setEllipsize(TextUtils.TruncateAt.END);
       holder.txttensanpham.setText(sanpham.getTensanpham());

       //định dạng cho giá, cứ 3 số thì 1 dấu phẩy
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText(decimalFormat.format(sanpham.getGiasanpham())+" VND");
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).into(holder.imghinhanhsanpham);
    }

    @Override
    public int getItemCount() {
        return arraylistsanpham.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imghinhanhsanpham;
        public TextView txttensanpham, txtgiasanpham;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            imghinhanhsanpham = (ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txttensanpham= itemView.findViewById(R.id.textviewtensanpham);
            txtgiasanpham= itemView.findViewById(R.id.textviewgiasanpham);

            //set onclick
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham", arraylistsanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }
}

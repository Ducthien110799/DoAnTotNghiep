package com.cntt.doantotnghiep.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AothunsweaterAdaper extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayaothunsweater;

    public AothunsweaterAdaper(Context context, ArrayList<Sanpham> arraylaptop) {
        this.context = context;
        this.arrayaothunsweater = arraylaptop;
    }

    @Override
    public int getCount() {
        return arrayaothunsweater.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayaothunsweater.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttenlaptop, txtgialaptop, txtmotalaptop;
        public ImageView imglaptop;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        AothunsweaterAdaper.ViewHolder viewHolder = null;
        //mở lần đầu
        if (view == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_aopolo, null);

            viewHolder.txttenlaptop= view.findViewById(R.id.textviewlaptop);
            viewHolder.txtgialaptop= view.findViewById(R.id.textviewgialaptop);
            viewHolder.txtmotalaptop= view.findViewById(R.id.textviewmotalaptop);
            viewHolder.imglaptop= view.findViewById(R.id.imageviewlaptop);

            view.setTag(viewHolder);
        }
        else {
            viewHolder= (AothunsweaterAdaper.ViewHolder) view.getTag();
        }

        Sanpham sanpham= (Sanpham) getItem(position);

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");//set giá cho dễ đọc

        viewHolder.txttenlaptop.setMaxLines(1);
        viewHolder.txttenlaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txttenlaptop.setText(sanpham.getTensanpham());

        viewHolder.txtgialaptop.setText(decimalFormat.format(sanpham.getGiasanpham())+" VND");

        //Định dang khi mô tả quá dài
        viewHolder.txtmotalaptop.setMaxLines(1);//set số dòng
        viewHolder.txtmotalaptop.setEllipsize(TextUtils.TruncateAt.END);//hiển thị dấu 3 khi còn nữa

        viewHolder.txtmotalaptop.setText(sanpham.getMotasanpham());
        Picasso.with(context).load(sanpham.getHinhanhsanpham()).into(viewHolder.imglaptop);

        return view;
    }
}

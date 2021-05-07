package com.cntt.doantotnghiep.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cntt.doantotnghiep.R;
import com.cntt.doantotnghiep.activity.GiohangActivity;
import com.cntt.doantotnghiep.activity.MainActivity;
import com.cntt.doantotnghiep.model.Giohang;
import com.cntt.doantotnghiep.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang> arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }
    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return arraygiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        public TextView txttengiohang, txtgiagiohang, txtsizegiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus, btnxoa;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang, null);
            //gán cho các thuộc tính
            viewHolder.txttengiohang = view.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = view.findViewById(R.id.buttonminus);
            viewHolder.btnvalues = view.findViewById(R.id.buttonvalues);
            viewHolder.btnplus = view.findViewById(R.id.buttonplus);
            viewHolder.btnxoa= view.findViewById(R.id.buttonxoa);
            viewHolder.txtsizegiohang = view.findViewById(R.id.textviewsize);

            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }
        Giohang giohang = (Giohang) getItem(position);

        //set dữ liệu
        viewHolder.txttengiohang.setText(giohang.getTensp());

        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp()) + " VND");

        Picasso.with(context).load(giohang.getHinhsp()).into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSoluongsp()+"");
        viewHolder.txtsizegiohang.setText(giohang.getSizesp());

        int sl= Integer.parseInt(viewHolder.btnvalues.getText().toString());

       if (sl <=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }else  if (sl >1){
            viewHolder.btnminus.setVisibility(View.VISIBLE);
            viewHolder.btnplus.setVisibility(View.VISIBLE);
        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                int slht= MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);

                long giamoinhat= (giaht * slmoinhat) /slht; //công thức tính khi click thêm số lượng sản phẩm
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);

                // Gán lại giá mới cho item sản phẩm trong giỏ hàng
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " VND");

                //update lại tổng tiền
                GiohangActivity.EventUltil();

                finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                finalViewHolder.btnminus.setVisibility(View.VISIBLE);
            }
        });

        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btnvalues.getText().toString() ) -1;
                int slht= MainActivity.manggiohang.get(position).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(position).getGiasp();
                MainActivity.manggiohang.get(position).setSoluongsp(slmoinhat);

                long giamoinhat= (giaht * slmoinhat) /slht; //công thức tính khi click thêm số lượng sản phẩm
                MainActivity.manggiohang.get(position).setGiasp(giamoinhat);

                // Gán lại giá mới cho item sản phẩm trong giỏ hàng
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + " VND");

                //update lại tổng tiền
                GiohangActivity.EventUltil();

                finalViewHolder.btnvalues.setText(String.valueOf(slmoinhat));
                if (slmoinhat == 1){
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                }else {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                }
            }

        });
        viewHolder.btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa ?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (MainActivity.manggiohang.size() <=0 ){
                            GiohangActivity.txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(MainActivity.manggiohang.get(position));
                            GiohangActivity.giohangAdapter.notifyDataSetChanged();
                            GiohangActivity.EventUltil();
                            if (MainActivity.manggiohang.size() <=0){
                                GiohangActivity.txtthongbao.setVisibility(View.VISIBLE);
                            }
                            else {
                                GiohangActivity.txtthongbao.setVisibility(View.INVISIBLE);
                            }
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GiohangActivity.giohangAdapter.notifyDataSetChanged();
                        GiohangActivity.EventUltil();
                    }
                });
                builder.show();
            }
        });
        return view;
    }
}

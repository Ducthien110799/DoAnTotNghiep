package com.cntt.doantotnghiep.model;

public class Giohang {
    public int idsp;
    public String tensp;
    public long giasp;
    public String sizesp;
    public String hinhsp;
    public  int soluongsp;

    public Giohang(int idsp, String tensp, long giasp, String sizesp, String hinhsp, int soluongsp) {
        this.idsp = idsp;
        this.tensp = tensp;
        this.giasp = giasp;
        this.sizesp = sizesp;
        this.hinhsp = hinhsp;
        this.soluongsp = soluongsp;
    }

    public int getIdsp() {
        return idsp;
    }

    public void setIdsp(int idsp) {
        this.idsp = idsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public long getGiasp() {
        return giasp;
    }

    public void setGiasp(long giasp) {
        this.giasp = giasp;
    }

    public String getSizesp() {
        return sizesp;
    }

    public void setSizesp(String sizesp) {
        this.sizesp = sizesp;
    }

    public String getHinhsp() {
        return hinhsp;
    }

    public void setHinhsp(String hinhsp) {
        this.hinhsp = hinhsp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }
}

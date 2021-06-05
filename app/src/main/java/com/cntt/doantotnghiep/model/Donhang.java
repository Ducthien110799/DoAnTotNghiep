package com.cntt.doantotnghiep.model;

public class Donhang {
    private String iddh;
    private String tensamnpham;
    private int soluong;
    private double giatien;
    private String ngaydathang;
    private String xacnhandh;

    public Donhang(String iddh, String tensamnpham, int soluong, double giatien, String ngaydathang, String xacnhandh) {
        this.iddh = iddh;
        this.tensamnpham = tensamnpham;
        this.soluong = soluong;
        this.giatien = giatien;
        this.ngaydathang = ngaydathang;
        this.xacnhandh = xacnhandh;
    }

    public String getIddh() {
        return iddh;
    }

    public void setIddh(String iddh) {
        this.iddh = iddh;
    }

    public String getTensamnpham() {
        return tensamnpham;
    }

    public void setTensamnpham(String tensamnpham) {
        this.tensamnpham = tensamnpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getGiatien() {
        return giatien;
    }

    public void setGiatien(double giatien) {
        this.giatien = giatien;
    }

    public String getNgaydathang() {
        return ngaydathang;
    }

    public void setNgaydathang(String ngaydathang) {
        this.ngaydathang = ngaydathang;
    }

    public String getXacnhandh() {
        return xacnhandh;
    }

    public void setXacnhandh(String xacnhandh) {
        this.xacnhandh = xacnhandh;
    }
}
